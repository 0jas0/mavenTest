package com.jas.elasticSearch;

import io.searchbox.client.JestResult;
import org.junit.Before;

/**
 * <pre>
 * Desc  :
 * Author: juas
 * Date  ：2019-07-22 14:33
 * </pre>
 */
public class Test {

    private IElasticSearchDAO elasticSearchDAO;

    @Before
    public void init(){
        elasticSearchDAO = new ElasticSearchDAOImpl();
    }

    @org.junit.Test
    public void deleteIndexTest(){
        JestResult jestResult = elasticSearchDAO.deleteIndex("index_test");
        System.out.println(jestResult);
    }
}
