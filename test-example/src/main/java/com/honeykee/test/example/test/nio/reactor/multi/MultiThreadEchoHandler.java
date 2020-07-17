package com.honeykee.test.example.test.nio.reactor.multi;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/28 11:05 上午
 * @since JDK 1.8
 */
public class MultiThreadEchoHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECEIVING = 0, SENDING = 1;
    int state = RECEIVING;
    static ExecutorService pool = Executors.newFixedThreadPool( 4 );
//    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,10,TimeUnit.MINUTES, new ArrayBlockingQueue<>( 10 ) );

    MultiThreadEchoHandler( Selector selector, SocketChannel socketChannel) throws Exception {
        socketChannel.configureBlocking( false );
        this.channel = socketChannel;
        //仅仅取得选择键，后设置感兴趣的IO事件
        sk = channel.register( selector, 0 );
        sk.attach( this );
        sk.interestOps( SelectionKey.OP_READ );
        selector.wakeup();
    }

    @Override
    public void run() {
        pool.execute( new AsyncTask() );
    }

    //异步任务，不在Reactor线程中执行
    public synchronized void asyncRun() {
        try {
            if (state == SENDING) {
                //写入通道
                channel.write(byteBuffer);
                //写完后,准备开始从通道读,byteBuffer切换成写模式
                byteBuffer.clear();
                //写完后,注册read就绪事件
                sk.interestOps(SelectionKey.OP_READ);
                //写完后,进入接收的状态
                state = RECEIVING;
            } else if (state == RECEIVING) {
                //从通道读
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {
                    System.out.println(new String(byteBuffer.array(), 0, length));
                }
                //读完后，准备开始写入通道,byteBuffer切换成读模式
                byteBuffer.flip();
                //读完后，注册write就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
                //读完后,进入发送的状态
                state = SENDING;
            }
            //处理结束了, 这里不能关闭select key，需要重复使用
            //sk.cancel();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    class AsyncTask implements Runnable{
        @Override
        public void run() {
            MultiThreadEchoHandler.this.asyncRun();
        }
    }

}
