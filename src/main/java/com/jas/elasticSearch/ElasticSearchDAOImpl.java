package com.jas.elasticSearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.ClearCache;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.action.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * <pre>
 * Desc  : elasticSearch: jest client 常用操作api
 * Author: juas
 * Date  ：2019-07-22 14:19
 * </pre>
 */
public class ElasticSearchDAOImpl implements IElasticSearchDAO{

    private JestClient jestClient = ElasticSearchUtil.getJestClient();

    private Logger log = LoggerFactory.getLogger(ElasticSearchDAOImpl.class);

    @Override
    public JestResult deleteIndex(String indexName) {
        DeleteIndex deleteIndex = new DeleteIndex.Builder(indexName).build();
        return execute(deleteIndex, "删除索引失败,indexName:" + indexName);
    }

    @Override
    public JestResult clearCache() {
        ClearCache clearCache = new ClearCache.Builder().build();
        return execute(clearCache, "清空缓存失败");
    }

    private JestResult execute(Action clientRequest, String msg){
        try {
            JestResult jestResult = jestClient.execute(clientRequest);
            return jestResult;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(msg,e);
            return null;
        }
    }
}
