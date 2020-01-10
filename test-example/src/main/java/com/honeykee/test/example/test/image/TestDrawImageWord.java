package com.honeykee.test.example.test.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-19 17:50
 * @since JDK 1.8
 */
public class TestDrawImageWord {
    public static void main(String[] args) throws IOException {
        File destFile = new File("/Users/gemii/Downloads/test/" + System.currentTimeMillis() + ".png" );
        BufferedImage img = new BufferedImage(750, 1334, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = img.createGraphics();
        // 填充白色背景
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, img.getWidth(), img.getHeight());

        // 抗锯齿 添加文字
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        graphics.setFont(new Font("苹方-简", Font.PLAIN, 19));
        //阴影颜色
        graphics.setPaint( new Color(96,56,43) );
        graphics.setPaint( new Color( 0xff60382b ) );
        //先绘制阴影
        graphics.drawString("长按识别小程序码", 20, 40);
        graphics.drawString("查看职位详情\n在线投递简历", 20, 60);



        graphics.dispose();

        // 输出
        ImageIO.write(img, "png", destFile);


    }



}
