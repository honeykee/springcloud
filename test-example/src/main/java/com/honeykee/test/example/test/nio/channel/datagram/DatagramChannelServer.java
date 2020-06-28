package com.honeykee.test.example.test.nio.channel.datagram;

import com.honeykee.test.example.test.nio.NioDictConstants;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/23 4:55 下午
 * @since JDK 1.8
 */
public class DatagramChannelServer {

    public static void receive()throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking( false );
        datagramChannel.bind( new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.UDP_PORT ) );
        System.out.println("UDP服务端启动...");
        Selector selector = Selector.open();
        datagramChannel.register( selector, SelectionKey.OP_READ );

        ByteBuffer byteBuffer = ByteBuffer.allocate( NioDictConstants.BUFFER_SIZE );
        while ( selector.select() > 0 ){
            Iterator< SelectionKey > iterator = selector.selectedKeys().iterator();
            while ( iterator.hasNext() ){
                SelectionKey selectionKey = iterator.next();
                if ( selectionKey.isReadable() ){
                    datagramChannel.receive( byteBuffer );
                    byteBuffer.flip();
                    System.out.println("收到数据:" + NioDictConstants.CHARSET_UTF8.decode( byteBuffer ).toString() );
                    byteBuffer.clear();
                }
                iterator.remove();
            }
        }
        System.out.println("UDP服务端停止...");
        selector.close();
        datagramChannel.close();
    }
    public static void main( String[] args ) throws Exception {
        receive();
    }


}
