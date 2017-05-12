<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: dasouche
  Date: 17/5/2
  Time: 下午7:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户</title>
</head>
<body>
    <form method="post" action="<c:url value="/user/create" />">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input type="text" name="userName" /></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="text" name="password" /></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="realName" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" name="提交" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
