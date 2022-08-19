package com.ujiuye.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*封装工具类实现连接池复用*/
public class JDBCUtils2 {
    // 1. 声明ThreadLocal类
    private static ThreadLocal<Connection> tl  = new ThreadLocal<>();
    private static DataSource dataSource;
    // 创建连接池 放在静态代码块中
    static{
        Properties properties = new Properties();
        InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader().getResourceAsStream("druid.properties");

        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 从池子中获取连接
    public static Connection getConnection() throws SQLException {
        Connection conn = tl.get();
        if(conn==null){
            // 1. 从池子中获取连接  绑定到ThreadLocal
            tl.set(dataSource.getConnection());
            conn = tl.get();
        }
        return conn;
    }

    // 回收连接的方法
    public static void release(){
        // 先获取绑定的连接对象  为了回收
        Connection connection = tl.get();
        // 将连接对象和ThreadLocal解除
        tl.remove();
        // 回收到池子中
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
