package com.honeykee.test.example.test.nio.reactor.multi;

import com.honeykee.test.example.test.nio.NioDictConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程Reactor反应器
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/28 10:25 上午
 * @since JDK 1.8
 */
public class MultiThreadEchoServerReactor {
    ServerSocketChannel serverSocketChannel;
    AtomicInteger next = new AtomicInteger(0 );
    //selectors集合,引入多个selector选择器
    Selector[] selectors = new Selector[2];
    //引入多个子反应器
    SubReactor[] subReactors;
    MultiThreadEchoServerReactor() throws Exception {
        //初始化多个selector选择器
        selectors[0] = Selector.open();
        selectors[1] = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind( new InetSocketAddress( NioDictConstants.TCP_PORT ) );
        serverSocketChannel.configureBlocking( false );

        //第一个selector,负责监控新连接事件
        SelectionKey selectionKey = serverSocketChannel.register( selectors[ 0 ], SelectionKey.OP_ACCEPT );
        //附加新连接处理handler处理器到SelectionKey（选择键）
        selectionKey.attach( new AcceptorHandler() );
        SubReactor subReactor1 = new SubReactor( selectors[0] );
        SubReactor subReactor2 = new SubReactor( selectors[1] );
        subReactors = new SubReactor[]{subReactor1, subReactor2};
    }

    public void startService(){
        // 一子反应器对应一条线程
        new Thread( subReactors[0] ).start();
        new Thread( subReactors[1] ).start();
        System.out.println("服务启动。。。");
    }

    //反应器
    class SubReactor implements Runnable{
        //每条线程负责一个选择器查询
        final Selector selector;

        SubReactor(Selector selector ){
            this.selector = selector;
        }

        @Override
        public void run() {
            try{
                while( !Thread.interrupted() ){
                    selector.select();
                    Iterator< SelectionKey > iterator = selector.selectedKeys().iterator();
                    while ( iterator.hasNext() ){
                        SelectionKey next = iterator.next();
                        dispatch(next);
                        iterator.remove();
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        private void dispatch( SelectionKey next ) {
            Runnable handler = ( Runnable ) next.attachment();
            if ( Objects.isNull( handler ) ){
                return;
            }
            handler.run();
        }
    }

    class AcceptorHandler implements Runnable{
        @Override
        public void run() {
            try{
                SocketChannel socketChannel = serverSocketChannel.accept();
                if ( Objects.isNull( socketChannel ) ){
                    return;
                }
                SocketAddress remoteAddress = socketChannel.getRemoteAddress();
                System.out.println("新连接：" + remoteAddress.toString() );
                new MultiThreadEchoHandler(selectors[next.get()], socketChannel );
            }catch(Exception ex){
                ex.printStackTrace();
            }
            if (next.incrementAndGet() == selectors.length) {
                next.set(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        MultiThreadEchoServerReactor server = new MultiThreadEchoServerReactor();
        server.startService();
    }
}
