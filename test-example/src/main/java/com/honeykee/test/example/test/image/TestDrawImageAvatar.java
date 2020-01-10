package com.honeykee.test.example.test.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;


/**
 * 使用 graphics 把头像图片切成圆图 并在头像后面加一个白圆圈  https://blog.csdn.net/zengrenyuan/article/details/80502451
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-19 16:40
 * @since JDK 1.8
 */
public class TestDrawImageAvatar {
    public static void main(String[] args) throws IOException {
        String url = "https://img-blog.csdn.net/20180529211243786?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3plbmdyZW55dWFu/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/7";
        BufferedImage avatarImage = ImageIO.read(new URL(url));
        int width = 120;
        // 透明底的图片
        BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = formatAvatarImage.createGraphics();
        //把图片切成一个圓
        {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
            int border = 1;
            //图片是一个圆型
            Ellipse2D.Double shape = new Ellipse2D.Double(border, border, width - border * 2, width - border * 2);
            //需要保留的区域
            graphics.setClip(shape);
            graphics.drawImage(avatarImage, border, border, width - border * 2, width - border * 2, null);
            graphics.dispose();
        }
        //在圆图外面再画一个圆
        {
            //新创建一个graphics，这样画的圆不会有锯齿
            graphics = formatAvatarImage.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int border = 3;
            //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
            //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
            Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            graphics.setStroke(s);
            graphics.setColor(Color.WHITE);
            graphics.drawOval(border, border, width - border * 2, width - border * 2);
            graphics.dispose();
        }

        try (OutputStream os = new FileOutputStream("/Users/gemii/Downloads/test/" + System.currentTimeMillis() + ".png" )) {
            ImageIO.write(formatAvatarImage, "PNG", os);
        }


        {
            url = "https://img-blog.csdn.net/20180529213113521";
            BufferedImage srcImg = ImageIO.read(new URL(url));
            //scrImg加载完之后没有任何颜色
            BufferedImage blankImage = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
            graphics = blankImage.createGraphics();
            graphics.drawImage(srcImg, 0, 0, null);

            int x = (blankImage.getWidth() - width) / 2;
            int y = (blankImage.getHeight() - width) / 2;
            graphics.drawImage(formatAvatarImage, x, y, width, width, null);
            try ( OutputStream os = new FileOutputStream("/Users/gemii/Downloads/test/" + System.currentTimeMillis() + ".png" )) {
                ImageIO.write(blankImage, "PNG", os);
            }
        }

    }
}
