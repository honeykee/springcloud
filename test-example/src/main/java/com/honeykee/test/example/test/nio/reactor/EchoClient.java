package com.honeykee.test.example.test.nio.reactor;

import com.honeykee.test.example.test.nio.NioDictConstants;

import javax.annotation.security.RunAs;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/24 5:04 下午
 * @since JDK 1.8
 */
public class EchoClient {
    public static void main(String[] args) throws Exception {
        new EchoClient().startClient();
    }

    private void startClient() throws Exception {
        SocketChannel socketChannel = SocketChannel.open( new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.TCP_PORT ) );
        socketChannel.configureBlocking( false );
        while ( !socketChannel.finishConnect() ){
            //不断的自旋、等待连接完成，或者做一些其他的事情
        }
        System.out.println("客户端启动成功...");
        //启动接受线程
        Processor processor = new Processor(socketChannel);
        new Thread(processor).start();

    }

    class Processor implements Runnable{
        final Selector selector;
        final SocketChannel channel;

        Processor(SocketChannel channel) throws IOException {
            //Reactor初始化
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector,
                    SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        @Override
        public void run() {
            try{
                while ( !Thread.interrupted() ){
                    selector.select();
                    Iterator< SelectionKey > iterator = selector.selectedKeys().iterator();
                    while ( iterator.hasNext() ){
                        SelectionKey sk = iterator.next();
                        if (sk.isWritable()) {
                            ByteBuffer buffer = ByteBuffer.allocate( NioDictConstants.BUFFER_SIZE );

                            Scanner scanner = new Scanner(System.in);
                            System.out.print("请输入发送内容:");
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel) sk.channel();
                                String next = scanner.next();
                                buffer.put(( LocalDateTime.now() + " >>" + next).getBytes());
                                buffer.flip();
                                // 操作三：通过DatagramChannel数据报通道发送数据
                                socketChannel.write(buffer);
                                buffer.clear();
                            }
                        }
                        if (sk.isReadable()) {
                            // 若选择键的IO事件是“可读”事件,读取数据
                            SocketChannel socketChannel = (SocketChannel) sk.channel();

                            //读取数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int length = 0;
                            while ((length = socketChannel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                System.out.print("server echo:" + NioDictConstants.CHARSET_UTF8.decode( byteBuffer ).toString() );
                                byteBuffer.clear();
                            }
                        }
                        //处理结束了, 这里不能关闭select key，需要重复使用
                        //selectionKey.cancel();
                        iterator.remove();
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
