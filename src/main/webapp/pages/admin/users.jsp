<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 10.12.2021
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<jsp:include page="/pages/_header.jsp"/>
<div>
    <center>
        <h1>Пользователи</h1>
    </center>
</div>
<div class="row justify-center m-5">
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Username</th>
            <th scope="col">Phone</th>
            <th scope="col">Email</th>
            <th scope="col">Status</th>
            <th scope="col">Admin</th>
            <th scope="col">Admin</th>
            <th scope="col">User</th>
        </tr>
        </thead>
        <c:if test="${sessionScope.userList != null}">
            <form action="/admin/users" method="post">
                <c:forEach begin="0" end="${fn:length(userList) - 1}" var="index">
                    <tbody>
                    <label><input type="text" name="userNumber" hidden value="${index}"></label>
                    <tr>
                        <th scope="row">${index + 1}</th>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.username}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.email}</td>
                        <td>${user.status}</td>
                        <td>
                            <button type="submit" name="userOperation" class="btn btn-warning" value="addAdmin">Add
                            </button>
                        </td>
                        <td>
                            <button type="submit" name="userOperation" class="btn btn-success" value="removeAdmin">
                                Remove
                            </button>
                        </td>
                        <td>
                            <button type="submit" name="userOperation" class="btn btn-danger" value="deleteUser">Delete
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </form>
        </c:if>
    </table>
</div>
</body>
</html>
