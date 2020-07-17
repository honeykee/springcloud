package com.honeykee.test.example.test.red;

import java.util.Random;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/7/16 4:19 下午
 * @since JDK 1.8
 */
public class RedPacketTest3 {

    public static void main( String[] args ) {
        int i = GetRandomClaimAmount( 300, 3, 100, 10000 );
        System.out.println( i );
    }

    private static Random rd = new Random();

    public static int GetRandomClaimAmount( int leftValue, int leftNums, int minValue, int maxValue ) {
        int randomValue = 0;
        if ( minValue <= 0 ) {
            minValue = 1;
        }
        //保证至少都有最小值，然后在剩下的值里面随机
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
        return randomValue;
    }

}
