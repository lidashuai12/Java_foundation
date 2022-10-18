<%--
  Created by IntelliJ IDEA.
  User: 69584
  Date: 2022/7/14
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
</head>
<body>
<div id="reigster">
    <form id="register" name="register" method="post" action="/brand_demo/registerServlet" >
        <h1>用户注册</h1>
        <p>用户名<input name="username" id="username" type="text" value="${cookie.registerUsername.value}">
            <span id="username_err" class="err_msg" style="display: none">用户名已存在</span>
        </p>
        <p>密码<input name="password" id="password" type="password" value="${cookie.registerPassword.value}"></p>
        <tr>
            <td>验证码</td>
            <td class="inputs">
                <input name="checkCode" type="text" id="checkCode">
                <img id="checkCodeImg" src="/brand_demo/checkCodeServlet">
                <a href="#" id="changeImg" >看不清？</a>
            </td>
        </tr>
        <p><input type="submit" value="提交"> <input type="reset" value="重置"></p>
        <p>${register_failed_msg}</p>
        <a href="login.jsp">已有账号？点击登录</a>
    </form>
</div>
<script>
    document.getElementById("changeImg").onclick = function () {
        //由于路径“/brand_demo/checkCodeServlet”与上面的一样，会被浏览器缓存，导致图片无法更换
        //添加“?a”的参数后只能更换一次图片，第二次又会被缓存
        //改用时间戳参数，就不会出现重名的情况
        document.getElementById("checkCodeImg").src = "/brand_demo/checkCodeServlet?" + new Date().getMilliseconds();
    };
    //使用ajax验证用户名是否存在
    //1.给用户名输入框绑定，失去焦点事件
    document.getElementById("username").onblur = function () {
        //2.发送ajax请求
        //2.1创建核心对象
        var xhttp;
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        //2.2发送请求
        //获取参数（用户名）,因为该事件函数已经绑定这个对象了，所以可以用this
        var username = this.value;

        //使用ajax时，前后端是两个项目，所以访问的资源路径要写完整的绝对路径
        xhttp.open("GET", "http://localhost:8080/brand_demo/selectUserServlet?username=" + username);
        xhttp.send();
        //3.获取响应
        xhttp.onreadystatechange = function() {
            if (this.readyState === 4 && this.status === 200) {
                //判断查询标记
                if (this.responseText === "false"){
                    //用户名存在,显示提示信息
                    document.getElementById("username_err").style.display = '';
                }else {
                    //用户名不存在，清除提示信息
                    document.getElementById("username_err").style.display = 'none';
                }
            }
        };
    };
</script>
</body>
</html>
