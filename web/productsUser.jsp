<%-- 
    Document   : productsUser
    Created on : 29.07.2021., 14:45:15
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
                <h1 class="text-center" style='margin-top: 70px;'>Proizvodi</h1>
            </div>
            <hr>
            <c:if test="${productAddedMsg!=null}">
                <div class="row bg-success p-3"  >
                    <span class="text-center "><b>${productAddedMsg}</b></span>
                </div>
                <hr>

            </c:if>
            <form id="" method="post" action="<%=request.getContextPath()%>/showProductsByCategory">

                <div class="row ">
                    <div class="col-sm-3">
                        <select class="form-select"  name="category" for="category" >
                            <c:forEach items="${categories}" var="category">
                                <option id="category" value="${category.idCategory}" ${category.idCategory==selectedCategory ? 'selected':''} >${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" class="btn btn-primary" value="Odaberi kategoriju"/>
                    </div>
                </div>
            </form>

            <div class="row" style="margin-top: 30px;">
                <c:if test="${not empty products }">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                
                                <th scope="col">Naziv</th>
                                <th scope="col">Kategorija</th>                            
                                <th scope="col">Cijena</th>
                                <th scope="col">Kratki opis</th>
                                <th scope="col">Količina</th>
                                <th scope="col"></th>                            
                                <th scope="col"></th>
                            </tr>
                        </thead>
                    </c:if>
                    <c:forEach items="${products}" var="product">
                        <form action="addToCart?id=${product.idProduct}" method="post">
                        <tbody>
                            <tr>
                                <td><c:out value="${product.name}"/></td>
                                <td><c:out value="${product.category.name}"/></td>
                                <td><c:out value="${product.price} kn"/></td>
                                <td class="col-sm-3"><c:out value="${product.description}"/> </td>
                                <td  class="col-sm-1"><input class="form-control " type="number" min="1" value="1" name="quantity" /> </td>
                        <div class="row">
                            <div class="col-sm-1">
                              <!--  <td class="col-sm-2 "><a href="addToCart?id=${product.idProduct}" class="btn btn-danger ">Dodaj u košaricu</a></td> -->
                                <td class="col-sm-2"> <input type="submit" class=" btn btn-danger" value="Dodaj u košaricu" /> </td> 
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
