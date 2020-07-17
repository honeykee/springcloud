package com.honeykee.test.example.test.nio.netty;

import com.honeykee.test.example.test.nio.NioDictConstants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.SneakyThrows;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/29 10:48 上午
 * @since JDK 1.8
 */
public class NettyDiscardServer {

    ServerBootstrap bootstrap = new ServerBootstrap();

    public void runServer(){
        //创建反应器线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();
        //设置反应器线程组
        bootstrap.group( bossLoopGroup, workerLoopGroup );
        //设置Nio通道类型
        bootstrap.channel( NioServerSocketChannel.class );
        //设置端口
        bootstrap.localAddress( NioDictConstants.TCP_PORT );
        //设置通道参数，开启TCP心跳
        bootstrap.option( ChannelOption.SO_KEEPALIVE, true );
        bootstrap.option( ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT );
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        //装配子通道流水线
        //设置父通道，一般不用设置
//        bootstrap.handler( new NettyDiscardConnectHandler() );
        bootstrap.childHandler( new ChannelInitializer< SocketChannel >(){
            //父通道有连接到达时会创建一个子通道，并初始化
            @Override
            protected void initChannel( SocketChannel channel ) throws Exception {
                //流水线管理子通道中的Handler处理器
                //向子通道流水线添加一个Handler处理器
                channel.pipeline().addLast( new NettyDiscardHandler() );
            }
        } );
        try{
            //开始绑定端口，通过调用sync同步方法阻塞直到绑定成功。，返回一个端口绑定的异步任务
            ChannelFuture channelFuture = bootstrap.bind().sync();
            channelFuture.addListener( new GenericFutureListener< Future< ? super Void > >() {
                @Override
                public void operationComplete( Future< ? super Void > future ) throws Exception {
                    Object object = future.get();
                    System.out.println("start server success...");
                }
            } );
            System.out.println("服务启动成功，监听端口：" + channelFuture.channel().localAddress() );
            //等待通道关闭的异步任务结束
            //自我阻塞，服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            //当通道被关闭，closeFuture实例sync()会返回。
            closeFuture.addListener( new GenericFutureListener< Future< ? super Void > >() {
                @Override
                public void operationComplete( Future< ? super Void > future ) throws Exception {
                    System.out.println("close success...");
                }
            } );
            closeFuture.sync();
            System.out.println("over........");
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            //释放所有资源，包括创建的所有线程
            bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }
        System.out.println("服务关闭");
    }

    public static void main( String[] args ) {
        NettyDiscardServer nettyDiscardServer = new NettyDiscardServer();
        nettyDiscardServer.runServer();
    }
}
