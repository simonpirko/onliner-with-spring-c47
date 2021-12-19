<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Zenbook
  Date: 03.12.2021
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mein window</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<jsp:include page="/pages/_header.jsp"/>

<div class="container">
    <div class="row m-4">

        <div class="col-2 ">
            <h4 style="margin-left: 70px">
                Categories
            </h4>
            <div class="card"
                 style="width: 250px; height: 125px;  left: 5px; margin-right: 20px; border-width: 5px; border-radius: 10%">
                <div class="list-group" id="list-tab" style="margin-top: 20px" role="tablist">
                    <div class="d-grid gap-2">
                        <a href="/product/category" class="btn btn-success">Mobile</a>
                        <a href="#" class="btn btn-success">Laptop</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-10" style=" display: flex; flex-wrap: wrap; flex-direction: row; width: 1000px;">
            <c:forEach begin="0" end="${fn:length(sessionScope.homeProductList) - 1}" var="index">
                <div class="col-5">
                    <div class="card mb-5"
                         style="  justify-content: space-between; margin-right :500px; border-top-left-radius: 15%; border-top-right-radius: 15%;  margin-left: 200px; height: 500px; width: 285px;">
                        <img class="card-img-top" style="width: 290px; height: 300px;"
                             src="${sessionScope.homeProductList.get(index).image}"
                             alt="Card image cap">
                        <form action="/" method="post">
                            <div class="card-body">
                                <label><input type="text" name="productNumber" hidden value="${index}"></label>
                                <h5 class="card-title"> ${sessionScope.homeProductList.get(index).model}</h5>
                                <p class="text">Price: ${sessionScope.homeProductList.get(index).price} p</p>
                                <button type="submit" class="btn btn-outline-secondary"><i class="bi bi-cart2"></i> Buy
                                </button>
                                <a href="#" class="btn btn-outline-secondary"><i class="bi bi-eye"></i> View Product</a>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
