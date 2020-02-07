<%-- 
    Document   : login
    Created on : Jan 9, 2020, 10:09:03 AM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="col-md-9 col-md-offset-3">
            <div class="projects main-content">

                <div class="limiter">
                    <div class="container-login100">
                        <div class="wrap-login100 p-t-90 p-b-30">

                            <form action="MainController" class="login100-form validate-form" method="POST">
                                <div class="text-center p-t-55 p-b-30">
                                    <span class="txt1">
                                        Login with email
                                    </span>
                                </div>
                                <label class="error-message">${requestScope.ERROR_MESSAGE}</label>
                                <div class="wrap-input100 validate-input m-b-16" data-validate="Please enter email: ex@abc.xyz">
                                    <input class="input100" type="text" name="txtEmail" placeholder="Email">
                                    <span class="focus-input100"></span>
                                </div>

                                <div class="wrap-input100 validate-input m-b-20" data-validate = "Please enter password from 6 - 24 chars">
                                    <span class="btn-show-pass">
                                        <i class="fa fa fa-eye"></i>
                                    </span>
                                    <input class="input100" type="password" name="txtPassword" placeholder="Password">
                                    <span class="focus-input100"></span>
                                </div>

                                <div class="container-login100-form-btn">
                                    <input type="submit" name="action" value="Login" class="login100-form-btn"/>
                                </div>

                                <div class="flex-col-c p-t-50">
                                    <span class="txt2 p-b-10">
                                        Don’t have an account?
                                    </span>

                                    <a href="register.jsp" class="txt3 bo1 hov1">
                                        Register now
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</div>




<%@include file="footer.jsp" %>
</body>
</html>
