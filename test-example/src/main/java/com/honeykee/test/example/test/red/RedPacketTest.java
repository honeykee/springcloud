package com.honeykee.test.example.test.red;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/7/16 3:51 下午
 * @since JDK 1.8
 */
public class RedPacketTest {

    public static void main( String[] args ) {
        List red = getLuckRedList( 3, 300, 100 );
        System.out.println(red);
        System.out.println("==========================================");
        List red2 = getLuckRedListback( 3, 3.1f, 1 );
        System.out.println(red2);
    }
    //单位为分的红包
    public static List<Integer> getLuckRedList( int number, Integer total, Integer min){
        //红包数
        //int number = 300;
        //红包总额
        // float total = 500;
        Integer money;
        //最小红包
        //double min = 0.48;
        double max;
        int i = 1;
        List<Integer> math = new ArrayList();
//        DecimalFormat df = new DecimalFormat("###.##");
        while (i < number ) {
            //保证即使一个红包是最大的了,后面剩下的红包,每个红包也不会小于最小值
            max = total - min * (number - i);
            int k = (int)(number - i) / 2;
            //保证最后两个人拿的红包不超出剩余红包
            if (number - i <= 2) {
                k = number - i;
            }
            //最大的红包限定的平均线上下
            max = max / k;
            //保证每个红包大于最小值,又不会大于最大值
            money = (int) (min  + Math.random() * (max - min  + 1));
//            money = (float)money / 100;
            //保留两位小数
//            money = Float.parseFloat(df.format(money));
            total=( total  - money );
//            total = total/100;
            math.add( money );
            System.out.println("第" + i + "个人拿到" + money + "剩下" + total);
            i++;
            //最后一个人拿走剩下的红包
            if (i == number) {
                math.add(total);
                System.out.println("第" + i + "个人拿到" + total + "剩下0");
            }
        }
        //取数组中最大的一个值的索引
        System.out.println("本轮发红包中第" + (math.indexOf( Collections.max(math)) + 1) + "个人手气最佳");
        return math;
    }

    //单位到元的红包
    public static List getLuckRedListback( int number, float total, double min){
        //红包数
        //int number = 300;
        //红包总额
        // float total = 500;
        float money;
        //最小红包
        //double min = 0.48;
        double max;
        int i = 1;
        List math = new ArrayList();
        DecimalFormat df = new DecimalFormat("###.##");
        while (i < number) {
            //保证即使一个红包是最大的了,后面剩下的红包,每个红包也不会小于最小值
            max = total - min * (number - i);
            int k = (int)(number - i) / 2;
            //保证最后两个人拿的红包不超出剩余红包
            if (number - i <= 2) {
                k = number - i;
            }
            //最大的红包限定的平均线上下
            max = max / k;
            //保证每个红包大于最小值,又不会大于最大值
            money = (int) (min * 100 + Math.random() * (max * 100 - min * 100 + 1));
            money = (float)money / 100;
            //保留两位小数
            money = Float.parseFloat(df.format(money));
            total=(int)(total*100 - money*100);
            total = total/100;
            math.add(money);
            System.out.println("第" + i + "个人拿到" + money + "剩下" + total);
            i++;
            //最后一个人拿走剩下的红包
            if (i == number) {
                math.add(total);
                System.out.println("第" + i + "个人拿到" + total + "剩下0");
            }
        }
        //取数组中最大的一个值的索引
        System.out.println("本轮发红包中第" + (math.indexOf( Collections.max(math)) + 1) + "个人手气最佳");
        return math;
    }
}
