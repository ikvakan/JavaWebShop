<%-- 
    Document   : users
    Created on : 01.08.2021., 23:42:30
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
        <%@include file="../Shared/navigationAdmin.jsp" %>
        <div class="container">
            <div class="row ">
                <h1 class="text-center" style='margin-top: 70px;'>Korisnici</h1>
            </div>
            <hr>
            <c:choose>
                <c:when test="${not empty users}">
                  

                    <div class="row" style="margin-top: 30px;">

                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Korisničko ime</th>
                                    <th scope="col">Ime</th>                            
                                    <th scope="col">Prezime</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Adresa</th>
                                    <th scope="col">Grad</th>                            
                                    <th scope="col">Datum registracije</th>
                                    <th scope="col">Ip adresa</th>

                                </tr>
                            </thead>

                            <c:forEach items="${users}" var="user">
                                <tbody>
                                    <tr>
                                        <td><c:out value="${user.userName}"/></td>
                                        <td><c:out value="${user.name}"/></td>
                                        <td><c:out value="${user.surname}"/></td>
                                        <td ><c:out value="${user.email}"/> </td>
                                        <td ><c:out value="${user.adress}"/> </td>
                                        <td ><c:out value="${user.city}"/> </td>
                                        <fmt:parseDate value="${user.regDate}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
                                        <td ><fmt:formatDate pattern="dd.MM.yyyy." value="${parsedDate}" type="date"/> </td> 
                                        <td ><c:out value="${user.ipAdress}"/> </td>
                                    </tr>
                                </tbody>

                            </c:forEach>
                        </table>
                    </div>
                     </c:when>
                <c:otherwise>
                    <div>
                        <h1 class="text-center text-danger">Nema registriranih korisnika</h1>
                    </div>
                </c:otherwise>
            </c:choose>

                </div>
            </body>
        </html>
