package com.honeykee.test.example.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-10-14 16:43
 * @since JDK 1.8
 */
public class TestHttpClient {

    public static void main( String[] args ) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet( URI.create("http://www.baidu.com/s?wd=java") );

        CloseableHttpResponse response = httpClient.execute( httpGet );

        response.getStatusLine().getStatusCode();

        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        entity.isStreaming();
        entity.writeTo( new FileOutputStream( new File( "" ) ) );

        String string = EntityUtils.toString(entity, "utf-8");

        //5.关闭资源
        response.close();
        httpClient.close();

        URL url = new URL( "www.baidu.com" );
        InputStream inputStream = url.openStream();

        URLConnection urlConnection = url.openConnection();
        InputStream inputStream1 = urlConnection.getInputStream();


        int FiberNumber = 1_000_000;
    }
}
