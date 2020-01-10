package com.honeykee.config.client.config;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-09-29 17:02
 * @since JDK 1.8
 */
public class SingleTon {
    private static SingleTon ourInstance = new SingleTon();

    public static SingleTon getInstance() {
        return ourInstance;
    }

    private SingleTon() {
    }
}
