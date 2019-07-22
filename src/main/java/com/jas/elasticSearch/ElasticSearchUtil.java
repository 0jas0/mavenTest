package com.jas.elasticSearch;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

/**
 * <pre>
 * Desc  : elasticSearch 工具类
 * Author: juas
 * Date  ：2019-07-22 10:30
 * </pre>
 */
public class ElasticSearchUtil {

    private volatile static JestClientFactory jestClientFactory;

    private static void initConnection(){
        if (jestClientFactory != null){
            return;
        }
        synchronized (ElasticSearchUtil.class){
            if (jestClientFactory == null){
                jestClientFactory = new JestClientFactory();
                HttpClientConfig httpClientConfig = new HttpClientConfig
                        .Builder("http://localhost:9200")
                        .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                        .multiThreaded(true)
                        .defaultMaxTotalConnectionPerRoute(100)
                        .maxTotalConnection(100)
                        .readTimeout(1000)
                        .build();
                jestClientFactory.setHttpClientConfig(httpClientConfig);
            }
        }
    }

    public static JestClient getJestClient(){
        initConnection();
        return jestClientFactory.getObject();
    }

}
