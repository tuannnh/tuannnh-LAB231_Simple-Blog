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
        <title>Success</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="col-md-9 col-md-offset-3">
            <div class="projects main-content">

                <article class="post post-message">
                    <div class="post-content">
                        <!-- The Content -->
                        <div class="the-content">
                            <h2 class="title">THANK YOU!</h2>
                            <div class="post_404_not_found">
                                <div class="page_message_404">
                                    ${requestScope.SUCCESS_MESSAGE}
                                </div>

                                <c:if test="${requestScope.SUCCESS_TYPE eq null}">
                                    <div class="go-to-home">
                                        <a href="index.jsp">Back To Home</a>
                                    </div>
                                </c:if>
                            </div>
                            <c:if test="${requestScope.SUCCESS_TYPE ne null}">
                                <div class="go-to-home">
                                    <a href="login.jsp">Login</a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <!-- End The Content -->

            </div>

        </article>

    </div>
</div>
</div>
</div>
</div>




<%@include file="footer.jsp" %>
</body>
</html>
