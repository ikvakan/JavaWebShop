<%-- 
    Document   : login
    Created on : 23.07.2021., 23:01:56
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container w-25" style="padding-top:100px;" >
            <div class="row">
                <div>
                    <h1 class="text-center text-muted">Prijava</h1>
                </div>
                <div class="form-group">
                    <form id="registerForm" method="post" action="LoginServlet" >
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="txtUserName">Korisničko ime</label>
                            <input type="text" class="form-control" id="txtUserName" name="userName"/>
                        </div>

                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtPassword">Lozinka</label>
                            <input type="password" class="form-control" id="txtPassword"   name="password"/>
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
