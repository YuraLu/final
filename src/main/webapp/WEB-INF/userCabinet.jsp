<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="button.personal_cabinet"/></title>
</head>
<body>

<div id="header">
    <div>
        <h1><fmt:message key="button.personal_cabinet"/></h1>
    </div>
    <nav>
        <ul class="header">
            <li>
                <a href="index.jsp"><fmt:message key="button.go_home"/></a>
            </li>
            <c:if test="${userId != null}">
                <li>
                    <a href="controller?command=signOut"><fmt:message key="button.signOut"/></a>
                </li>
            </c:if>
            <c:if test="${userId == null}">
                <p>You are not registered user?</p>
                <li>
                    <a href="controller?command=viewSignUp"><fmt:message key="button.signUp"/></a>
                </li>
            </c:if>
            <c:if test="${roleId == 1}">
                <li>
                    <a href="controller?command=viewUserTable">User Table</a>
                </li>
            </c:if>
            <li>
                <a href="controller?command=viewTestTable">Test Table</a>
            </li>
        </ul>
    </nav>
</div>

<hr>
<div class="main-content">


</div>
<hr>
<div class="footer">
    <p>@2019 Copyright Yuri L. </p>
</div>
</body>
</html>