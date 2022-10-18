package com.cqupt.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //要先把ServletRequest强制转换成HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;
        //判断是否与登录相关
        //"selectUserServlet",
        String[] URL = {"/user","/brand","/addServlet_01","/element-brand.html","/login.jsp","/imgs/","/js","/css/","/loginServlet","/register.jsp","/registerServlet","/checkCodeServlet","/register.html","selectAllServlet_01","brand-json.html","addBrand.html"};
        //获取当前访问的资源路径
        String url = request.getRequestURL().toString();
        //判断访问资源的路径是否在放行范围内
        for (String s:URL) {
            //如果访问资源的路径包含有放行路径，就放行
            if (url.contains(s)){
                //找到了，放行
                chain.doFilter(req, resp);
                //这里写break只是跳出循环，所以这里只能写return结束这个方法，放行访问登陆资源的请求，
                //后面的登录验证不要做，用户输入用户名密码后会发起第二次请求.
                //当循环结束也没有在放行范围内匹配到的话，就不执行return，而是继续执行后面的登录验证判断(这种情况
                // 可以是用户输入登陆信息发送第二次请求，也可以是第三方恶意跳过登陆直接访问内部资源，这两种情况都需要登陆验证来判别)
                return;
            }
        }


        //判断session中是否有user
        //因为登录功能里把成功登录的用户信息存进了session中
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        //判断user是否为空
        if (user != null){
            //用户登陆过了
            //放行，这里req和request都是一个东西，用哪个都可以。只是做了个强制转换，并没有改变内部数据。
            chain.doFilter(req, resp);
        }else {
            //没有登陆,存储提示信息，跳转到登录页面
            req.setAttribute("login_msg","您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }
    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
