<%-- 
    Document   : addProduct
    Created on : 24.07.2021., 16:41:02
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">

        <script  src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"/>


        <script src="../Resources/JS/validationProduct.js" type="text/javascript"></script>



    </head>
    <body>
        <%@include file="../Shared/navigationAdmin.jsp" %> 
        <div style="margin-top: 100px;" class="container w-25"  >
            <c:set var="product" value="${requestScope['product']}" />
            <c:choose>
                <c:when test="${product ==null}">
            <div class="row">
                <div>
                    <h1 class="text-center text-muted">Dodaj proizvod</h1>
                </div>
                <div class="form-group" style="margin-top: 40px;">
                    <form id="productForm" method="post" action="addProduct" >
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="productName">Naziv</label>
                            <input type="text" required="true" class="form-control" id="productName" name="productName" />
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="productPrice">Cijena</label>
                            <input type="number" step="0.1" min="0" required="true" class="form-control digits" id="productPrice" name="productPrice"/>
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="productDesc">Kratki opis</label>
                            <textarea id="productDesc" required="true" class="form-control" name="productDesc" ></textarea>
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="category">Kategorija</label>
                            <select class="form-control" name="category" for="category" >
                                <c:forEach items="${category_list}" var="category">
                                    <option id="category" value="${category.idCategory}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="padding:25px">
                            <div class="row">
                                <input class="btn btn-danger btn-block" style="margin-bottom: 15px;" type="submit" value="Spremi"/>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
            </c:when>
                <c:otherwise>
                    <div class="row">
                <div>
                    <h1 class="text-center text-muted">Uredi proizvod</h1>
                </div>
                <div class="form-group" style="margin-top: 40px;">
                    <form id="productForm" method="post" action="updateProduct?id=${product.idProduct}" >
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="productName">Naziv</label>
                            <input type="text" required="true" class="form-control" id="productName" name="productName" value="${product.name}"/>
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="productPrice">Cijena</label>
                            <input type="number" step="0.1" required="true" class="form-control digits" id="productPrice" name="productPrice" value="${product.price}"/>
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="productDesc">Kratki opis</label>
                            <textarea id="productDesc" required="true" class="form-control" name="productDesc" 
                                      value="${product.description}"><c:out value="${product.description}"/></textarea>
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="category">Kategorija</label>
                            <select class="form-control" name="category" for="category" >
                                <c:forEach items="${category_list}" var="category">
                                    <option id="category" value=${category.idCategory} ${category.idCategory==product.category.idCategory ? 'selected' :''}>${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" style="padding:25px">
                            <div class="row">
                                <input class="btn btn-danger btn-block" style="margin-bottom: 15px;" type="submit" value="Spremi"/>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
                </c:otherwise>
            </c:choose>


    </body>
</html>
