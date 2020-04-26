package com.honeykee.webflux.demo.controller;

import com.honeykee.webflux.demo.entity.City;
import com.honeykee.webflux.demo.handler.CityHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020/4/14 2:51 下午
 * @since JDK 1.8
 */
@Api( tags = "city 管理")
@RestController
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityHandler cityHandler;

    @GetMapping(value = "/{id}")
    @ApiOperation( value = "查询单个city")
    public Mono< City > findCityById( @PathVariable("id") Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping()
    @ApiOperation( value = "查询所有city")
    public Flux<City> findAllCity() {
        return cityHandler.findAllCity();
    }
    @PostMapping()
    @ApiOperation( value = "保存city")
    public Mono<Long> saveCity( @RequestBody City city) {
        return cityHandler.saveCity(city);
    }

    @PutMapping()
    @ApiOperation( value = "修改city")
    public Mono<Long> modifyCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation( value = "删除city")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCityById(id);
    }


}
