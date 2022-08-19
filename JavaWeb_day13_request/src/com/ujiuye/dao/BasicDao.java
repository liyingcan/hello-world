package com.ujiuye.dao;

import com.ujiuye.utils.JDBCUtils2;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/*封装增删改查的方法
*   1. 增删改  ---- update()
*   2. 单查   ----  querySingle()
*   3. 多查   ----  queryMore()
*   4. 查询分组函数  ---- scalar()
* */
public class BasicDao<T> {
    QueryRunner qr;
    {
        qr = new QueryRunner();
    }
    // 增删改
    public int update(String sql, Object...params){
        try {
            return qr.update(JDBCUtils2.getConnection(), sql , params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils2.release();
        }
    }

    // 单查
    public T querySingle(String sql, Class<T> clazz, Object...params){
        try {
            return qr.query(JDBCUtils2.getConnection(), sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils2.release();
        }
    }

    // 多查
    public List<T> queryMore(String sql, Class<T> clazz, Object...params){
        try {
            return qr.query(JDBCUtils2.getConnection(), sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils2.release();
        }
    }

    // 查询分组函数
    public int scalar(String sql, Object...params){
        try {
            return ((Long)qr.query(JDBCUtils2.getConnection(), sql, new ScalarHandler<>(), params)).intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils2.release();
        }
    }


}
