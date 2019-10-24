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
    <title><fmt:message key="title.main"/></title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />


    </head>
<body>

<div id="header">
    <div>
        <h1><fmt:message key="title.main"/></h1>
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
                <li>
                    <a href="controller?command=viewUserCabinet"><fmt:message key="button.personal_cabinet"/></a>
                </li>
            </c:if>
            <c:if test="${userId == null}">
                <p>You are not registered user?</p>
                <li>
                    <a href="controller?command=viewSignUp"><fmt:message key="button.signUp"/></a>
                </li>
            </c:if>
        </ul>
    </nav>


    <div class="signInForm">
        <form action="controller" method="post">
            <label for="login">
                <fmt:message key="registration.enter_login_message"/>
                <input type="text" name="login" id="login" placeholder="" required/>
            </label>
            <label for="password">
                <fmt:message key="registration.enter_password_message"/>
                <input type="password" name="password" id="password" placeholder="" required/>
            </label>
            <button type="submit" name="command" value="signIn">
                <strong><fmt:message key="button.signIn"/></strong>
            </button>
        </form>
    </div>

</div>

<hr>
<div class="main-content">

    <p>Tutor Management system is very popular system to make student's assessment.</p>

</div>
<hr>
<div class="footer">
    <p>@2019 Copyright Yuri L. </p>
</div>


</body>
</html>
