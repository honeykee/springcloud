package com.honeykee.test.example.test.nio;

import java.io.File;
import java.nio.charset.Charset;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/23 4:10 下午
 * @since JDK 1.8
 */
public class NioDictConstants {
    public static File inFile =new File( "/Users/gemii/Documents/img/test.jpeg" );
    public static File outFile =new File( "/Users/gemii/Documents/img/" + System.currentTimeMillis() + ".jpeg" );

    public static String FILE_PATH = "/Users/gemii/Documents/img";

    public static final String HOST_LOCAL = "127.0.0.1";
    public static final Integer TCP_PORT = 8181;

    public static final Integer UDP_PORT = 8189;

    public static final Integer BUFFER_SIZE = 1024;

    public final static Charset CHARSET_UTF8 = Charset.forName( "UTF-8" );
}
