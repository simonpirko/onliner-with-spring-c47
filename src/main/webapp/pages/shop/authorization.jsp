<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<jsp:include page="/pages/_header.jsp"/>
<div class="container-flex">
    <div class="row justify-content-md-center">
        <div class="col-sm-3 pt-3">
            <form action="/shop/authorization" method="post">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <a class="btn btn-primary" href="/shop/registration" role="button">Registration</a>
            </form>
            <c:if test="${requestScope.message!=null}">
                <div class="alert alert-danger" role="alert">
                        ${requestScope.message}
                </div>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
