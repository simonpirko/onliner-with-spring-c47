<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">FakeOnliner</a>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <c:if test="${sessionScope.user == null}">
                <form class="d-flex m-2" action="/search" method="post">
                    <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success  m-2" type="submit"><i class="bi bi-search"></i></button>
                </form>
                <form class="d-flex m-2" action="/shop/authorization" method="post">
                    <a class="btn btn-outline-success" href="/shop/authorization" role="button"><i
                            class="bi bi-shop"></i></a>
                </form>
                <form class="d-flex m-2" action="/basket" method="post">
                    <a class="btn btn-outline-primary" href="/basket" role="button"><i class="bi bi-basket"></i></a>
                </form>
                <form class="d-flex m-2" action="/user/authorization" method="post">
                    <a class="btn btn-outline-info" href="/user/authorization" role="button"><i
                            class="bi bi-box-arrow-in-right"></i></a>
                </form>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <c:if test="${sessionScope.user.status eq 'admin'}">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/admin/users">Users</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/products">Products</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/categories">Categories</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.user.status eq 'user'}">
                    <form class="d-flex m-2" action="/search" method="post">
                        <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success  m-2" type="submit"><i class="bi bi-search"></i></button>
                    </form>
                    <a class="nav-link">${sessionScope.user.username}</a>
                    <form class="d-flex m-2" action="/search" method="post">
                        <a class="btn btn-outline-success" href="/pages/user/user_profile.jsp"><i
                                class="bi bi-person-circle"></i></a>
                    </form>
                    <form class="d-flex m-2" action="/search" method="post">
                        <a class="btn btn-outline-primary" href="/basket" role="button"><i class="bi bi-basket"></i></a>
                    </form>
                    <form class="d-flex m-2" action="/search" method="post">
                        <a class="btn btn-outline-danger" href="/logOut"><i class="bi bi-box-arrow-right"></i></a>
                    </form>
                </c:if>
            </c:if>
        </div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>