<%-- 
    Document   : new-article
    Created on : Jan 9, 2020, 2:32:44 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new Article</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="col-md-9 col-md-offset-3">
            <div class="projects main-content">

                <h2 class="title">Post new Article</h2>
                <form action="MainController" method="POST" class="comment-form contact">
                    <div class="contact-item form-name">
                        <label class="article-lable">Title:</label> <input name="txtTitle" value="" type="text" required="" class="article-title">
                    </div>
                    <div class="contact-item form-url">
                      <label class="article-lable">Description:</label> <input id="url" name="txtDescription" value="" type="text" class="article-description" required="">
                    </div>
                    <div class="field-full form-message">
                      <label class="article-lable">Content:</label> <textarea name="txtContent" class="article-content" required=""></textarea>
                    </div>
                    <div class="contact-item form-submit">
                        <input name="action" type="submit" id="submit" class="submit" value="Post Article">
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
</div>



<%@include file="footer.jsp" %>
</body>
</html>
