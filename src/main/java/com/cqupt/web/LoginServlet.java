package com.cqupt.web;

import com.cqupt.pojo.User;
import com.cqupt.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //添加“记住我”功能,获取复选框数据
        String remember = req.getParameter("remember");

        //调用UserService查询
        User user = userService.login(username, password);

        //判断
        if (user != null){
            String contextPath = req.getContextPath();
            System.out.println(contextPath);
            //登录成功,重定向到'查询所有'的BrandServlet
            //判断用户是否勾选了“记住我”
            if ("1".equals(remember)){
                //勾选了，发送cookie；
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);
                //设置cookie存活时间
                c_username.setMaxAge(60*60*24*7);//单位为秒，存活7天
                c_password.setMaxAge(60*60*24*7);
                //发送
                resp.addCookie(c_username);
                resp.addCookie(c_password);
            }
            //将登陆成功后的User对象存放在Session中
            HttpSession session = req.getSession();
            session.setAttribute("user",user);

            resp.sendRedirect(contextPath + "/element-brand.html");
        }else {
            //登录失败
            //存储错误信息到request中
            req.setAttribute("login_msg","用户名或密码错误");
            //跳转到login.jsp
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
