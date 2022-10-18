<%--
  Created by IntelliJ IDEA.
  User: 69584
  Date: 2022/7/1
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script>

    </script>

    <h1>${user.username},欢迎您</h1>
    <input type="button" id="add" name="add" value="新增">
    <table align="center" width="80%">
        <tr>
            <th>序号</th>
            <th>品牌名</th>
            <th>公司名</th>
            <th>品牌排名</th>
            <th>品牌介绍</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${brands}" var="brand" varStatus="status">
            <tr align="center">
                <td>${status.count}</td>
                <td>${brand.brandName}</td>
                <td>${brand.companyName}</td>
                <td>${brand.ordered}</td>
                <td>${brand.description}</td>
                <c:if test="${brand.status == 1}">
                    <td>启用</td>
                </c:if>
                <c:if test="${brand.status == 0}">
                    <td>禁用</td>
                </c:if>
                <td><a href="/brand_demo/selectByIdServlet?id=${brand.id}">修改</a> <a href="#">删除</a> </td>
            </tr>
        </c:forEach>
    </table>

<script>
    document.getElementById("add").onclick=function () {
        location.href="/brand_demo/addBrand.jsp";
    }
</script>

</body>
</html>
