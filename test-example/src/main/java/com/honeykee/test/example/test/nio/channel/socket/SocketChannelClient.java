package com.honeykee.test.example.test.nio.channel.socket;

import com.honeykee.test.example.test.nio.NioDictConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/23 4:08 下午
 * @since JDK 1.8
 */
@Slf4j
public class SocketChannelClient {

    public static void sendFile() throws Exception {
        File inFile = NioDictConstants.inFile;
        if( !inFile.exists() ) {
            log.error("[ZCS]sendFile inFile not exist");
            return;
        }
        FileChannel fileInChannel = new FileInputStream( inFile).getChannel();
        SocketChannel socketChannel = SocketChannel.open();
        //直接open，connect
//        SocketChannel socketChannel = SocketChannel.open( new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.TCP_PORT ) );
        printSocketChannelStatus( socketChannel );
        //不能bind
//        socketChannel.bind( new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.TCP_PORT ) );
        socketChannel.connect(  new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.TCP_PORT )  );
//        socketChannel.socket().connect( new InetSocketAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.TCP_PORT )  );
        socketChannel.configureBlocking( false );
        printSocketChannelStatus( socketChannel );
        while ( !socketChannel.finishConnect() ){
            System.out.println("socketChannel not connect...");
        }
        System.out.println("socketChannel connect server success");
        //先传输文件名
        ByteBuffer inFileNameEncode = NioDictConstants.CHARSET_UTF8.encode( inFile.getName() );
        socketChannel.write( inFileNameEncode );
        //再传输文件大小
        ByteBuffer byteBuffer = ByteBuffer.allocate( NioDictConstants.BUFFER_SIZE );
        byteBuffer.putLong( inFile.length() );
        byteBuffer.flip();
        socketChannel.write( byteBuffer );
        byteBuffer.clear();
        //最后开始发送文件
        System.out.println("开始传输文件");
        int length = 0;
        long progress = 0;
        while( ( length = fileInChannel.read( byteBuffer ) ) > 0 ){
            byteBuffer.flip();
            socketChannel.write( byteBuffer );
            byteBuffer.clear();
            progress += length;
            System.out.println("| " + ( 100 * progress/ inFile.length() )  + "% |" );
        }
        fileInChannel.close();
        socketChannel.shutdownOutput();
        socketChannel.close();
        System.out.println("文件传输完成");
    }

    public static void main( String[] args ) throws Exception {
        sendFile();
    }

    public static void printSocketChannelStatus( SocketChannel socketChannel ) throws IOException {
        System.out.println( "socketChannel.isOpen:" + socketChannel.isOpen() );
        System.out.println( "socketChannel.isBlocking:" + socketChannel.isBlocking() );
        System.out.println( "socketChannel.isConnected:" + socketChannel.isConnected() );
        System.out.println( "socketChannel.isConnectionPending:" + socketChannel.isConnectionPending() );
        System.out.println( "socketChannel.isRegistered:" + socketChannel.isRegistered() );
//        System.out.println( "socketChannel.finishConnect:" + socketChannel.finishConnect() );
    }


}
