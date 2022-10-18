<%--
  Created by IntelliJ IDEA.
  User: 69584
  Date: 2022/7/1
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>添加品牌</title>
</head>
<body>
    <form action="/brand_demo/addServlet" method="post">
        品牌名称：<input name="brandName"><br>
        企业名称：<input name="companyName"><br>
        排序：<input name="ordered"><br>
        描述信息：<textarea rows="5" cols="20" name="description"></textarea><br>
        状态：
            <input type="radio" name="status" value="0">禁用
            <input type="radio" name="status" value="1">启用

        <input type="submit" value="提交">
    </form>
</body>
</html>
