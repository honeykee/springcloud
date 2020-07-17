package com.honeykee.test.example.test.nio.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 2:34 下午
 * @since JDK 1.8
 */
public class Byte2IntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List< Object > out ) throws Exception {
        while ( in.isReadable() && in.readableBytes() >= 4 ){
            Integer integer = in.readInt();
            System.out.println("解码出一个正数：" + integer);
            out.add( integer );
        }
    }
}
