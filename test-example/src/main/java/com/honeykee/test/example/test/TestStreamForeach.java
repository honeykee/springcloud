package com.honeykee.test.example.test;

import java.util.*;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-10-12 18:03
 * @since JDK 1.8
 */

public class TestStreamForeach {
    public static void main( String[] args ) throws Exception{
        Map<Integer, String> map = new HashMap(  );
        map.put( 0, "A" );
        map.put( 1, "B" );
        map.put( 2, "C" );
        map.put( 3, "D" );
        map.put( 4, "E" );
        //实验结果不是异步执行的
        map.forEach( (var ,str ) ->{
            if ( var == 1 ){
                try {
                    Thread.sleep( 1 * 1000 );
                } catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
                try{
                    var = 10 /0 ;
                }catch ( Exception e ){
                    System.out.println( e.getMessage() );
                }
            }
            System.out.println(str);
        });

        System.out.println("******************************");
        //list测试
        List<Integer> list = new ArrayList<>(  );
        for ( int i = 0 ; i< 100000000; i++ ){
            list.add( i );
        }

        Long start = System.currentTimeMillis();

        list.stream().forEach( var->{
//            if ( var == 1 ){
//                try {
//                    Thread.sleep( 1 * 1000 );
//                } catch ( InterruptedException e ) {
//                    e.printStackTrace();
//                }
//                var = 10 /0 ;
//            }
//            System.out.println( var );
            Integer a = var;
        } );
        Long end = System.currentTimeMillis();
        System.out.println( "list.stream.forEach time1: " + (end - start) );


        //parallelStream.foreach
        start = System.currentTimeMillis();
        list.parallelStream().forEach( var->{
//            if ( var == 1 ){
//            try {
//                Thread.sleep( 1 * 1000 );
//            } catch ( InterruptedException e ) {
//                e.printStackTrace();
//            }
//                var = 10 /0 ;
//            }
//            System.out.println( var );
            Integer a = var;
        } );
        end = System.currentTimeMillis();
        System.out.println( "list.parallelStream.foreach time2: " + (end - start) );

        //parallelStream.foreach
        start = System.currentTimeMillis();
        list.forEach( var->{
//            if ( var == 1 ){
//            try {
//                Thread.sleep( 1 * 1000 );
//            } catch ( InterruptedException e ) {
//                e.printStackTrace();
//            }
//                var = 10 /0 ;
//            }
//            System.out.println( var );
            Integer a = var;
        } );
        end = System.currentTimeMillis();
        System.out.println( "list.foreach time2: " + (end - start) );


        start = System.currentTimeMillis();
        for ( Integer var : list ){
//            if ( var == 1 ){
//                try {
//                    Thread.sleep( 1 * 1000 );
//                } catch ( InterruptedException e ) {
//                    e.printStackTrace();
//                }
//                var = 10 /0 ;
//            }
//            System.out.println( var );
            Integer a = var;
        }

        end = System.currentTimeMillis();
        System.out.println( "增强foreach time3: " + ( end - start) );



        start = System.currentTimeMillis();
        for ( int i= 0; i < list.size() ; i ++  ){
//            if ( var == 1 ){
//            try {
//                Thread.sleep( 1 * 1000 );
//            } catch ( InterruptedException e ) {
//                e.printStackTrace();
//            }
//                var = 10 /0 ;
//            }
            Integer integer = list.get( i );
//            System.out.println( list.get( i ) );
        }

        end = System.currentTimeMillis();
        System.out.println( "普通for time4: " + ( end - start) );












    }
}
