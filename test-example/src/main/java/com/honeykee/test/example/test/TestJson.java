package com.honeykee.test.example.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-10-14 17:16
 * @since JDK 1.8
 */
public class TestJson {

    public static void main( String[] args ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValueAsString( new Object() );


        JsonObject jsonObject = new JsonObject();
        jsonObject.getAsJsonArray( "" );


    }
}
