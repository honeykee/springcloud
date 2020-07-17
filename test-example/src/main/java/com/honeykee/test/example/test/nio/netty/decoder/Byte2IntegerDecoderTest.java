package com.honeykee.test.example.test.nio.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/30 2:34 下午
 * @since JDK 1.8
 */
public class Byte2IntegerDecoderTest {

    @Test
    public void test(){
        ChannelInitializer channelInitializer = new ChannelInitializer() {
            @Override
            protected void initChannel( Channel ch ) throws Exception {
                ch.pipeline().addLast( new Byte2IntegerDecoder() );
                ch.pipeline().addLast( new IntegerProcessHandler() );
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel( channelInitializer );
        for ( int i = 0; i < 100; i++ ) {
            ByteBuf buffer = Unpooled.buffer();
            buffer.writeInt( i );
            channel.writeInbound( buffer );
        }
//        try {
//            Thread.sleep( Integer.MAX_VALUE );
//        } catch ( InterruptedException e ) {
//            e.printStackTrace();
//        }

    }

}
