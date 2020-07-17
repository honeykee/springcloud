package com.honeykee.test.example.test.nio.future;


import com.honeykee.test.example.test.nio.future.guava.GuavaFutureDemo;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/6/28 2:12 下午
 * @since JDK 1.8
 */
public class JavaFutureDemo {

    static class HotWaterJob implements Callable<Boolean>{

        @Override
        public Boolean call() {
            try {
                System.out.println("开始🔥烧水");
                Thread.sleep( 30000L );
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
                Thread.sleep( 30000L );
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


    static class ReadStatus implements Runnable{
        Boolean status ;
        Callable<Boolean> job;

        ReadStatus(Boolean status,Callable<Boolean> job  ){
            this.status = status;
            this.job = job;
        }

        @Override
        public void run() {
            while ( !status ){
                FutureTask<Boolean> hotTask = new FutureTask<>( job );
                Thread hotThread = new Thread( hotTask );
                hotThread.start();
                try {
                    status = hotTask.get();
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                } catch ( ExecutionException e ) {
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

//        Callable<Boolean> hotJob = new HotWaterJob();
//        FutureTask<Boolean> hotTask = new FutureTask<>( hotJob );
//        Callable<Boolean> washJob = new WashJob();
//        FutureTask<Boolean> washTask = new FutureTask<>( washJob );
//        Thread hotThread = new Thread( hotTask, "--烧水" );
//        Thread washThread = new Thread( washTask, "--清洗茶壶" );
//        hotThread.start();
//        washThread.start();

//        ReadStatus readStatus1 = new ReadStatus( false, new HotWaterJob() );
        Runnable hotWaterTask = new Runnable(){
            @Override
            public void run() {
                while ( !readBookJob.hotWaterStatus ){
                    FutureTask<Boolean> hotTask = new FutureTask<>(  new HotWaterJob()  );
                    Thread hotThread = new Thread( hotTask );
                    hotThread.start();
                    try {
                        System.out.println( LocalDateTime.now() + ", hotStatus:" );
                        Boolean hotStatus = hotTask.get();
                        System.out.println( LocalDateTime.now() + ", hotStatus:" + hotStatus );
                        readBookJob.hotWaterStatus = hotStatus;
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    } catch ( ExecutionException e ) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(hotWaterTask);
        thread.start();

        Runnable washTask = new Runnable() {
            @Override
            public void run() {
                while ( !readBookJob.washStatus ){
                    FutureTask<Boolean> washJob = new FutureTask<>(  new WashJob()  );
                    Thread hotThread = new Thread( washJob );
                    hotThread.start();
                    try {
                        System.out.println(  LocalDateTime.now() + ", washStatus:");
                        Boolean washStatus = washJob.get();
                        System.out.println(  LocalDateTime.now() + ", washStatus:"+ washStatus);
                        readBookJob.washStatus = washStatus;
                    } catch ( InterruptedException e ) {
                        e.printStackTrace();
                    } catch ( ExecutionException e ) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread2 = new Thread( washTask );
        thread2.start();

        System.out.println("====================");
//        Thread.currentThread().setName( "--主线程" );
//        try {
//            Boolean washStatus = hotTask.get();
//            Boolean hotStatus = hotTask.get();
//            drinkTea(washStatus, hotStatus);
//        } catch ( InterruptedException e ) {
//            e.printStackTrace();
//        } catch ( ExecutionException e ) {
//            e.printStackTrace();
//        }
//        System.out.println("结束喝茶");

    }
}
