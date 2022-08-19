package com.ujiuye.service.impl;

import com.ujiuye.bean.User;
import com.ujiuye.dao.UserDao;
import com.ujiuye.dao.impl.UserDaoImpl;
import com.ujiuye.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public boolean saveUser(User user) {

        // 1. 处理注册业务
        UserDao dao = new UserDaoImpl();
        int row = dao.insertUser(user);
        return row > 0?true:false;
    }
}
