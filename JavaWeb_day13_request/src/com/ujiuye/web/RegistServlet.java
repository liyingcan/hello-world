package com.ujiuye.web;

import com.ujiuye.bean.User;
import com.ujiuye.service.UserService;
import com.ujiuye.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

@WebServlet(name = "RegistServlet",urlPatterns = "/regist")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求的编码
        request.setCharacterEncoding("utf-8");

        // 1. 获取表单数据
        Map<String, String[]> map = request.getParameterMap();
        String[] hobbies = map.get("hobby");
        String hobby = Arrays.toString(hobbies);
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setHobby(hobby);
        System.out.println(user);

        // 调用业务层 处理业务
        UserService service = new UserServiceImpl();
        boolean b = service.saveUser(user);

        if(b){
            // 注册成功  跳转登录页面
            request.getRequestDispatcher("WEB-INF/login.html").forward(request,response);
            // response.sendRedirect("WEB-INF/login.html");
        }else{
            // 注册失败  回到注册页面
            request.getRequestDispatcher("regist01.html").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
