package com.ujiuye.dao.impl;

import com.ujiuye.bean.User;
import com.ujiuye.dao.BasicDao;
import com.ujiuye.dao.UserDao;

public class UserDaoImpl extends BasicDao<User> implements UserDao {
    @Override
    public int insertUser(User user) {
        String sql = "insert into userss(username,pwd,hobby,sex,pro) values(?,?,?,?,?)";
        return update(sql, user.getUsername(), user.getPwd()
                ,user.getHobby(),user.getSex(),user.getPro());
    }
}
