<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="text"/>
<fmt:message bundle="${rb}" key="button.go_home" var="home"/>
<fmt:message bundle="${rb}" key="enter_login_message" var="login"/>
<fmt:message bundle="${rb}" key="registration_message" var="registration"/>


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



<table align="center" width="80%" cellspacing="0" cellpadding="4">
    <tr>
        <td align="center" width="70">
            <form action="pages" method="post">
                <input type="hidden" name="command" value="goToShowAllTestsPageCommand"/>
                <input type="submit" size="50" value="${home}"/>
            </form>
        </td>

        <td align="right" width="300">
            <c:if test="${!empty sessionScope.user}">
                <fmt:message key="logged_message"/>
            </c:if>
        </td>
        <td align="left" width="200">
            <c:if test="${!empty sessionScope.user}">
                <input type="button" value="${sessionScope.user.userName}"/>
            </c:if>
        </td>
        <td align="right" width="70">
            <form action="pages" method="post">
                <c:if test="${!empty sessionScope.user}">
                    <input type="hidden" name="command" value="logOutCommand"/>
                    <input type="submit" value="<fmt:message key="logout_message" />"/>
                </c:if>
            </form>
        </td>
    </tr>
</table>
<br>
<br>

<h1><fmt:message key="registration.welcome_message"/></h1>

<form action="pages" method="post">

    <table width="100%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="200" title="<fmt:message key="registration.name_pattern" />">
                <fmt:message key="registration.enter_name_message"/>
            </td>
            <td>
                <input type="text" size="50" name="name" pattern="^([A-Za-zА-Яа-яЁё]){3,44}$" required/>
            </td>
        </tr>
        <tr>
            <td align="right" title="<fmt:message key="registration.email_pattern"/>">
                <fmt:message key="registration.enter_email_message"/>
            </td>
            <td>
                <input type="text" size="50" name="email" pattern="^(\w+[\.-]?\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})$"
                       required/>
            </td>
        </tr>
        <tr>
            <td align="right" width="100" title="<fmt:message  key="login.login_pattern"/>">
                <fmt:message key="registration.enter_login_message"/>
            </td>
            <td>
                <input type="text" size="50" name="login" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,20}$" required/>
            </td>
        </tr>
        <tr>
            <td align="right" title="<fmt:message  key="login.pass_pattern" />">
                <fmt:message key="registration.enter_password_message"/>

            <td><input type="password" size="49" name="password" required/>
            </td>
        </tr>

        <%--			<tr>--%>
        <%--				<td align="right" title="${pass_pattern}">${enter_password}</td>--%>
        <%--				<td><input type="password" size="49" name="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$" required />--%>
        <%--				</td>--%>
        <%--			</tr>--%>
        <tr>
            <td></td>
            <td>
                <input type="hidden" name="command" value="registration"/>
                <input type="submit" value="<fmt:message key="button.registration"/>"/>
            </td>
        </tr>
    </table>

</form>


<fmt:message bundle="${rb}" key="registration.wrong_params" var="registration_wrong_params"/>
<h3>
    <c:if test="${!empty param.wrong_params}">
        <c:out value="${registration_wrong_params}"/>
    </c:if>
</h3>

</body>
</html>