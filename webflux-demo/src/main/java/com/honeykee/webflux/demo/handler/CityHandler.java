package com.honeykee.webflux.demo.handler;

import com.honeykee.webflux.demo.entity.City;
import com.honeykee.webflux.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/4/14 2:20 下午
 * @since JDK 1.8
 */

@Service
public class CityHandler {

    @Autowired
    private CityRepository cityRepository;

    public Mono<Long> saveCity( City  city){
        return Mono.create( citySink-> citySink.success(cityRepository.saveCity( city )) );
    }

    public Mono<City> findCityById(Long id){
        return Mono.justOrEmpty( cityRepository.findCityById( id ) );
    }

    public Flux<City> findAllCity(){
        return Flux.fromIterable( cityRepository.findAll() );
    }

    public Mono<Long> modifyCity(City city){
        return Mono.create( citySink -> citySink.success( cityRepository.updateCity( city ) ) );
    }

    public Mono<Long> deleteCityById(Long id){
        return Mono.create( longMonoSink -> longMonoSink.success( cityRepository.deleteCity( id ) ) );
    }

}
