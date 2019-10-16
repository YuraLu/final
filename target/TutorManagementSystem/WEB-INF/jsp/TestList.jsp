<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text"/>
<c:import url="WEB-INF/forms/sign_in.jsp"/>
<html lang=" en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<div id="header">
    <div>
        <h1><fmt:message key="title.main"/></h1>
    </div>

    <div class="container">
        <div class="new-select-style-locale">
            <form style="display: inline; margin-left: 20px">
                <input type="hidden" name="action" value="main"/>
                <div class="new-select-style-locale" style="margin-left: 340px; margin-top: -25px; ">
                    <label for="locale"></label>
                    <select id="locale" name="locale" onchange="submit()">
                        <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                        <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                    </select>
                </div>
            </form>
        </div>
    </div>
</div>

<c:if test="${sessionScope.userId != null && sessionScope.userStatusId != null}">
    <center>
        <h1><fmt:message key="title.personal_cabinet"/></h1>
        <h2>
            <a href="new"><fmt:message key="add_test"/></a>
            <a href="list"><fmt:message key="test_list"/></a>
        </h2>
    </center>
</c:if>

<nav>
    <ul class="header" style="margin-right: -23%; margin-top: 3%">
        <c:if test="${sessionScope.userId != null && sessionScope.userStatusId != null}">
            <li>
                <a href="pages?command=logout">
                    <fmt:message key="button.logout"/>
                </a>
            </li>
            <li>
                <a href="pages?command=viewUserCabinet">
                    <fmt:message key="button.personal_cabinet"/>
                </a>
                <input type="hidden" name="userId" value="${sessionScope.userId}">
                <input type="hidden" name="userStatus" value="${sessionScope.userStatusId}">
            </li>
        </c:if>
        <c:if test="${sessionScope.userId == null}">
            <li>
                <a href="pages?command=goToRegistrationPage">
                    <fmt:message key="button.signUp"/>
                </a>
            </li>
            <li>

                <a href="" class="overlayLink">
                    <fmt:message key="button.signIn"/>
                </a>
            </li>
        </c:if>
    </ul>
</nav>

<hr>
<div class="content-wrapper">

    <%--    <div align="center">--%>
    <%--        <c:if test="${empty testList}">--%>
    <%--            <h2>List is empty</h2>--%>
    <%--        </c:if>--%>
    <%--        <c:if test="${not empty testList}">--%>
    <%--            <table border="1" cellpadding="5">--%>
    <%--                <caption><h2>List of Tests</h2></caption>--%>
    <%--                <tr>--%>
    <%--                    <th>ID</th>--%>
    <%--                    <th>Title</th>--%>
    <%--                    <th>Description</th>--%>
    <%--                    <th>Subject</th>--%>
    <%--                    <th>Author</th>--%>
    <%--                </tr>--%>
    <%--                <c:forEach var="test" items="${testList}">--%>
    <%--                    <tr>--%>
    <%--                        <td><c:out value="${test.id}"/></td>--%>
    <%--                        <td><c:out value="${test.title}"/></td>--%>
    <%--                        <td><c:out value="${test.description}"/></td>--%>
    <%--                        <td><c:out value="${test.subjectId}"/></td>--%>
    <%--                        <td><c:out value="${test.authorId}"/></td>--%>
    <%--                        <td>--%>
    <%--                            <a href="edit?id=<c:out value='${test.id}' />">Edit</a>--%>
    <%--                            <a href="delete?id=<c:out value='${test.id}' />">Delete</a>--%>
    <%--                        </td>--%>
    <%--                    </tr>--%>
    <%--                </c:forEach>--%>
    <%--            </table>--%>
    <%--        </c:if>--%>
</div>




<%--    <div align="center">--%>
<%--        <c:if test="${test != null}">--%>
<%--        <form action="update" method="post">--%>
<%--            </c:if>--%>
<%--            <c:if test="${test == null}">--%>
<%--            <form action="insert" method="post">--%>
<%--                </c:if>--%>
<%--                <table border="1" cellpadding="5">--%>
<%--                    <caption>--%>
<%--                        <h2>--%>
<%--                            <c:if test="${test != null}">--%>
<%--                                Edit test--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${test == null}">--%>
<%--                                Add New Test--%>
<%--                            </c:if>--%>
<%--                        </h2>--%>
<%--                    </caption>--%>
<%--                    <c:if test="${test != null}">--%>
<%--                        <input type="hidden" name="id" value="<c:out value='${test.id}' />"/>--%>
<%--                    </c:if>--%>
<%--                    <tr>--%>
<%--                        <th>Title:</th>--%>
<%--                        <td>--%>
<%--                            <input type="text" name="title" size="45"--%>
<%--                                   value="<c:out value='${test.title}'/>"--%>
<%--                            />--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <th>Description:</th>--%>
<%--                        <td>--%>
<%--                            <input type="text" name="description" size="45"--%>
<%--                                   value="<c:out value='${test.description}'/>"--%>
<%--                            />--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <th>Subject:</th>--%>
<%--                        <td>--%>
<%--                            <input type="text" name="subjectId" size="5"--%>
<%--                                   value="<c:out value='${test.subjectId}'/>"--%>
<%--                            />--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <th>Author:</th>--%>
<%--                        <td>--%>
<%--                            <input type="text" name="authorId" size="5"--%>
<%--                                   value="<c:out value='${test.authorId}'/>"--%>
<%--                            />--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td colspan="2" align="center">--%>
<%--                            <input type="submit" value="Add"/>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </table>--%>
<%--            </form>--%>
<%--    </div>--%>


</div>


<hr>
<div id="footer">
    <div class="end_page"><fmt:message key="footer.copyRight"/></div>
</div>
</body>
</html>