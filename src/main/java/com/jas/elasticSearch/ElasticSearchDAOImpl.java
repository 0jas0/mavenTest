package com.jas.elasticSearch;

import com.alibaba.fastjson.JSON;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.cluster.Health;
import io.searchbox.cluster.NodesInfo;
import io.searchbox.cluster.NodesStats;
import io.searchbox.core.*;
import io.searchbox.indices.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public JestResult closeIndex(String indexName) {
        CloseIndex closeIndex = new CloseIndex.Builder(indexName).build();
        return execute(closeIndex, "关闭索引失败，indexName:" + indexName);
    }

    @Override
    public JestResult optimizeIndex() {
        Optimize optimize = new Optimize.Builder().build();
        return execute(optimize, "优化索引失败");
    }

    @Override
    public JestResult flushIndex() {
        Flush flush = new Flush.Builder().build();
        return execute(flush, "优化索引失败");
    }

    @Override
    public JestResult indicsExists(String indexName) {
        IndicesExists indicesExists = new IndicesExists.Builder(indexName).build();
        return execute(indicesExists, "判断索引是否存在失败，indexName:" + indexName);
    }

    @Override
    public JestResult nodesInfo() {
        NodesInfo info = new NodesInfo.Builder().build();
        return execute(info, "获取节点信息失败");
    }

    @Override
    public JestResult health() {
        Health health = new Health.Builder().build();
        return execute(health, "获取健康信息失败");
    }

    @Override
    public JestResult nodesStats() {
        NodesStats nodesStats = new NodesStats.Builder().build();
        return execute(nodesStats, "获取节点的状态信息");
    }

    @Override
    public <T> JestResult updateDocument(T o, String index, String type, String id) {
        Map<String, T> map = new HashMap<>();
        map.put("doc", o);
        String string = JSON.toJSONString(map);
        Update update = new Update.Builder(string).index(index).type(type).id(id).build();
        return execute(update,"更新文档信息失败");
    }

    @Override
    public JestResult deleteDocument(String index, String type, String id) {
        Delete delete = new Delete.Builder(id).index(index).type(type).build();
        StringBuilder stringBuilder = new StringBuilder("通过主键方式删除文档失败");
        stringBuilder.append(",index:" + index);
        stringBuilder.append(",type:" + type);
        stringBuilder.append(",id:" + id);
        return execute(delete, stringBuilder.toString());
    }

    @Override
    public JestResult deleteDocumentByQuery(String index, String type, String params) {
        DeleteByQuery deleteByQuery = new DeleteByQuery.Builder(params).addIndex(index).addType(type).build();
        StringBuilder stringBuilder = new StringBuilder("通过添加删除文档失败");
        stringBuilder.append(",index:" + index);
        stringBuilder.append(",type:" + type);
        stringBuilder.append(",params:" + params);
        return execute(deleteByQuery, stringBuilder.toString());
    }

    @Override
    public JestResult getDocument(String index, String type, String id) {
        Get get = new Get.Builder(index,id).type(type).build();
        StringBuilder stringBuilder = new StringBuilder("通过id获取文档失败");
        stringBuilder.append(",index:" + index);
        stringBuilder.append(",type:" + type);
        stringBuilder.append(",id:" + id);
        return execute(get, stringBuilder.toString());
    }

    @Override
    public JestResult createSearch(String keyword, String index, String type, String... fields) {
        if (fields == null || fields.length == 0){
            return null;
        }
        BoolQueryBuilder booleanQueryBuilder = QueryBuilders.boolQuery();
        for (String field : fields){
            BoolQueryBuilder innerQuery = QueryBuilders.boolQuery();
            innerQuery.filter(QueryBuilders.termQuery(field, keyword));
            booleanQueryBuilder.should(innerQuery);
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(booleanQueryBuilder);
        Search search = new Search.Builder(searchSourceBuilder.toString()).build();
        StringBuilder stringBuilder = new StringBuilder("模糊查询失败");
        stringBuilder.append(",keyword:" + keyword);
        stringBuilder.append(",index:" + index);
        stringBuilder.append(",type:" + type);
        stringBuilder.append(",fields:" + Arrays.toString(fields));
        return execute(search, stringBuilder.toString());
    }

    @Override
    public <T> JestResult createIndex(T o, String indexName, String type, String id) {
        Index index = new Index.Builder(o).index(indexName).type(type).id(id).build();
        StringBuilder stringBuilder = new StringBuilder("创建索引失败");
        stringBuilder.append(",index:" + indexName);
        stringBuilder.append(",type:" + type);
        stringBuilder.append(",id:" + id);
        stringBuilder.append(",object:" + JSON.toJSONString(o));
        return execute(index, stringBuilder.toString());
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
