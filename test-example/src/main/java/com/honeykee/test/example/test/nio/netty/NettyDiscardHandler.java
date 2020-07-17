package com.honeykee.test.example.test.nio.netty;

import com.honeykee.test.example.test.nio.NioDictConstants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/29 11:08 上午
 * @since JDK 1.8
 */
@ChannelHandler.Sharable
public class NettyDiscardHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered( ChannelHandlerContext ctx ) throws Exception {
        super.channelRegistered( ctx );
    }

    @Override
    public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
//        readOne(ctx,msg);
        readTwo(ctx,msg);
    }

    private void readTwo( ChannelHandlerContext ctx, Object msg ) {
        //回写数据
//        ctx.channel().writeAndFlush( msg );
        ByteBuf byteBuf = (ByteBuf) msg;
        try {
            if ( byteBuf.isReadable() ){
                //1.get到byte[]中,不会移动readIndex
                byte[] bytes = new byte[byteBuf.readableBytes()];
                byteBuf.getBytes( 0, bytes );
                System.out.println("收到1：" +  new String( bytes,"UTF-8" ) );
                byteBuf.markReaderIndex();
                //2.read到byte[]中，会移动readIndex指针,read会导致ctx.writeAndFlush( msg )为空数据
                byteBuf.readBytes( bytes );
                System.out.println("收到2：" +  new String( bytes ) );
                System.out.println("收到2：" +  new String( bytes,"UTF-8" ) );

                //3.read到ByteBuffer中
                byteBuf.resetReaderIndex();
                ByteBuffer byteBuffer = ByteBuffer.allocate( byteBuf.readableBytes()  );
                byteBuf.readBytes( byteBuffer );
                byteBuffer.flip();
                String s = new String( byteBuffer.array() );
                System.out.println("收到3：" +  s );
                String string = NioDictConstants.CHARSET_UTF8.decode( byteBuffer ).toString();
                System.out.println("收到4：" +  string );
                if ( !byteBuf.hasArray() ){
                    System.out.println("Direct ByteBuf...");
                    //Direct ByteBuf不能通过array()得到byte数组
//                    byte[] array = byteBuf.array();
                }
                //引用一次
                byteBuf.resetReaderIndex();
                byteBuf.retain();
                System.out.println("回写前 msg.refCnt：" +  byteBuf.refCnt() );
                ChannelFuture channelFuture = ctx.writeAndFlush( msg );
                channelFuture.addListener( future -> System.out.println("回写后 msg.refCnt：" +  byteBuf.refCnt() ) );
            }
        }catch ( Exception ex ){
            ex.printStackTrace();
        }finally {
            ReferenceCountUtil.release( msg );
            System.out.println("finally 之后 msg.refCnt：" +  byteBuf.refCnt() );
        }
    }

    private void readOne( ChannelHandlerContext ctx, Object msg ) throws UnsupportedEncodingException {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("msg type: " + (in.hasArray()?"堆内存":"直接内存"));

        int len = in.readableBytes();
        byte[] arr = new byte[len];
        in.getBytes(0, arr);
        System.out.println("server received: " + new String(arr, "UTF-8"));

        //写回数据，异步任务
        System.out.println("写回前，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        ChannelFuture f = ctx.writeAndFlush(msg);
        f.addListener((ChannelFuture futureListener) -> {
            System.out.println("写回后，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        });
    }
}
