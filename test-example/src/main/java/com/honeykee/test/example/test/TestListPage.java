package com.honeykee.test.example.test;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * List分页
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-10-25 15:38
 * @since JDK 1.8
 */
public class TestListPage {
    public static void main( String[] args ) {

        StopWatch stopWatch = new StopWatch( "taskName-" );
        stopWatch.start();

        List< Integer > list = new ArrayList();

        LinkedBlockingQueue queue = new LinkedBlockingQueue();

        for(int i = 0 ; i < 10_000_000; i++){
            list.add( i ) ;
        }
        queue.addAll( list );

        Integer pageSize = 1000;
        List<Integer> subList = new ArrayList<>(  );
        while ( queue.drainTo(subList, pageSize) > 0 ){
//            System.out.println("first->"+ subList.get( 0 ) );
//            System.out.println("last ->"+ subList.get( subList.size() - 1 ));
            //必须清零，否则原来的数据都在
            subList.clear();
        }


        stopWatch.stop();

        String id = stopWatch.getId();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        System.out.println("id->" + id );
        System.out.println("time->"+totalTimeMillis);


    }


    public static List<List> splitList(List list, int pageSize)
    {

        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;

        List<List> listArray = new ArrayList<>();
        for (int i = 0; i < page; i++)
        {
            List subList = new ArrayList();
            for (int j = 0; j < listSize; j++)
            {
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
                if (pageIndex == (i + 1))
                {
                    subList.add(list.get(j));
                }
                if ((j + 1) == ((j + 1) * pageSize))
                {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }





}
