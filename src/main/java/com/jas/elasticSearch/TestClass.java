package com.jas.elasticSearch;

import io.searchbox.client.JestResult;
import org.junit.Before;
import org.junit.Test;

/**
 * <pre>
 * Desc  :
 * Author: juas
 * Date  ：2019-07-22 14:33
 * </pre>
 */
public class TestClass {

    public class User{
        private String userId;
        private String userName;
        private String department;

        public User(String userId, String userName, String department) {
            this.userId = userId;
            this.userName = userName;
            this.department = department;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }

    private IElasticSearchDAO elasticSearchDAO;

    @Before
    public void init(){
        elasticSearchDAO = new ElasticSearchDAOImpl();
    }

    @Test
    public void deleteIndexTest(){
        JestResult jestResult = elasticSearchDAO.deleteIndex("index_test");
        System.out.println(jestResult);
    }

    @Test
    public void createIndexTest(){
        User user = new User("3", "zcc", "crm");
        elasticSearchDAO.createIndex(user, "index_test", "type_test", user.getUserId());
    }

    @Test
    public void indicsExistsTest(){
        JestResult result = elasticSearchDAO.indicsExists("index_test");
        System.out.println(result);
    }

    @Test
    public void getDocumentTest(){
        JestResult document = elasticSearchDAO.getDocument("index_test", "type_test", "3");
        User user = document.getSourceAsObject(User.class);
        System.out.println(user);
    }

    @Test
    public void updateDocumentTest(){
        User user = new User("2", "bhz", "crm");
        JestResult jestResult = elasticSearchDAO.updateDocument(user, "index_test", "type_test", "2");
        System.out.println(jestResult);
    }

}
