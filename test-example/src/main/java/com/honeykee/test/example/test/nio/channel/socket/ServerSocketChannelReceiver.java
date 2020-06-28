package com.honeykee.test.example.test.nio.channel.socket;

import com.honeykee.test.example.test.nio.NioDictConstants;
import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/23 4:09 下午
 * @since JDK 1.8
 */
@Data
public class ServerSocketChannelReceiver {

    public static void main( String[] args ) throws Exception {
        startServer();
    }

    /**
     * 服务端保存客户端对象，对应一个客户端文件
     */
//    @Data
    static class Client{
        //文件名称
        String fileName;
        //文件长度
        long fileLength;
        //开始传输事件
        long startTime;
        //客户端地址
        InetSocketAddress socketAddress;
        //输出的文件通道
        FileChannel outFileChannel;
    }

    //
    static Map<SelectableChannel, Client> clientMap = new HashMap<>();



    public static void startServer() throws Exception{
        //获取选择器
        Selector selector = Selector.open();
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
//        serverSocket.bind(  new InetSocketAddress( NioDictConstants.TCP_PORT ) );
        //设置为非阻塞
        serverSocketChannel.configureBlocking( false );
        //绑定连接
        serverSocketChannel.bind( new InetSocketAddress( NioDictConstants.TCP_PORT ) );
        System.out.println("socket服务器启动成功...");
        //注册通道的连接事件到选择器,ServerSocketChannel只接收Accept
        serverSocketChannel.register( selector, SelectionKey.OP_ACCEPT );
        while ( selector.select() > 0 ){
            Iterator< SelectionKey > iterator = selector.selectedKeys().iterator();
            while ( iterator.hasNext() ){
                SelectionKey next = iterator.next();
                if ( next.isConnectable() ){
                    System.out.println("channel connect...");
                }else if ( next.isAcceptable() ){
                    System.out.println("channel accept...");
                    //连接就绪事件，获取客户端连接
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel ) next.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if ( Objects.isNull( socketChannel ) ) {
                        continue;
                    }
                    socketChannel.configureBlocking( false );
                    //将新连接的事件注册到selector
                    socketChannel.register( selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE );
                    Client client = new Client();
                    client.socketAddress =  (InetSocketAddress ) socketChannel.getRemoteAddress() ;
                    clientMap.put( socketChannel, client );
                    System.out.println("socketChannel 连接成功:" + socketChannel.getRemoteAddress() );
                    client.startTime = System.currentTimeMillis();
                }else if ( next.isReadable() ){
                    System.out.println("channel read...");
                    //如果是可读事件，读取数据
                    processData(next);
                }else if ( next.isWritable() ){
//                    System.out.println("channel write...");
                }
                //NIO会累加，如果不移除下一次又会被选择到
                iterator.remove();
            }
        }
        serverSocketChannel.close();
        selector.close();
    }

    private static void processData( SelectionKey next ) throws Exception {
        Client client = clientMap.get( next.channel() );
        SocketChannel socketChannel = (SocketChannel) next.channel();
        int length = 0;
        ByteBuffer byteBuffer = ByteBuffer.allocate( NioDictConstants.BUFFER_SIZE );
        byteBuffer.clear();
        while( (length = socketChannel.read( byteBuffer )) > 0 ){
            byteBuffer.flip();
            if ( null == client.fileName ){
                //客户端首先发送过来的是文件名称
                String fileName = NioDictConstants.CHARSET_UTF8.decode( byteBuffer ).toString();
                File fileDirectory = new File( NioDictConstants.FILE_PATH );
                if ( !fileDirectory.exists() ){
                    fileDirectory.mkdir();
                }
                client.fileName = fileName;
                String filePath = fileDirectory.getAbsolutePath() + File.separatorChar + System.currentTimeMillis() + "-" + fileName ;
                System.out.println("目标文件:" + filePath );
                File file = new File( filePath );
                FileOutputStream fileOutputStream = new FileOutputStream( file );
                FileChannel fileChannel = fileOutputStream.getChannel();
                client.outFileChannel = fileChannel ;
            }else if ( 0 == client.fileLength ){
                //客户端其次发送文件大小
                long fileLength = byteBuffer.getLong();
                client.fileLength = fileLength ;
                System.out.println("开始接收文件内容...");
            }else {
                //客户端最后发送过来的是文件内容
                client.outFileChannel.write( byteBuffer );
            }
            byteBuffer.clear();
        }
        next.cancel();
        if ( length == -1 ){
            System.out.println("传输完成:" + client.fileName );
            client.outFileChannel.close();
            System.out.println("花费时间毫秒:"+ ( System.currentTimeMillis() - client.startTime ) );
        }
    }
}
