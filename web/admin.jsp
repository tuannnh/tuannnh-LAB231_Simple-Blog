<%-- 
    Document   : index
    Created on : Jan 7, 2020, 7:14:46 PM
    Author     : tuannnh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>


        <div class="col-md-9 col-md-offset-3">

            <div class="projects main-content">
                <form action="MainController" class="contact-item" method="POST">
                    <i class="fa fa-search" aria-hidden="true"></i>
                    <input type="hidden" name="" value="" />
                    <input class="admin-search-input" type="text" placeholder="Search articles here, leave blank to view all articles."
                           aria-label="Search" name="txtSearchKeyword" value="${requestScope.SEARCH}">
                    <select name="txtStatus" class="admin-select">
                        <option value="New" <c:if test="${requestScope.STATUS eq 'New'}">selected</c:if> >New</option>
                        <option value="Active" <c:if test="${requestScope.STATUS eq 'Active'}">selected</c:if> >Active</option>
                        <option value="Deleted" <c:if test="${requestScope.STATUS eq 'Deleted'}">selected</c:if> >Deleted</option>
                    </select>
                    <input type="submit" hidden="true" value="SearchArticle" name="action"/>
                </form>

                <c:if test="${requestScope.ARTICLES == null}">
                    <h1 class="welcome-message">Welcome to administrator's page! Start search for articles :)</h1>
                </c:if>
                <c:if test="${requestScope.ARTICLES != null}">

                    <c:if test="${not empty requestScope.ARTICLES}">

                        <table  class="table table-hover table-bordered">
                            <thead>
                                <tr>
                                    <th  scope="col" class="text-center">No</th>
                                    <th  scope="col" class="text-center">Article</th>
                                    <th  scope="col" class="text-center">Status</th>
                                        <c:if test="${article.status eq 'New'}">
                                        <th  scope="col" class="text-center">Action</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.ARTICLES}" var="article" varStatus="counter">
                                    <tr class="align-middle">
                                        <td class="align-middle text-center">${(requestScope.PAGEID * 20)-20+counter.count}</td>
                                        <td>
                                            <div class="contact-item">
                                                <h2 class="title">
                                                    <c:url var="DetailLink" value="MainController">
                                                        <c:param name="action" value="ViewDetail"/>
                                                        <c:param name="txtId" value="${article.id}"/>
                                                    </c:url>
                                                    <a href="${DetailLink}"> ${article.title}</a>
                                                </h2>
                                                <!-- Post Details -->
                                                <div class="post-details">
                                                    ${article.dateCreateString} //
                                                    ${article.email}
                                                </div>
                                                <!-- End Post Details -->
                                                <p class="post-description">
                                                    ${article.shortDescription}
                                                </p>
                                            </div>
                                        </td>
                                        <td class="text-center">${article.status}</td>

                                        <c:if test="${article.status eq 'New'}">
                                            <td class="">
                                                <form action="MainController" method="POST" class="">
                                                    <input type="hidden" name="txtId" value="${article.id}"/>
                                                    <input type="hidden" name="txtSearchKeyword" value="${requestScope.SEARCH}"/>
                                                    <input type="hidden" name="txtPageId" value="${requestScope.PAGEID}"/>
                                                    <input type="hidden" name="txtStatus" value="${requestScope.STATUS}"/>
                                                    <input type="submit" class="btn-action btn btn-success glyphicon glyphicon-ok" name="action" value="Approve"/>
                                                </form>

                                                <form action="MainController" method="POST" class="mt-5">
                                                    <input type="hidden" name="txtId" value="${article.id}"/>
                                                    <input type="hidden" name="txtSearchKeyword" value="${requestScope.SEARCH}"/>
                                                    <input type="hidden" name="txtPageId" value="${requestScope.PAGEID}"/>
                                                    <input type="hidden" name="txtStatus" value="${requestScope.STATUS}"/>
                                                    <input type="submit" class="btn-action btn btn-danger" name="action" value="Delete"/>
                                                </form>
                                            </td>
                                        </c:if>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>




                        <!-- Pagination -->
                        <div class="pagination-wrap">
                            <ul>

                                <c:if test="${requestScope.PAGEID gt 1}">
                                    <li>

                                        <c:url var="PrevPageLink" value="MainController">
                                            <c:param name="action" value="SearchArticle"/>
                                            <c:param name="txtSearchKeyword" value="${requestScope.SEARCH}"/>
                                            <c:param name="txtPageId" value="${requestScope.PAGEID - 1}"/>
                                            <c:param name="txtStatus" value="${requestScope.STATUS}"/>

                                        </c:url>
                                        <a href="${PrevPageLink}" class="prev page-numbers">  
                                            <i class="fa fa-long-arrow-left"></i>
                                        </a>

                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${requestScope.TOTALPAGE}" varStatus="counter">
                                    <c:if test="${requestScope.PAGEID eq counter.count}">
                                        <li>
                                            <span class="page-numbers current">${requestScope.PAGEID}</span>
                                        </li>
                                    </c:if>
                                    <c:if test="${requestScope.PAGEID ne counter.count}">
                                        <li>
                                            <c:url var="ChangePageLink" value="MainController">
                                                <c:param name="action" value="SearchArticle"/>
                                                <c:param name="txtSearchKeyword" value="${requestScope.SEARCH}"/>
                                                <c:param name="txtPageId" value="${counter.count}"/>
                                                <c:param name="txtStatus" value="${requestScope.STATUS}"/>

                                            </c:url>
                                            <a href="${ChangePageLink}" class="page-numbers">  
                                                ${counter.count} 
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>

                                <c:if test="${requestScope.PAGEID lt requestScope.TOTALPAGE}">
                                    <li>

                                        <c:url var="NextPageLink" value="MainController">
                                            <c:param name="action" value="SearchArticle"/>
                                            <c:param name="txtSearchKeyword" value="${requestScope.SEARCH}"/>
                                            <c:param name="txtPageId" value="${requestScope.PAGEID + 1}"/>
                                            <c:param name="txtStatus" value="${requestScope.STATUS}"/>
                                        </c:url>
                                        <a href="${NextPageLink}" class="next page-numbers">  
                                            <i class="fa fa-long-arrow-right"></i>
                                        </a>

                                    </li>
                                </c:if>

                            </ul>

                        </div>
                        <!-- End Pagination -->

                    </c:if>
                    <c:if test="${empty requestScope.ARTICLES}">
                        <h1 class="search-fail">No result contains: "${requestScope.SEARCH}" and Status: "${requestScope.STATUS}". Please try again.</h1>

                    </c:if>
                </c:if>

            </div>
        </div>
    </div>
</div>
</div>



<%@include file="footer.jsp" %>

</body>
</html>
