package com.honeykee.test.example.test.nio.netty;

import com.honeykee.test.example.test.nio.NioDictConstants;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 10:25 上午
 * @since JDK 1.8
 */
public class NettyEchoClient {

    Bootstrap bootstrap = new Bootstrap();

    public void runClient(){
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        //设置工作线程组
        bootstrap.group( workerLoopGroup );
        //设置Nio类型
        bootstrap.channel( NioSocketChannel.class );
        //设置监听端口
        bootstrap.remoteAddress( NioDictConstants.HOST_LOCAL, NioDictConstants.TCP_PORT );
        //设置通道参数
        bootstrap.option( ChannelOption.SO_KEEPALIVE, true );
        bootstrap.option( ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT );
        //装配子通道流水线
        bootstrap.handler( new ChannelInitializer< SocketChannel >() {
            @Override
            protected void initChannel( SocketChannel ch ) throws Exception {
                // pipeline管理子通道channel中的Handler
                // 向子channel流水线添加一个handler处理器
                ch.pipeline().addLast( NettyEchoClientHandler.INSTANCE );
            }
        } );
        ChannelFuture channelFuture = bootstrap.connect();
        channelFuture.addListener( (ChannelFuture future )  -> {
            if ( future.isSuccess() ){
                System.out.println("EchoClient客户端连接成功!");
//                Channel channel = future.channel();
//                Scanner scanner = new Scanner( System.in );
//                System.out.println("请输入发送内容:");
//                while(scanner.hasNext()){
//                    String next = scanner.next();
//                    byte[] bytes = ( LocalDateTime.now() + ">>" + next ).getBytes();
//                    ByteBuf buffer = channel.alloc().buffer();
//                    buffer.writeBytes( bytes );
//                    channel.writeAndFlush( buffer );
//                    System.out.println("请输入发送内容:");
//                }
            }else {
                System.out.println("EchoClient客户端连接失败!");
            }
        } );
        // 阻塞,直到连接完成
        try {
            channelFuture.sync();
            System.out.println("sync success...");
            Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner( System.in );
            System.out.println("请输入发送内容:");
            while(scanner.hasNext()){
                String next = scanner.next();
                byte[] bytes = ( LocalDateTime.now() + ">>" + next ).getBytes();
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes( bytes );
                channel.writeAndFlush( buffer );
                System.out.println("请输入发送内容:");
            }
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }finally {
            System.out.println("finally......");
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main( String[] args ) {
        NettyEchoClient nettyEchoClient = new NettyEchoClient();
        nettyEchoClient.runClient();
    }
}
