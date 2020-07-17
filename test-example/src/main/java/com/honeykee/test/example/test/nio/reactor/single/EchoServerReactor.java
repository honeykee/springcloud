package com.honeykee.test.example.test.nio.reactor.single;

import com.honeykee.test.example.test.nio.NioDictConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/24 2:53 下午
 * @since JDK 1.8
 */
public class EchoServerReactor implements Runnable{

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    EchoServerReactor() throws Exception{
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind( new InetSocketAddress( NioDictConstants.TCP_PORT ) );
        serverSocketChannel.configureBlocking( false );
        SelectionKey selectionKey = serverSocketChannel.register( selector, SelectionKey.OP_ACCEPT );
        selectionKey.attach( new AcceptorHandler() );
    }

    @Override
    public void run() {
        try {
            System.out.println("服务启动完成...");
            while ( !Thread.interrupted() ){
                System.out.println("等待数据...");
                selector.select();
                Iterator< SelectionKey > iterator = selector.selectedKeys().iterator();
                while ( iterator.hasNext() ){
                    SelectionKey next = iterator.next();
                    dispatch(next);
                }
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    void dispatch(SelectionKey selectionKey){
        Runnable handler = (Runnable)selectionKey.attachment();
        if ( null != handler ){
            handler.run();
        }
    }

    class AcceptorHandler implements Runnable{
        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if( socketChannel != null ){
                    new EchoHandler(selector, socketChannel );
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(new EchoServerReactor() ).start();
    }
}
