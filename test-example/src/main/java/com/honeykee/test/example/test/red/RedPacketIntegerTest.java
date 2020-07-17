package com.honeykee.test.example.test.red;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 整数红包测试
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/7/16 4:05 下午
 * @since JDK 1.8
 */
@Slf4j
public class RedPacketIntegerTest {

    public static void main( String[] args ) {
        List< Integer > luckRedList = getLuckRedList( 3, 300 );
        System.out.println( luckRedList );
    }

    private static Random rd = new Random();

    /**
     * @param number 红包数
     * @param total 红包总额，整数，单位：分
     * @return
     */
    public static List<Integer> getLuckRedList( int number, Integer total ){
        //默认最小1元钱
        Integer min = 100;
//        Integer maxValue = 10000 ;
        Integer money;
        Integer max;
        int i = 1;
        List<Integer> math = new ArrayList();


        while (i < number ) {
//            int avgMaxValue = ( ( leftValue - ( leftNums * min ) ) / leftNums ) * 2;
//            if ( maxValue > 0 && maxValue >= min ) {
//                avgMaxValue = ( maxValue - min ) < avgMaxValue ? ( maxValue - min ) : avgMaxValue;
//            }

            //保证即使一个红包是最大的了,后面剩下的红包,每个红包也不会小于最小值
            max = total - min * (number - i);
            System.out.println("max:" + max );

            int k = (number - i) / 2;
            System.out.println("k:" + k);
            //保证最后两个人拿的红包不超出剩余红包
            if (number - i <= 2) {
                k = number - i;
                System.out.println("-k2:"+ k );
            }

            //最大的红包限定的平均线上下
            max = max / k;
            System.out.println("max2:" + max );

            //保证每个红包大于最小值,又不会大于最大值
            money = (int) ( min  + Math.random() * ( max - min  + 1 ));

            total=( total  - money );
            math.add( money );
//            System.out.println("第" + i + "个人拿到" + money + "剩下" + total);
            i++;
            //最后一个人拿走剩下的红包
            if (i == number) {
                math.add(total);
//                System.out.println("第" + i + "个人拿到" + total + "剩下0");
            }


        }
        //取数组中最大的一个值的索引
//        System.out.println("本轮发红包中第" + (math.indexOf( Collections.max(math)) + 1) + "个人手气最佳");
        return math;
    }

}
