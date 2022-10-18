package com.cqupt.web;

import com.cqupt.pojo.User;
import com.cqupt.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectUserServlet")
public class SelectUserServlet extends HttpServlet {
    private UserService userService = new UserService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        System.out.println(username);
        //调用service查询用户名是否已经被使用
        boolean flag = userService.register_ajax(username);
        System.out.println(flag);
        //向页面输出标记，true表示可以注册，false表示不能注册
        resp.getWriter().write("" + flag);



    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

}
