<%-- 
    Document   : home
    Created on : 19.07.2021., 12:51:21
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        
     
        <link href="Resources/Css/navbar.css" rel="stylesheet" type="text/css"/>

        <title>Admin page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg   navbar-dark bg-dark ">
            <div class="container-fluid  ">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/Admin/homeAdmin.jsp">Admin page</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav ms-auto  navbar-dark bg-dark">

                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="<%=request.getContextPath()%>/Product">Proizvodi</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="<%=request.getContextPath()%>/showOrders">Narudžbe</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " href="<%=request.getContextPath()%>/Users">Korisnici</a>
                        </li>
                      
                        <li class="nav-item" >
                            <a class="nav-link" href="<%=request.getContextPath()%>/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </body>
</html>
