package com.honeykee.webflux.demo.repository;

import com.honeykee.webflux.demo.entity.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/4/14 2:24 下午
 * @since JDK 1.8
 */

@Repository
public class CityRepository {

    private ConcurrentHashMap<Long , City > repository = new ConcurrentHashMap<>();

    private final static AtomicLong idGenerator = new AtomicLong();

    public Long saveCity(City city){
        Long id = idGenerator.incrementAndGet();
        city.setId( id );
        repository.put( id, city );
        return id;
    }

    public Collection< City> findAll(){
        return repository.values();
    }

    public City findCityById(Long id){
        return repository.get( id );
    }

    public Long updateCity(City city){
        repository.put( city.getId(), city );
        return city.getId();
    }

    public Long deleteCity(Long id){
        repository.remove( id );
        return id;
    }


}
