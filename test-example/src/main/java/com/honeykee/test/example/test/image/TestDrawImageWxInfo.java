package com.honeykee.test.example.test.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-19 19:30
 * @since JDK 1.8
 */
public class TestDrawImageWxInfo {
    public static void main( String[] args ) throws Exception{
        BufferedImage image = new BufferedImage( 750, 1334, BufferedImage.TYPE_INT_RGB );
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage poster = ImageIO.read( new File( "/Users/gemii/Documents/img/poster.jpg" ) );
//        BufferedImage avatarImage = ImageIO.read( new File( "/Users/gemii/Documents/img/aa.jpeg" ) );
        BufferedImage avatarImage = ImageIO.read( new URL( "https://img-blog.csdn.net/20180529211243786?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3plbmdyZW55dWFu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/7" ) );

        graphics.setColor( Color.white );
        graphics.drawImage( poster,null,0,0 );

        //
        drawOther( graphics, avatarImage );
        graphics.dispose();

        ImageIO.write( image,"png",new File( "/Users/gemii/Downloads/test/" + System.currentTimeMillis() + ".png" ) );
    }

    private static void drawOther( Graphics2D graphics, BufferedImage avatarImage ) {
        String name = "福利小助手 小娟";

        //字体
        graphics.setFont(new Font("微软雅黑", Font.ITALIC, 39));
        //文字颜色
        graphics.setPaint( new Color( 0xff60382b ) );
        graphics.drawString( name, 334, 429 );

        graphics.setFont(new Font("苹方", Font.PLAIN, 19));
        graphics.setPaint( new Color( 0xff877e7c ) );
        graphics.drawString( "这里是SR简介啊，专业的服务顾问", 334, 467 );
        graphics.drawString( "这里是SR简介啊，专业的服务顾问", 334, 497 );
        graphics.drawString( "这里是SR简介啊，专业的服务顾问", 334, 527 );
        graphics.drawString( "这里是SR简介啊，专业的服务顾问", 334, 557 );
        graphics.drawString( "这里是SR简介啊，专业的服务顾问", 334, 587 );


        //画头像
        int width = 167;

        int left = 124;
        int top = 406;
        //图片是一个圆型
        Ellipse2D.Double shape = new Ellipse2D.Double( left, top, width , width );
        //需要保留的区域
        graphics.setClip(shape);
        graphics.drawImage(avatarImage, left, top, width , width , null);
    }
}
