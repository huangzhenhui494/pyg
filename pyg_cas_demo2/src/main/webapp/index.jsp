<%--
  Created by IntelliJ IDEA.
  User: Hzh-win10
  Date: 2019/2/22
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>欢迎您:<%=request.getRemoteUser()%>,登录系统</h1>
    <a href="http://192.168.25.125:9100/cas/logout?service=http://www.baidu.com">注销</a>
</body>
</html>
