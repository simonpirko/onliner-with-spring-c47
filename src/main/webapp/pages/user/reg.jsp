<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        <%@include file="/pages/styles/input.css"%>
    </style>
</head>
<body>

<jsp:include page="/pages/_header.jsp"/>

<div class="container-fluid">

    <form action="/user/registration" method="post">

        <div class="row justify-content-md-center">
            <figure class="text-center g-5 pt-5">
                <h1 class="display-5">Registration</h1>
            </figure>

            <div class="col col-lg-3 mb-3">
                <label class="margin-input" class="form-label"><h4>Username</h4></label>
                <input name="username" type="text" class="form-control" placeholder="Username">
            </div>

            <div class="col col-lg-3 mb-3">
                <label class="margin-input" class="form-label"><h4>Password</h4></label>
                <input name="password" type="password" class="form-control" placeholder="Password">
            </div>

        </div>

        <div class="row justify-content-md-center">

            <div class="col col-lg-3 mb-3">
                <label class="margin-input" class="form-label"><h4>Name</h4></label>
                <input name="firstName" type="text" class="form-control" placeholder="Name">
            </div>

            <div class="col col-lg-3 mb-3">
                <label class="margin-input" class="form-label"><h4>Surname</h4></label>
                <input name="lastName" type="text" class="form-control" placeholder="Surname">
            </div>

        </div>

        <div class="row justify-content-md-center">

            <div class="col col-lg-3 mb-3">
                <label class="margin-input" for="inputEmail" class="form-label"><h4>Email</h4></label>
                <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email">
            </div>

            <div class="col col-lg-3 mb-3">
                <label class="margin-input" class="form-label"><h4>Phone number</h4></label>
                <input name="phoneNumber" type="text" class="form-control" placeholder="Number">
            </div>

        </div>

        <div class="d-grid gap-2 col-6 mx-auto">
            <button class="btn btn-primary" type="submit">Sing up</button>
        </div>

    </form>


    <div class="row justify-content-md-center">

        <div class="col-6">
            <c:if test="${requestScope.userMessage != null}">
                <div class="alert alert-success role=alert">
                        ${requestScope.firstNameMessage}
                </div>
            </c:if>

            <c:if test="${requestScope.firstNameMessage != null}">
                <div class="alert alert-danger role=alert">
                        ${requestScope.lastNameMessage}
                </div>
            </c:if>

            <c:if test="${requestScope.lastNameMessage != null}">
                <div class="alert alert-danger role=alert">
                        ${requestScope.lastNameMessage}
                </div>
            </c:if>

            <c:if test="${requestScope.usernameMessage != null}">
                <div class="alert alert-danger role=alert">
                        ${requestScope.usernameMessage}
                </div>
            </c:if>

            <c:if test="${requestScope.passwordMessage != null}">
                <div class="alert alert-danger role=alert">
                        ${requestScope.passwordMessage}
                </div>
            </c:if>

            <c:if test="${requestScope.phoneNumberMessage != null}">
                <div class="alert alert-danger role=alert">
                        ${requestScope.phoneNumberMessage}
                </div>
            </c:if>

        </div>

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
