package com.fuyao.newstarblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewstarblogApplicationTests {

    @Autowired
    private DataSource dataSource;

//    测试Druid数据源
    /*@Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);  //com.alibaba.druid.proxy.jdbc.ConnectionProxyImpl@ac417a2
    }*/

}
