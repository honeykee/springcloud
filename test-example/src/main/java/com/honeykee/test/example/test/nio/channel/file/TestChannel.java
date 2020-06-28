package com.honeykee.test.example.test.nio.channel.file;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/19 3:38 下午
 * @since JDK 1.8
 */

@Slf4j
public class TestChannel {
    public static void main( String[] args ) throws Exception {
        File inFile =new File( "/Users/gemii/Documents/img/test.jpeg" );
        File outFile =new File( "/Users/gemii/Documents/img/" + System.currentTimeMillis() + ".jpeg" );
//        copyFile(inFile, outFile );


        FileInputStream fileInputStream = new FileInputStream( inFile );
        FileChannel inChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream( outFile );
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate( 1024 );

        long l = outChannel.transferFrom( inChannel, 0, inChannel.size() );
        System.out.println(l);
//        long l1 = inChannel.transferTo( 0, inChannel.size(), outChannel );
//        System.out.println(l1);

//        int len = -1;
//        while (( len = inChannel.read( byteBuffer ) ) != -1 ){
//            System.out.println(len);
//            //变为可读模式
//            byteBuffer.flip();
//            int write = outChannel.write( byteBuffer );
//            //变为写入模式
//            byteBuffer.clear();
//        }
//        //将缓冲区写入channel的数据强制写入磁盘
//        outChannel.force( true );

        //关闭通道
        inChannel.close();
        outChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
        System.out.println("------");
    }

    public static void copyFile( File src, File des ) {
        try {
            FileInputStream fis = new FileInputStream(src);
            FileOutputStream fos = new FileOutputStream(des);
            FileChannel inchannel = fis.getChannel();
            FileChannel outchannel = fos.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);
            while (inchannel.read(buf) != -1) {
                buf.flip();
                outchannel.write(buf);
                buf.clear();
            }
            outchannel.close();
            inchannel.close();
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            log.info("复制文件时发FileNotFoundException导常!", e);
        } catch ( IOException e) {
            log.info("复制文件时发IOException导常!", e);
        }
    }
}
