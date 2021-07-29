<%-- 
    Document   : adminLogin
    Created on : 23.07.2021., 23:04:00
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
        <script  src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" 
                integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" 
        crossorigin="anonymous"></script>

        <link href="../Resources/Css/login.css" rel="stylesheet" type="text/css"/>

        <title>JSP Page</title>
    </head>
    <body>
        <div class="container w-25" style="padding-top:100px;" >
            <div class="row">
                <div>
                    <h1 class="text-center text-muted">Prijava</h1>
                </div>
                <div class="form-group">
                    <form action="<%=request.getContextPath()%>/j_security_check" method="POST" >
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="txtUserName">Korisničko ime</label>
                            <input type="text" class="form-control" id="txtUserName" name="j_username"/>
                        </div>

                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtPassword">Lozinka</label>
                            <input type="password" class="form-control" id="txtPassword"   name="j_password"/>
                        </div>
                        <div class="form-group" style="padding:25px">
                            <div class="row">
                                <input class="btn btn-dark btn-block" style="margin-bottom: 15px;" type="submit" value="Spremi"/>
                                <a href="<%=request.getContextPath()%>/home.jsp" class="btn btn-danger">Povratak</a>
                            </div>
                        </div>

                    </form>
                </div>

            </div>

        </div>
    </body>
</html>
