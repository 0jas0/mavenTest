package com.jas.elasticSearch;

import io.searchbox.client.JestResult;

import java.util.Map;

/**
 * <pre>
 * Desc  : elasticSearch: jest client 常用操作api
 * Author: juas
 * Date  ：2019-07-22 14:08
 * </pre>
 */
public interface IElasticSearchDAO {

    /**
      * @Description: 删除索引
      * @Author: juas
      * @Date: 2019/7/22 2:10 PM
      * @Param indexName:
      * @return: io.searchbox.client.JestResult
    */
    public JestResult deleteIndex(String indexName);

    /**
      * @Description: 清空缓存
      * @Author: juas
      * @Date: 2019/7/22 2:17 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult clearCache();


    /**
      * @Description: 关闭索引
      * @Author: juas
      * @Date: 2019/7/22 7:10 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult closeIndex(String indexName);


    /**
      * @Description: 优化索引
      * @Author: juas
      * @Date: 2019/7/22 7:11 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult optimizeIndex();


    /**
      * @Description: 刷新索引
      * @Author: juas
      * @Date: 2019/7/22 7:12 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult flushIndex();


    /**
      * @Description: 判断索引是否存在
      * @Author: juas
      * @Date: 2019/7/22 7:13 PM
     *  @parm indexName
      * @return: io.searchbox.client.JestResult
    */
    public JestResult indicsExists(String indexName);


    /***
      * @Description: 查看节点信息
      * @Author: juas
      * @Date: 2019/7/22 7:14 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult nodesInfo();


    /**
      * @Description: 查看集群健康情况
      * @Author: juas
      * @Date: 2019/7/22 7:14 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult health();


    /**
      * @Description: 查看节点状态
      * @Author: juas
      * @Date: 2019/7/22 7:15 PM
      * @return: io.searchbox.client.JestResult
    */
    public JestResult nodesStats();


    /**
      * @Description: 更新document
      * @Author: juas
      * @Date: 2019/7/22 7:16 PM
      * @Param doc: 更新后的信息
      * @Param index: 索引库
      * @Param type: 对象类型
      * @Param id: 文档唯一表示
      * @return: io.searchbox.client.JestResult
    */
    public <T> JestResult updateDocument(T o, String index, String type, String id);


    /**
      * @Description: 删除一个文档通过id
      * @Author: juas
      * @Date: 2019/7/22 7:18 PM
      * @Param index:
      * @Param type:
      * @Param id:
      * @return: io.searchbox.client.JestResult
    */
    public JestResult deleteDocument(String index, String type, String id);


    /**
      * @Description: 通过条件删除文档
      * @Author: juas
      * @Date: 2019/7/22 7:20 PM
      * @Param index:
      * @Param type:
      * @Param params:
      * @return: io.searchbox.client.JestResult
    */
    public JestResult deleteDocumentByQuery(String index, String type, Map<String, String> params);


    /**
      * @Description: 获取一个文档
      * @Author: juas
      * @Date: 2019/7/22 7:22 PM
      * @Param index:
      * @Param type:
      * @Param id:
      * @return: io.searchbox.client.JestResult
    */
    public JestResult getDocument(String index, String type, String id);


    /**
      * @Description: 搜索关键词
      * @Author: juas
      * @Date: 2019/7/22 7:33 PM
      * @Param keyword: 搜索的关键词
      * @Param index:
      * @Param type:
      * @Param fields: 要搜索的字段
      * @return: io.searchbox.client.JestResult
    */
    public JestResult createSearch(String keyword, String index, String type, String... fields);


    /**
      * @Description: 创建一个索引
      * @Author: juas
      * @Date: 2019/7/22 7:39 PM
      * @Param o:
      * @Param index:
      * @Param type:
      * @Param id:
      * @return: io.searchbox.client.JestResult
    */
    public <T> JestResult createIndex(T o , String index , String type, String id);
}
