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
        <title>${requestScope.ARTICLE.title}</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="col-md-9 col-md-offset-3">
            <div class="projects main-content">

                <article class="post">

                    <div class="post-content">
                        <h2 class="title">${requestScope.ARTICLE.title}</h2>

                        <!-- Post Details -->
                        <div class="post-details">
                            ${requestScope.ARTICLE.dateCreateString} //
                            ${requestScope.ARTICLE.email} //
                            ${requestScope.ARTICLE.numberComment} Comments
                            <blockquote>
                                <p>${requestScope.ARTICLE.shortDescription}</p>
                            </blockquote>
                        </div>
                        <!-- End Post Details -->

                        <!-- The Content -->
                        <div class="the-content">
                            <p>
                                ${requestScope.ARTICLE.content}
                            </p>


                        </div>
                        <!-- End The Content -->

                        <div id="comments">
                            <h2 class="title"> ${requestScope.ARTICLE.numberComment} Comments</h2>
                            <div class="comments-inner">
                                <ul class="comment-list">
                                    <c:forEach items="${requestScope.ARTICLE.commentCollection}" var="comment">
                                        <li class="comment">
                                            <div class="comment-head">
                                                <h5 class="comment-title">${comment.email} <span class="comment-date">${comment.commentDateString}</span></h5> 
                                            </div>
                                            <div class="comment-content">
                                                <p>${comment.content}</p>
                                            </div>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>

                            <!-- Respond -->
                            <div id="respond" class="comment-respond">
                                <h2 class="title">Leave a Reply</h2>
                                <form action="MainController" method="POST" class="comment-form contact">

                                    <div class="contact-item field-full form-message">
                                        <textarea name="txtContent" required="" placeholder="Your Comment ..."></textarea>
                                    </div>
                                    <div class="contact-item form-submit">
                                        <input type="hidden" name="txtId" value="${requestScope.ARTICLE.id}" />
                                        <input name="action" type="submit" id="submit" class="submit" value="Post Comment">
                                    </div>
                                </form>
                            </div><!-- #respond -->
                            <!-- End Respond -->
                        </div>
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
