package com.jas.elasticSearch;

import io.searchbox.client.JestResult;

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
}
