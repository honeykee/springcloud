package com.honeykee.test.example.controller;

import com.honeykee.test.example.model.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020-02-26 17:42
 * @since JDK 1.8
 */

@Api(value = "aoppp", tags = "tagsss")
@Slf4j
@Controller
@ResponseBody
@RequestMapping(value="/person")
public class PersonController {

    @ApiOperation( value = "zzzz")
    @PostMapping(value="/save")
    public Person getPerson( @RequestBody Person person){
        person.setAge( 100 );
        return person;
    }
}
