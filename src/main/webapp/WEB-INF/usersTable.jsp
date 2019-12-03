<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>
        <fmt:message key="title.personal_cabinet"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
            aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="controller?command=viewIndex"><fmt:message key="nav.title_main"/></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:if test="${roleId == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=viewUserTable">
                        <fmt:message key="nav.user_table"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewTestTable">
                    <fmt:message key="nav.test_table"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewSubjectTable">
                    <fmt:message key="nav.subject_table"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewQuestionTable">
                    <fmt:message key="nav.question_table"/>
                </a>
            </li>
        </ul>

        <div class="nav-tabs " id="localeDivNav">
            <form>
                <input type="hidden" name="command" value="viewUserTable"/>
                <select id="locale" name="locale" onchange="submit()">
                    <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
        </div>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewUserCabinet">
                    <fmt:message key="nav.personal_cabinet"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=signOut"><fmt:message key="nav.button_signOut"/></a>
            </li>
        </ul>
    </div>
</nav>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.user_list"/></h2>
            <p><fmt:message key="title.userTable_intro"/></p>
        </div>
        <form action="controller" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th><fmt:message key="table.message.user.userStatus"/></th>
                    <th><fmt:message key="table.message.user.name"/></th>
                    <th><fmt:message key="table.message.user.email"/></th>
                    <th><fmt:message key="table.message.user.login"/></th>
                    <th><fmt:message key="table.message.user.banned"/></th>
                    <th><fmt:message key="table.chooseForAction"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>
                            <c:if test="${user.role.id == 1}"><fmt:message key="title.admin"/></c:if>
                            <c:if test="${user.role.id == 2}"><fmt:message key="title.student"/></c:if>
                            <c:if test="${user.role.id == 3}"><fmt:message key="title.tutor"/></c:if>
                        </td>
                        <td> ${user.name} </td>
                        <td> ${user.email} </td>
                        <td> ${user.login} </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.banned}"><fmt:message key="table.user_banned"/></c:when>
                                <c:otherwise><fmt:message key="table.user_not_banned"/></c:otherwise>
                            </c:choose>

                        </td>
                        <td>
                            <label>
                                <input type="radio" name="userForAction" value="${user.id}"/>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="col">
                <button type="submit" name="command" value="changeUserStatus"
                        class="mt-2">
                    <strong><fmt:message key="table.button.change_status"/></strong>
                </button>
                <button type="submit" name="command" value="changeUserBanStatus"
                        class="mt-2">
                    <strong><fmt:message key="table.button.change_ban_status"/></strong>
                </button>
            </div>

        </form>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <span class="text-muted"><fmt:message key="footer.copyRight"/></span>
    </div>
</footer>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.slim.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>