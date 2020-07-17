package com.honeykee.test.example.test.nio.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.Date;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 2:34 下午
 * @since JDK 1.8
 */
public class Byte2IntegerReplayingDecoder extends ReplayingDecoder {
    @Override
    protected void decode( ChannelHandlerContext ctx, ByteBuf in, List< Object > out ) throws Exception {
        //ReplayingDecoder不用检查ByteBuf是否有字节，这里的ByteBuf不是原始的，而是ReplayingDecoderByteBuf包装过的，所以不需要字节检查
        Integer integer = in.readInt();
        System.out.println("解码出一个正数：" + integer);
        out.add( integer );
    }

    public static void main( String[] args ) {
        System.out.println(new Date(1593863460000L));
    }
}
