<%-- 
    Document   : home
    Created on : 19.07.2021., 12:51:21
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
              crossorigin="anonymous">
        <script  src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" 
        crossorigin="anonymous"></script>
        <link href="Resources/Css/navbar.css" rel="stylesheet" type="text/css"/>


        <title>Shopping app</title>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg   navbar-dark bg-dark ">
            <div class="container-fluid  ">
                <a class="navbar-brand" href="home.jsp">Shopping app</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav ms-auto  navbar-dark bg-dark">
                        <c:if test="${user!= null}">
                            <li class="nav-item">
                                <a style="color: #ffff00" class="nav-link " href="#">Dobro došao, ${user.userName}</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="<%=request.getContextPath()%>/showAllProducts">Proizvodi</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="showCart">Košarica</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="MyOrders">Moje kupnje</a>
                        </li>

                        <li class="nav-item dropdown dropdown-left-manual " id="navbarNavDropdown" >
                            <a class="nav-link  dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Login
                            </a>
                            
                                <ul class="dropdown-menu  dropdown-left-manual" style="right: 0; left: auto;" aria-labelledby="navbarDropdownMenuLink">
                                    <li><a class="dropdown-item" href="loginForm.jsp">Login</a></li>
                                    <li><a class="dropdown-item" href="registerForm.jsp">Registracija</a></li>
                                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Admin/homeAdmin.jsp">Za admnistratore</a></li>
                                    <li><a class="dropdown-item" href="Logout">Logout</a></li>
                                </ul>
                           
                        </li>

                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
