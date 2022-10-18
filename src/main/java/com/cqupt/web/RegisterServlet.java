package com.cqupt.web;

import com.cqupt.pojo.User;
import com.cqupt.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session中获取生成的验证码
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCodeGen");

        String contextPath = req.getContextPath();

        //获取用户输入的验证码、用户名和密码
        String checkCode_input = req.getParameter("checkCode");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //比对验证码，忽略大小写
        if (!checkCode_input.equalsIgnoreCase(checkCode)) {
            //验证码错误，不允许注册
            req.setAttribute("register_failed_msg", "验证码错误！");
            req.getRequestDispatcher( "/register.jsp").forward(req, resp);
            //return以后下面的代码就不会执行了，如果验证码正确就可以继续执行下面代码
            return;
        }
        //封装进新用户对象内
        User user = new User(null, username, password);
        //调用UserService的register方法
        boolean flag = userService.register(user);
        if (flag) {
            req.setAttribute("register_success_msg", "注册成功,请登录！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("register_failed_msg", "用户名已被用过！");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
