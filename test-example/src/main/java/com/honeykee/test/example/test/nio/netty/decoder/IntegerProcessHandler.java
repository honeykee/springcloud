package com.honeykee.test.example.test.nio.netty.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 2:35 下午
 * @since JDK 1.8
 */
public class IntegerProcessHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
//        super.channelRead( ctx, msg );
        Integer integer = (Integer) msg;
        System.out.println("读取到一个整数：" + integer );
    }
}
