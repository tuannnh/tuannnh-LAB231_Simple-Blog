<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta charset="UTF-8">
        <title>Tuan - Project LAB Blog</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">

        <link rel='stylesheet' href='../../fonts.googleapis.com/cssbeb9.css?family=Roboto:400,600,700%7CLato:400,700'
              type='text/css' media='all' />

        <link rel="stylesheet" type="text/css" href="assets/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="assets/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="assets/vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="assets/vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->


        <link rel="stylesheet" type="text/css" href="assets/css/libs/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/libs/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/libs/justifiedGallery.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/libs/magnific-popup.css">
        <link rel="stylesheet" type="text/css" href="assets/css/styles.css">
        <link rel="stylesheet" type="text/css" href="assets/css/util.css">
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    </head>

    <body class="home">

        <!-- Wrapper Site -->
        <div id="main-content">

            <!-- Preload -->
            <div id="preload">
                <div class="kd-bounce">
                    <div></div>
                    <div></div>
                </div>
            </div>
            <!-- Preload -->

            <div class="container">
                <div class="row container">

                    <div class="col-md-3">
                        <div class="header affix">
                            <div class="table">
                                <div class="table-cell">
                                    <!-- Logo -->
                                    <div class="logo">
                                        <a href="index.jsp">HUNG TUAN</a>
                                    </div>
                                    <!-- End Logo -->

                                    <!-- Navigation -->
                                    <div class="main-menu">
                                        <nav>
                                            <ul class="menu-list">

                                                <li class="active">
                                                    <a href="index.jsp">Home</a>
                                                </li>

                                                <c:if test="${sessionScope.USER.role == null}">
                                                    <li>
                                                        <a href="login.jsp">Login</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${sessionScope.USER.role == 'Admin'}">
                                                    <li>
                                                        <a href="admin.jsp">Welcome back, ${sessionScope.USER.name}</a>
                                                    </li>
                                                </c:if>
                                                <c:if test="${sessionScope.USER.role == 'User'}">
                                                    <li>
                                                        Welcome back, ${sessionScope.USER.name}
                                                    </li>
                                                </c:if>

                                                <c:if test="${sessionScope.USER.role eq null}">
                                                    <li>
                                                        <a href="register.jsp">Register</a>
                                                    </li>
                                                </c:if>

                                                <c:if test="${sessionScope.USER.role == 'User'}">
                                                    <li>
                                                        <a href="create-article.jsp">New Article</a>
                                                    </li>
                                                </c:if>

                                                <c:if test="${sessionScope.USER.role != null}">
                                                    <li>
                                                        <c:url var="LogoutLink" value="MainController">
                                                            <c:param name="action" value="Logout"/>
                                                        </c:url>
                                                        <a href="${LogoutLink}">Logout</a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div>
                                    <!-- End Navigation -->



                                    <div class="copyright">
                                        <p>
                                            Hung Tuan @ 2020. Create by Hung Tuan
                                        </p>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>