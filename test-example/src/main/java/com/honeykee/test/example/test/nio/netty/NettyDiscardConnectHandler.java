package com.honeykee.test.example.test.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 11:15 上午
 * @since JDK 1.8
 */
public class NettyDiscardConnectHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用：handlerAdded()");
        Channel channel = ctx.channel();
        super.handlerAdded(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用：channelRegistered()");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用：channelActive()");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("被调用：channelRead()");
        NioSocketChannel nioSocketChannel = ( NioSocketChannel ) msg;
        System.out.println( "新连接" + nioSocketChannel.remoteAddress().toString() );
        System.out.println();
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用：channelReadComplete()");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive( ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用：channelInactive()");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用: channelUnregistered()");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("被调用：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
}
