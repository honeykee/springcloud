package com.honeykee.test.example.test;

import com.honeykee.test.example.model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-11 15:11
 * @since JDK 1.8
 */
public class TestListStream {

    public static void main( String[] args ) {
        List< Person > list = new ArrayList<>();
        list.add( new Person( 12, "AAA" ) );
        list.add( new Person( 10, "BBB" ) );
        list.add( new Person( 8, "CCC" ) );
        list.add( new Person( 16, "DDD" ) );
        list.add( new Person( 12, "EEE" ) );
        System.out.println(list );



        list.sort( ( o1, o2 ) -> o2.getAge() - o1.getAge() );


        list.forEach( person -> {
            System.out.println(person);
            try {
                Thread.sleep( 1000 );
            } catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        } );


    }
}
