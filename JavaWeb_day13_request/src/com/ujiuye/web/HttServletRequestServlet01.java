package com.ujiuye.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "HttServletRequestServlet01", urlPatterns = "/request01")
public class HttServletRequestServlet01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取请求参数
        // 1.1 获取请求行参数
        String method = request.getMethod();// 获取请求方式
        // System.out.println(method);

        String scheme = request.getScheme();
        System.out.println("协议："+scheme);

        int port = request.getServerPort();
        System.out.println("端口号："+port);

        String contextPath = request.getContextPath();
        System.out.println("项目名:"+contextPath);

        StringBuffer url = request.getRequestURL();
        System.out.println("url："+url);

        String uri = request.getRequestURI();
        System.out.println("uri："+uri);

        // 1.2 获取请求头参数
        // 根据一个name 获取对应的一个值
        String accept = request.getHeader("Accept");
//        System.out.println(accept);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            // 获取当前元素
            String name = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(name);
            String value = null;
            while (headers.hasMoreElements()){
                 value += headers.nextElement();
            }
//            System.out.println(name+":"+value);
        }

        // 1.3 请求正文信息  *****
        // 1.3.1 根据一个key 获取一个value
        String username = request.getParameter("username");
        System.out.println(username);
        System.out.println("----------------------------------------");
        // 1.3.2 根据一个key 获取多个value
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println(Arrays.asList(hobbies));
        System.out.println("----------------------------------------");
        // 1.3.3 获取所有的key
        Enumeration<String> names = request.getParameterNames();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            System.out.println(name+" : "+Arrays.asList(values));
        }
        System.out.println("----------------------------------------");
        // 1.3.4 获取所有的key和对应的value
        Map<String, String[]> map = request.getParameterMap();

        Set<Map.Entry<String, String[]>> entries = map.entrySet();

        for (Map.Entry<String, String[]> entry:entries) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            System.out.println(key+" : "+Arrays.asList(value));
        }



    }
}
