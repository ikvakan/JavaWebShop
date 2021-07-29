<%-- 
    Document   : products
    Created on : 24.07.2021., 15:09:49
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proizvodi</title>
    </head>
    <body>
        <%@include file="../Shared/navigationAdmin.jsp" %> 
        <div class="container ">
            <div class="row ">
                <h1 class="text-center" style='margin-top: 70px;'>Proizvodi</h1>
            </div>
            <hr>
            <div class="row col-md-8">
                <div class="col-md-4">
                    <a href="<%=request.getContextPath()%>/showProduct" class="btn btn-success">Dodaj proizvod</a>
                </div>
                <div class="col-md-4">
                    <a href="<%=request.getContextPath()%>/listCategory" class="btn btn-primary">Dodaj kategoriju</a>
                </div>
            </div>
            <br/>
            <div class="row" style="margin-top: 30px;">
                <c:if test="${not empty product_list }">
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Naziv</th>
                            <th scope="col">Kategorija</th>                            
                            <th scope="col">Cijena</th>
                            <th scope="col">Kratki opis</th>
                            <th scope="col"></th>                            
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    </c:if>
                <c:forEach items="${product_list}" var="product">
                <tbody>
                    <tr>
                            <th scope="row"><c:out value="${product.idProduct}"/></th>
                            <td><c:out value="${product.name}"/></td>
                            <td><c:out value="${product.category.name}"/></td>
                            <td><c:out value="${product.price}"/></td>
                            <td class="col-sm-3"><c:out value="${product.description}"/> </td>
                            
                        <div class="row">
                            <div class="col-sm-1">
                                <td class="col-sm-1 "><a href="showEditProduct?id=${product.idProduct}" class="btn btn-primary ">Uredi</a></td>
                            </div>
                            <div class="col-sm-1">
                                <td class="col-sm-1 "><a href="deleteProduct?id=<c:out value="${product.idProduct}"/>" class="btn btn-danger ">Obriši</a></td>
                            </div>
                        </div>
                    </tr>
                </tbody>
                </c:forEach>
                </table>
            </div>
    </div>
</body>
</html>
