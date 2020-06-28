package com.honeykee.test.example.test.pool;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/15 2:07 下午
 * @since JDK 1.8
 */
public class TestThreadPool {


    @SneakyThrows
    public static void main( String[] args ) {
        ThreadPoolExecutor executorService = new ThreadPoolExecutor( 1,2,10, TimeUnit.MINUTES,new ArrayBlockingQueue<>( 5 ) );
        int i = 0;

        executorService.execute( new Runnable() {
            @Override
            public void run() {
                System.out.println( Thread.currentThread().getId() );
            }
        });

        Thread.sleep( 20000 );

        System.out.println("sleep out...");
        while ( i < 7 ){
            int finalI = i;
            executorService.execute( new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
//                    if ( finalI == 0 ){
//                    Thread.sleep( 2000 );
//                    }
                    System.out.println( Thread.currentThread().getId() );
                }
            });
            i++;
        }

        executorService.shutdown();
    }
}
