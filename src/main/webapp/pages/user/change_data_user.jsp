<%--
  Created by IntelliJ IDEA.
  User: Zenbook
  Date: 05.12.2021
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Change data user</title>
</head>
<body>
<jsp:include page="/pages/_header.jsp"/>

<div class="container " style="padding-top: 50px;">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <form action="/user/changerUser" method="post">
                            <div class="row px-3">
                                <label class="mb-1">
                                    <h6 class="mb-0 text-sm">Name</h6>
                                </label>
                                <input style="padding-left: 20px;" name="firstname" type="text" class="form-control"
                                       value="${sessionScope.user.firstName}">
                            </div>
                            <c:if test="${requestScope.firstNameMessage!=null}">
                                <div class="alert alert-danger" role="alert">
                                        ${requestScope.firstNameMessage}
                                </div>
                            </c:if>
                            <div class="row px-3">
                                <label class="mb-1">
                                    <h6 class="mb-0 text-sm">Surname</h6>
                                </label>
                                <input style="padding-left: 20px;" name="lastname" type="text" class="form-control"
                                       value="${sessionScope.user.lastName}">
                            </div>
                            <c:if test="${requestScope.lastNameMessage!=null}">
                                <div class="alert alert-danger" role="alert">
                                        ${requestScope.lastNameMessage}
                                </div>
                            </c:if>
                            <div class="row px-3">
                                <label class="mb-1">
                                    <h6 class="mb-0 text-sm">Username</h6>
                                </label>
                                <input style="padding-left: 20px;" name="username" type="text" class="form-control"
                                       value="${sessionScope.user.username}">
                            </div>
                            <c:if test="${requestScope.usernameMessage!=null}">
                                <div class="alert alert-danger" role="alert">
                                        ${requestScope.usernameMessage}
                                </div>
                            </c:if>
                            <div class="row px-3">
                                <label class="mb-1">
                                    <h6 class="mb-0 text-sm">Password</h6>
                                </label>
                                <input style="padding-left: 20px;" name="password" type="password" class="form-control"
                                       value="${sessionScope.user.password}">
                            </div>
                            <c:if test="${requestScope.passwordMessage!=null}">
                                <div class="alert alert-danger" role="alert">
                                        ${requestScope.passwordMessage}
                                </div>
                            </c:if>
                            <div class="row px-3">
                                <label class="mb-1">
                                    <h6 class="mb-0 text-sm">Phone</h6>
                                </label>
                                <input style="padding-left: 20px;" name="phonenumber" type="text" class="form-control"
                                       value="${sessionScope.user.phoneNumber}">
                            </div>
                            <c:if test="${requestScope.phoneMessage!=null}">
                                <div class="alert alert-danger" role="alert">
                                        ${requestScope.phoneMessage}
                                </div>
                            </c:if>
                            <div class="row px-3">
                                <label class="mb-1">
                                    <h6 class="mb-0 text-sm">Email</h6>
                                </label>
                                <input style="padding-left: 20px;" name="email" type="text" class="form-control"
                                       value="${sessionScope.user.email}">
                            </div>
                            <c:if test="${requestScope.emailMessage!=null}">
                                <div class="alert alert-danger" role="alert">
                                        ${requestScope.emailMessage}
                                </div>
                            </c:if>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12">
                                    <button class="btn btn-primary" type="submit">Save changes</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>