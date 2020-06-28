package com.honeykee.test.example.test.nio.channel.datagram;

import com.honeykee.test.example.test.nio.NioDictConstants;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/23 4:55 下午
 * @since JDK 1.8
 */
public class DatagramChannelClient {

    public static void send()throws Exception{
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking( false );
        ByteBuffer byteBuffer = ByteBuffer.allocate( NioDictConstants.BUFFER_SIZE );
        Scanner scanner = new Scanner( System.in );
        System.out.println("UDP客户端启动...");
        System.out.println("请输入发送内容...");
        while ( scanner.hasNext() ){
            String next = scanner.next();
            byteBuffer.put( ( System.currentTimeMillis() + "->" + next).getBytes() );
            byteBuffer.flip();
            datagramChannel.send( byteBuffer, new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.UDP_PORT ) );
            byteBuffer.clear();
        }
        System.out.println("UDP客户端停止...");
        scanner.close();
        datagramChannel.close();
    }

    public static void main( String[] args ) throws Exception {
        send();
    }

}
