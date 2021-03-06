<%-- 
    Document   : myOrders
    Created on : 01.08.2021., 20:20:02
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              crossorigin="anonymous"/>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../Shared/navigationUser.jsp" %>

        <div class="container">
            <div class="row ">
                <h1 class="text-center" style='margin-top: 70px;'>Moja košarica</h1>
            </div>
            <hr>

            <c:choose>
                <c:when test="${not empty orders}">

                    <c:forEach items="${orders}" var="order">

                        <div class="row" style="margin: 30px;">
                            <fieldset>
                                <fmt:parseDate value="${order.buyDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                <legend >Datum kupnje - <span class="text-success"><fmt:formatDate pattern="dd.MM.yyyy." value="${parsedDate}" type="date"/> </span> </legend>
                                <table class="table" >
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">Naziv</th>
                                            <th scope="col">Kategorija</th>                            
                                            <th scope="col">Cijena</th>
                                            <th scope="col">Količina</th>
                                            <th scope="col">Ukupno</th>                            
                                            <th scope="col">Način plaćanja</th>

                                        </tr>
                                    </thead>

                                    <c:forEach items="${order.cartItems}" var="cartItem" >
                                        <tbody>
                                            <tr>
                                                <td><c:out value="${cartItem.product.name}"/></td>
                                                <td><c:out value="${cartItem.product.category.name}"/></td>
                                                <td><c:out value="${cartItem.product.price} kn"/></td>
                                                <td ><c:out value="${cartItem.quantity}"/></td>
                                                <td class=""><c:out value="${cartItem.total} kn"/> </td>
                                                <td><c:out value="${order.paymentMethod}"/> </td>
                                            </tr>
                                        </tbody>
                                    </c:forEach>

                                    <tr>
                                        <td><b>UKUPNA CIJENA: <span class="text-danger" >${order.totalPrice} kn</span></b></td>
                                    </tr>
                                </table>
                                   
                            </fieldset>

                        </div>

                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <div>
                        <h1 class="text-center text-danger">Nema proizvoda u košarici</h1>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>



    </body>
</html>
