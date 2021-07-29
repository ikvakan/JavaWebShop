<%-- 
    Document   : addCategory
    Created on : 24.07.2021., 18:29:42
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script  src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
        <script src="../Resources/JS/validationCategory.js" type="text/javascript"></script>
        <title>Dodaj kategoriju</title>

    </head>
    <body>
        <%@include file="../Shared/navigationAdmin.jsp" %> 



        <div class="container  ">
            <div class="row ">
                <h1 class="text-center" style='margin-top: 70px;'>Kategorije</h1>
            </div>
            <hr>
            <c:set var="category" value="${requestScope['category']}" />
            <c:choose>
                <c:when test="${category == null}">
                    <form id="categoryForm" method="post" action="<%=request.getContextPath()%>/addCategory">
                        <div class="row ">

                            <div class="col-sm-3">
                                <input type="text" required="true" name="category" class="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="submit" class="btn btn-primary" value="Dodaj kategoriju"/>
                            </div>

                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form id="categoryForm" method="post" action="<%=request.getContextPath()%>/updateCategory">
                        <div class="row ">

                            <div class="col-sm-3">
                                <input hidden="true" name="id" value="${category.idCategory}"/>
                                <input type="text" required="true" name="category" value="${category.name}" class="form-control"/>
                            </div>
                            <div class="col-sm-2">
                                <input type="submit" class="btn btn-primary" value="Spremi"/>
                            </div>

                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
            <br/>
            <c:choose>
                <c:when test="${not empty category_list}">

                    <div class="row" style="margin-top: 30px;">
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">Kategorija</th>
                                    <th scope="col"></th>    
                                    <th scope="col"></th>                              
                                </tr>
                            </thead>
                            <c:forEach items="${category_list}" var="category">

                                <tbody>
                                    <tr>
                                        <td scope="row"><c:out value="${category.idCategory}"/></td>
                                        <td scope="row"><c:out value="${category.name}"/></td>

                                <div class="row">
                                    <div class="col-sm-1">
                                        <td class="col-sm-1 "><a href="showEditCategory?id=${category.idCategory}" class="btn btn-primary ">Uredi</a></td>
                                    </div>
                                    <div class="col-sm-1">
                                        <td class="col-sm-1 "><a href="deleteCategory?id=${category.idCategory}" class="btn btn-danger ">Obriši</a></td>
                                    </div>
                                </div>
                                </tr>
                                </tbody>
                            </c:forEach>

                        </table>
                    </div>
                </c:when>
               
            </c:choose>

        </div>
    </body>
</html>

