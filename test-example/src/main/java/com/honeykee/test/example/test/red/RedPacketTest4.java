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
public class RedPacketTest4 {

    public static void main( String[] args ) {
        List< Integer > luckRedList = getLuckRedList( 3, 5010 );
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
        Integer minValue = 100;
        Integer maxValue = 10000 ;
        int i = 1;
        int randomValue ;
        List<Integer> math = new ArrayList();
        Integer leftValue = total;
        Integer leftNums = number;

        while (i <= number ) {
            i++;
            int avgMaxValue = ( ( leftValue - ( leftNums * minValue ) ) / leftNums ) * 2;
            if ( maxValue > 0 && maxValue >= minValue ) {
                avgMaxValue = ( maxValue - minValue ) < avgMaxValue ? ( maxValue - minValue ) : avgMaxValue;
            }

            //最后一次
            if ( leftNums == 1 ) {
                if ( maxValue > 0 && maxValue >= minValue ) {
                    if ( leftValue <= maxValue ) {
                        randomValue = leftValue;
                    } else {
                        avgMaxValue = maxValue - minValue;
                        randomValue = rd.nextInt( avgMaxValue ) + minValue;
                    }
                } else {
                    randomValue = leftValue;
                }
            } else {
                if (  avgMaxValue == 0 ){
                    randomValue = minValue;
                }else {
                    randomValue = rd.nextInt( avgMaxValue );
                    randomValue += minValue;
                }
            }
            leftValue -= randomValue ;
            leftNums--;
            math.add( randomValue );
        }
        return math;
    }

}
