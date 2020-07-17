package com.honeykee.test.example.test.nio.reactor.single;

import com.honeykee.test.example.test.nio.NioDictConstants;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/24 4:02 下午
 * @since JDK 1.8
 */
public class EchoHandler implements Runnable {

    final SocketChannel socketChannel;
    final SelectionKey selectionKey;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECEIVING = 0, SENDING = 1;
    int state = RECEIVING;

    EchoHandler( Selector selector, SocketChannel socketChannel) throws Exception {
        socketChannel.configureBlocking( false );
        this.socketChannel = socketChannel;
        selectionKey = socketChannel.register( selector, 0 );
        selectionKey.attach( this );
        selectionKey.interestOps( SelectionKey.OP_READ );
        selector.wakeup();
    }
    
    @Override
    public void run() {
        try{
            if(state == SENDING){
                //写入通道
                socketChannel.write( byteBuffer );
                byteBuffer.clear();
                selectionKey.interestOps( SelectionKey.OP_READ );
                state = RECEIVING;
            }else if(state == RECEIVING ){
                //从通道读
                int length = 0;
                while (  ( length = socketChannel.read( byteBuffer ) ) > 0 ){
                    byteBuffer.flip();
                    System.out.println( new String(byteBuffer.array(), 0, length) );
                    System.out.println("------------------------------------------------");
                    System.out.println( NioDictConstants.CHARSET_UTF8.decode( byteBuffer ).toString() );
                }
                //读完后，准备开始写入通道,byteBuffer切换成读模式
                byteBuffer.flip();
                //读完后，注册write就绪事件
                selectionKey.interestOps( SelectionKey.OP_WRITE );
                //读完后,进入发送的状态
                state = SENDING;
            }
            //处理结束了, 这里不能关闭select key，需要重复使用
//            selectionKey.cancel();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
