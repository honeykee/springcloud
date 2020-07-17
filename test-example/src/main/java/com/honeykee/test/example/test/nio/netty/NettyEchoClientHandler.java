package com.honeykee.test.example.test.nio.netty;

import com.honeykee.test.example.test.nio.NioDictConstants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 10:48 上午
 * @since JDK 1.8
 */
@ChannelHandler.Sharable
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {

    public static final NettyEchoClientHandler INSTANCE = new NettyEchoClientHandler();

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
//        super.channelRead( ctx, msg );
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println("byteBuf.hasArray:" + byteBuf.hasArray() );
        System.out.println("byteBuf.isDirect:" + byteBuf.isDirect() );
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.getBytes( 0, bytes );
        System.out.println( "client received: " + new String( bytes,"UTF-8" ));

        ByteBuffer buffer = ByteBuffer.allocate( byteBuf.readableBytes() );
        byteBuf.readBytes( buffer );
        //必须flip
        buffer.flip();
        System.out.println("receive buffer:" + NioDictConstants.CHARSET_UTF8.decode( buffer ).toString() );
        System.out.println(byteBuf.refCnt());
        byteBuf.release();
    }
}
