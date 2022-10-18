<%--
  Created by IntelliJ IDEA.
  User: 69584
  Date: 2022/7/1
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>修改品牌</title>
</head>
<body>
<h3>修改品牌</h3>
<form action="/brand_demo/updateServlet" method="post">
    <%--隐藏域，提交id，让serlvet知道修改的是哪一个--%>
    <input type="hidden" name="id" value="${brand.id}">


    品牌名称：<input name="brandName" value="${brand.brandName}"><br>
    企业名称：<input name="companyName" value="${brand.companyName}"><br>
    排序：<input name="ordered" value="${brand.ordered}"><br>
    描述信息：<textarea rows="5" cols="20" name="description">${brand.description}</textarea><br>
    状态：
    <c:if test="${brand.status == 0}">
        <input type="radio" name="status" value="0" checked>禁用
        <input type="radio" name="status" value="1">启用
    </c:if>
    <c:if test="${brand.status ==1}">
        <input type="radio" name="status" value="0" >禁用
        <input type="radio" name="status" value="1"checked>启用
    </c:if>


    <input type="submit" value="提交">
</form>
</body>
</html>

