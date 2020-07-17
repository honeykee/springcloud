package com.honeykee.test.example.test.nio.future.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.*;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/28 3:09 下午
 * @since JDK 1.8
 */
public class GuavaFutureDemo {
    static class HotWaterJob implements Callable<Boolean> {

        @Override
        public Boolean call() {
            try {
                System.out.println("开始🔥烧水");
                Thread.sleep( 3000L );
                System.out.println("水烧🔥开了");
            } catch ( InterruptedException e ) {
                System.out.println("🔥烧水失败");
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    static class WashJob implements Callable<Boolean>{

        @Override
        public Boolean call() {
            try {
                System.out.println("开始洗茶壶");
                Thread.sleep( 3000L );
                System.out.println("茶壶洗完");
            } catch ( InterruptedException e ) {
                System.out.println("洗茶壶失败");
                e.printStackTrace();
                return false;
            }
            return true;
        }
    }

    public static void drinkTea( Boolean washStatus, Boolean hotWaterStatus ){
        if ( washStatus && hotWaterStatus ){
            System.out.println("开始泡茶喝");
        }else if ( !washStatus ){
            System.out.println("清洗茶壶失败");
        }else if( !hotWaterStatus ){
            System.out.println("烧水失败");
        }else {
            System.out.println("---");
        }
    }

    static class ReadBookJob implements Runnable{
        //洗茶壶状态
        Boolean washStatus = false;
        //烧水状态
        Boolean hotWaterStatus = false;
        @Override
        public void run() {
            while( true ){
                try {
                    Thread.sleep( 1000L );
                    System.out.println("读书中...");
                    if ( washStatus && hotWaterStatus ){
                        drinkTea( washStatus, hotWaterStatus);
                    }
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main( String[] args ) {
        ReadBookJob readBookJob = new ReadBookJob();
        Thread mainThread = new Thread( readBookJob );
        mainThread.setName( "--main thread" );
        mainThread.start();
        Callable<Boolean> hotWaterJob = new HotWaterJob();

        Callable<Boolean> washJob = new WashJob();
        ExecutorService executorService = Executors.newFixedThreadPool( 2 );
        ListeningExecutorService pool = MoreExecutors.listeningDecorator( executorService );
        ListenableFuture< Boolean > hotWaterFuture = pool.submit( hotWaterJob );
        Futures.addCallback( hotWaterFuture, new FutureCallback<Boolean>(){
            @Override
            public void onSuccess( Boolean result ) {
                if ( result ){
                    readBookJob.hotWaterStatus = result;
                }
            }
            @Override
            public void onFailure( Throwable t ) {
                readBookJob.hotWaterStatus = false;
                System.out.println("烧水失败");
            }
        } );

        ListenableFuture< Boolean > washFuture = pool.submit( washJob );
        Futures.addCallback( washFuture, new FutureCallback< Boolean >() {
            @Override
            public void onSuccess( Boolean result ) {
                if ( result ){
                    readBookJob.washStatus = true;
                }
            }

            @Override
            public void onFailure( Throwable t ) {
                readBookJob.washStatus = false;
                System.out.println("洗茶壶失败");
            }
        } );

    }
}
