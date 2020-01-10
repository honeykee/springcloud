package com.honeykee.test.example.test;

import com.honeykee.test.example.model.Person;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-12-18 15:02
 * @since JDK 1.8
 */
public class TestListDistinctByObjectProperty {

    public static void main( String[] args ) {

        List< String > list1 = Arrays.asList( "a", "b", "c", "a", new String( "c" ) );
        list1 = list1.stream().distinct().collect( Collectors.toList() );
        System.out.println( list1 );

        List< Integer > list2 = Arrays.asList( 1, 2, 3, 1, new Integer( 2 ) );
        list2 = list2.stream().distinct().collect( Collectors.toList() );
        System.out.println( list2 );

        List< Person > personList = new ArrayList<>();
        personList.add( new Person( 11, "AA" ) );
        personList.add( new Person( 12, "BB" ) );
        personList.add( new Person( 13, "AA" ) );
        personList.add( new Person( 11, "CC" ) );

        System.out.println( personList );


        //方法一，简单实惠（推荐）
//        personList = personList.stream().collect( Collectors.collectingAndThen(
//                Collectors.toCollection(() -> new TreeSet<>( Comparator.comparing( Person::getName ) ) ), ArrayList::new ) );

        try {
            //方法二，略微笨重，不过很通用
            personList =  removeDuplicatePlan( personList , Person.class.getDeclaredMethod( "getAge") , Integer.class);

        } catch ( NoSuchMethodException e ) {
            e.printStackTrace();
        }

        System.out.println( personList );
    }



    private static <T> List<T>  removeDuplicatePlan( List<T> planList , Method method , Class clazz) {
//        Set<Person> set = new TreeSet<>( Comparator.comparing( Person::getName ) );

        Set<T> set = new TreeSet<>(new Comparator<T>() {
            @Override
            public int compare(T obj1, T obj2) {
                // 字符串则按照asicc码升序排列
                Object invoke1 = null;
                Object invoke2 = null;
                try {
                    invoke1 = method.invoke( obj1 );
                    invoke2 = method.invoke( obj2 );
                } catch ( Exception e ) {
                }

                if ( String.class == clazz ){
                    int i = ( ( String ) invoke1 ).compareTo( ( String ) invoke2 );
                    return i;
                }
                if ( Integer.class == clazz ){
                    return ( (Integer)invoke1 ).compareTo( (Integer ) invoke2 );
                }
                // 返回0表示存在相同的，否则为不存在相同的
                return 1;
            }
        });

        set.addAll(planList);
        return new ArrayList<>(set);
    }
}
