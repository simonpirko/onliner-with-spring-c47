<%--
  Created by IntelliJ IDEA.
  User: simonpirko
  Date: 23.11.21
  Time: 8:48 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%--<form action="/user/reg" method="post">--%>
<%--    <input type="text" name="name" placeholder="Name">--%>
<%--    <input type="text" name="username" placeholder="Username">--%>
<%--    <p>${username}</p>--%>
<%--    <input type="password" name="password" placeholder="Password">--%>
<%--    <p>${password}</p>--%>
<%--    <button>Submit</button>--%>
<%--</form>--%>

<s:form action="/user/reg" method="post" modelAttribute="newUser">
    <s:input path="name" placeholder="Name"/>
    <br>
    <s:errors path="name"/>
    <br>
    <s:input path="username" placeholder="Username"/>
    <br>
    <s:errors path="username"/>
    <br>
    <s:input path="password" placeholder="Password"/>
    <br>
    <s:errors path="password"/>
    <br>
    <s:input path="code" placeholder="Code"/>
    <br>
    <s:errors path="code"/>
    <br>
    <s:input path="code2" placeholder="Code2"/>
    <br>
    <s:errors path="code2"/>
    <br>
    <s:input path="number" placeholder="Number"/>
    <br>
    <s:errors path="number"/>
    <br>
    <s:input path="number2" placeholder="Number2"/>
    <br>
    <s:errors path="number2"/>
    <br>
    <s:button>Submit</s:button>
</s:form>

</body>
</html>
