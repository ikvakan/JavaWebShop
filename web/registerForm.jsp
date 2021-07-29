<%-- 
    Document   : registerForm
    Created on : 18.07.2021., 16:11:31
    Author     : IgorKvakan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script  src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link href="Resources/Css/register.css" rel="stylesheet" type="text/css"/>
        <script src="Resources/JS/validationRegister.js" type="text/javascript"></script>



        <title>Shopping app</title>
    </head>
    <body>
        <div class="container w-25" style="padding-top:100px;" >
            <div class="row">
                <div>
                    <h1 class="text-center text-muted">Registracija</h1>
                </div>
                <div class="form-group">
                    <form id="registerForm" method="post" action="register" >
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="txtUserName">Korisničko ime</label>
                            <input type="text" class="form-control" id="txtUserName" name="userName"/>
                        </div>
                        <div >
                            <label class="text-muted" style="font-weight: bold;" for="txtName">Ime</label>
                            <input type="text" class="form-control" id="txtName" name="name"/>
                        </div>
                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtSurname">Prezime</label>
                            <input type="text" class="form-control" id="txtSurname"  name="surname"/>
                        </div>
                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtPassword">Lozinka</label>
                            <input type="password" class="form-control" id="txtPassword"   name="password"/>
                        </div>
                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtEmail">Email</label>
                            <input type="email" class="form-control" id="txtEmail" required="true"  name="email"/>
                        </div>
                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtAdress">Adresa</label>
                            <input type="text" class="form-control" id="txtAdress"   name="adress"/>
                        </div>
                        <div>
                            <label class="text-muted" style="font-weight: bold;" for="txtCity">Grad</label>
                            <input type="text" class="form-control" id="txtCity"   name="city"/>
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
