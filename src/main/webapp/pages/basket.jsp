<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 15.12.2021
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basket</title>
    <style>
        <%@include file="/pages/styles/basket.css"%>
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
</head>
<body>
<jsp:include page="/pages/_header.jsp"/>
<div class="container mt-5 p-3 rounded cart">
    <div class="row no-gutters">
        <div class="col-md-8">
            <div class="product-details mr-2">
                <div class="d-flex flex-row align-items-center">
                    <a class="btn btn-light" href="/" role="button"><i class="bi bi-arrow-left"></i></a>
                    <span class="ml-2">Continue Shopping</span>
                </div>
                <hr>
                <h6 class="mb-0">Shopping cart</h6>
                <c:if test="${sessionScope.basketList != null}">
                    <c:if test="${fn:length(sessionScope.basketList) != 0}">
                        <c:forEach begin="0" end="${fn:length(sessionScope.basketList) - 1}" var="index">
                            <form action="/deleteItem" method="post" style="margin-top: 5px">
                                <label><input type="text" name="productBasketNumber" hidden value="${index}"></label>
                                <div class="d-flex justify-content-between align-items-center mt-3 p-2 items rounded">
                                    <div class="d-flex flex-row">
                                        <img class="rounded"
                                             src="${sessionScope.basketList.get(index).image}" width="40">
                                        <div class="ml-2" style="margin-left: 10px">
                                            <span class="font-weight-bold d-block">${sessionScope.basketList.get(index).brand}</span>
                                            <span class="spec">${sessionScope.basketList.get(index).model}</span>
                                        </div>
                                    </div>

                                    <div class="d-flex flex-row align-items-center">
                                    <span class="d-block"
                                          style="margin-right: 50px;">${sessionScope.basketList.get(index).amount}</span>
                                        <span class="d-block ml-5 font-weight-bold" style="margin-right: 30px;">
                                                ${sessionScope.basketList.get(index).price}</span>
                                        <button type="submit" class="btn btn-light"><i class="bi bi-trash"></i></button>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
        </div>
        <div class="col-md-4">
            <div class="payment-info">
                <div class="d-flex justify-content-between align-items-center">
                    <span>Card details</span>
                </div>
                <span class="type d-block mt-3 mb-1">Card type</span><label class="radio"> <input type="radio"
                                                                                                  name="card"
                                                                                                  value="payment"
                                                                                                  checked> <span><img
                    width="30" src="https://img.icons8.com/color/48/000000/mastercard.png"/></span> </label>
                <label class="radio"> <input type="radio" name="card" value="payment"> <span><img width="30"
                                                                                                  src="https://img.icons8.com/officel/48/000000/visa.png"/></span>
                </label>
                <label class="radio"> <input type="radio" name="card" value="payment"> <span><img width="30"
                                                                                                  src="https://img.icons8.com/ultraviolet/48/000000/amex.png"/></span>
                </label>
                <label class="radio"> <input type="radio" name="card" value="payment"> <span><img width="30"
                                                                                                  src="https://img.icons8.com/officel/48/000000/paypal.png"/></span>
                </label>
                <div><label class="credit-card-label">Name on card</label><input type="text"
                                                                                 class="form-control credit-inputs"
                                                                                 placeholder="Name"></div>
                <div>
                    <label class="credit-card-label">Card number</label>
                    <input type="text" class="form-control credit-inputs" placeholder="0000 0000 0000 0000">
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <label class="credit-card-label">Date</label>
                        <input type="text" class="form-control credit-inputs" placeholder="12/24">
                    </div>
                    <div class="col-md-6">
                        <label class="credit-card-label">CVV</label>
                        <input type="text" class="form-control credit-inputs" placeholder="342">
                    </div>
                </div>
                <hr class="line">
                <div class="d-flex justify-content-between information">
                    <span>Total</span>
                    <span>${requestScope.totalCost}p</span>
                </div>
                <button class="btn btn-primary mt-3" style="margin-left: 120px" type="button">
                    <center><span>Checkout</span></center>
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
