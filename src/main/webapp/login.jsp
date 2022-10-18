<%--
  Created by IntelliJ IDEA.
  User: 69584
  Date: 2022/7/13
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
</head>
<body>
<div id="loginDiv">
<%--    <h1>用户登录</h1><br>--%>

    <form action="/brand_demo_new/loginServlet" method="post" id="form">
        <h1 id="loginMsg">LOG IN</h1>
        <div id="errMsg" color="red">${login_msg} ${register_success_msg}</div>
        <p>用户名<input id="username" type="text" name="username" value="${cookie.username.value}"></p>

        <p>密码<input id="password" name="password" type="password" value="${cookie.password.value}"></p>
        <p>记住我<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">
            <a href="register.html">没有账号？点击注册</a>
        </div>
    </form>

</div>
</body>
</html>
