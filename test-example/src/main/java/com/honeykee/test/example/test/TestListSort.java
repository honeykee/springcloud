package com.honeykee.test.example.test;

import com.honeykee.test.example.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-10-29 15:33
 * @since JDK 1.8
 */
public class TestListSort {

    public static void main( String[] args ) {
        long start = System.currentTimeMillis();

        List< Person > list = new ArrayList<>();
        for ( int i = 100_000_000 ;i > 0; i --){
            list.add( new Person( i, "nam-1" ));
        }


        System.out.println("first->" + list.get( 0 ));

//        list.sort( Comparator.comparing( Person::getAge ) );
//        list = list.stream().sorted( Comparator.comparing( Person::getAge ) ).collect( Collectors.toList() );
        Collections.sort( list, Comparator.comparing( Person::getAge ) );

        System.out.println("first->" + list.get( 0 ));
        long end = System.currentTimeMillis();
        System.out.println( "spend->" + (end - start));
//        System.out.println(list.get( 0 ));

    }
}
