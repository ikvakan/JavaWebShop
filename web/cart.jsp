<%-- 
    Document   : cart
    Created on : 30.07.2021., 01:17:31
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
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
                <c:when test="${not empty cartItems}">
                    <div class="row" style="margin: 30px;">

                        <table class="table">
                            <thead class="thead-dark">
                                <tr>

                                    <th scope="col">Naziv</th>
                                    <th scope="col">Kategorija</th>                            
                                    <th scope="col">Cijena</th>
                                    <th scope="col">Količina</th>
                                    <th scope="col">Ukupno</th>                            
                                    <th scope="col"></th>
                                    <th scope="col"></th>

                                </tr>
                            </thead>

                            <c:forEach items="${cartItems}" var="cartItem">
                                    <tbody>
                                        <tr>
                                            <td><c:out value="${cartItem.product.name}"/></td>
                                            <td><c:out value="${cartItem.product.category.name}"/></td>
                                            <td><c:out value="${cartItem.product.price} kn"/></td>
                                            <td  class="col-sm-1"><input class="form-control " type="number" min="1" value="${cartItem.quantity}" name="quantity" /> </td>
                                            <td class=""><c:out value="${cartItem.total} kn"/> </td>

                                    <div class="row">
                                        <div class="col-sm-1">
                                          < <td class="col-sm-1 "><a href="updateCart?id=${cartItem.product.idProduct}&quantity=${cartItem.quantity}" class="btn btn-primary ">
                                                  Ažuriraj</a>
                                          </td> 
                                           
                                        </div>
                                        <div class="col-sm-1">
                                          <!--  <td class="col-sm-1"> <input type="submit" class=" btn btn-danger" value="Ukloni" /> </td> -->
                                              <td class="col-sm-1 "><a href="removeCartItem?id=${cartItem.product.idProduct}" class="btn btn-danger ">Ukloni</a></td>
                                        </div>
                                    </div>
                                    </tr>
                                    </tbody>
                                
                            </c:forEach>

                        </table>
                    </div>

                    <hr>
                    <form>
                        <div class="row">
                            <div class="col-sm-2">
                                <span ><b>ZA PLATITI: <span class="text-danger">${totalPrice} kn</span> </b></span>
                            </div>
                            <div class="col-sm-2">
                                <input style="width: 250px;"  type="submit" class="btn btn-success  " value="Naplata"/>
                            </div>
                        </div>
                    </form>
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
