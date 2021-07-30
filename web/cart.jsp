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
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../Shared/navigationUser.jsp" %>
        <div class="container">
            <div class="row ">
                <h1 class="text-center" style='margin-top: 70px;'>Proizvodi</h1>
            </div>
            <hr>
            
                <div class="row bg-success p-3"  >
                    <span class="text-center "><b>${msg}</b></span>
                </div>
                <hr>

            <form id="" method="post" action="<%=request.getContextPath()%>/">

                <div class="row ">
                    <div class="col-sm-3">
                        <select class="form-select"  name="category" for="category" >
                            <c:forEach items="${categories}" var="category">
                                <option id="category" value="${category.idCategory}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" class="btn btn-primary" value="Odaberi kategoriju"/>
                    </div>
                </div>
            </form>

            <div class="row" style="margin-top: 30px;">
                
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>

                                <th scope="col">Naziv</th>
                                <th scope="col">Kategorija</th>                            
                                <th scope="col">Cijena</th>
                                <th scope="col">Kratki opis</th>
                                <th scope="col">Količina</th>
                                <th scope="col">Ukupno</th>                            
                                <th scope="col"></th>
                            </tr>
                        </thead>
                    
                    <c:forEach items="${productsInCart}" var="cartItem">
                        <form action="" method="post">
                            <tbody>
                                <tr>
                                    <td><c:out value="${cartItem.name}"/></td>
                                    <td><c:out value="${cartItem.product.category.name}"/></td>
                                    <td><c:out value="${cartItem.product.price}"/></td>
                                    <td class="col-sm-3"><c:out value="${product.description}"/> </td>
                                    <td  class="col-sm-1"><input class="form-control " type="number" min="1" value="${cartItem.quantity}" name="quantity" /> </td>
                                    <td class="col-sm-3"><c:out value="${cartItem.total}"/> </td>

                            <div class="row">
                                <div class="col-sm-1">
                                  <!--  <td class="col-sm-2 "><a href="addToCart?id=${product.idProduct}" class="btn btn-danger ">Dodaj u košaricu</a></td> -->
                                    <td class="col-sm-2"> <input type="submit" class=" btn btn-danger" value="Ažuriraj" /> </td> 
                                </div>
                            </div>
                            </tr>
                            </tbody>
                        </form>
                    </c:forEach>
                </table>
            </div>

        </div>

    </body>
</html>
