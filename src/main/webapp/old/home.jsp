<%--
  Created by IntelliJ IDEA.
  User: simonpirko
  Date: 23.11.21
  Time: 8:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<p>Hello ${sessionScope.user}!</p>
<a href="/user/reg">Registration</a>
<a href="/user/auth">Authorization</a>
<a href="/user/logout">Logout</a>
</body>
</html>
