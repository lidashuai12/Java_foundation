package com.cqupt.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据uri最后面的请求方法名，分发请求方法
        //1.获取uri
        String uri = req.getRequestURI();
        //2.获取其方法名
        int index = uri.lastIndexOf('/');  //uri形式为：brand_demo_new/brand/selectAll
        String methodName = uri.substring(index+1); //单纯subString截出来的是带'/'的，所以索引要+1
        //2.1获取 BrandServlet/UserServlet 字节码对象 .class(回顾反射)
            //谁（this所在的方法）调用我，我（this）代表谁
            // 这里this是由BaseServlet的子类继承的service方法调用的，所以this指向的是其子类
        //System.out.println(this);
        Class<? extends BaseServlet> cls = this.getClass();
        //2.2获取方法 Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3执行方法
            try {
                method.invoke(this,req,resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
