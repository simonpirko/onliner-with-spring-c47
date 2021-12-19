<%--
  Created by IntelliJ IDEA.
  User: dima2
  Date: 11.12.2021
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<jsp:include page="/pages/_header.jsp"/>
<div class="container">
    <div class="col-sm-12 pt-5">
        <div class="row">
            <div class="col col-sm-8">
                <div class="row style=" style="min-height: 250px;">
                    <div class="col col-lg-4">
                        <img class="rounded mx-auto d-block" style="max-width: 100px;max-height: 130px"
                             src="${product.image}">
                    </div>
                    <div class="col col-lg-8">
                        <h5>${product.model}</h5>
                        <p>${product.description}</p>
                        <div>
                            <h6 class="card-title">от ${product.price}</h6>
                            <button type="button" class="btn btn-primary btn-sm">Все предложения</button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <h5>Характеристики</h5>
                </div>
                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Наименование характеристики</th>
                            <th scope="col">Значение</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Дата выхода на рынок</td>
                            <td>2021 г.</td>
                        </tr>
                        <tr>
                            <td>Продуктовая линейка</td>
                            <td>Honor MagicBook</td>
                        </tr>
                        <tr>
                            <td>Тип</td>
                            <td>ультрабук</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-sm-2">
                <div class="row">
                    <div class="col-sm-6">
                        <h6 class="card-title">от ${product.price}</h6>
                        <button type="button" class="btn btn-primary btn-sm">Купить</button>
                    </div>
                    <div class="col-sm-6 m-auto">
                        <h5>Agroup</h5></span>
                    </div>
                </div>

                <div class="row justify-content-md-center mt-5">
                    <button type="button" class="btn btn-primary btn-sm">Все предложения</button>
                </div>
                <div class="row justify-content-md-start mt-2">
                    <p class="card-text"><small class="text-muted">Приведенные предложения продавцов являются рекламной
                        информацией и их приглашением делать оферты. При покупке всегда запоминайте полное наименование
                        юридического лица или индивидуального предпринимателя. Обязательно уточняйте комплект поставки,
                        цвет
                        товара и иную информацию в процессе заказа.
                        Проблемы с наличием или ценой? Хотите пожаловаться или похвалить продавца? Вы можете оставить
                        отзыв
                        на
                        странице магазина!</small></p>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>