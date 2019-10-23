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
    <title>Registration page</title>
</head>
<body>

<div id="header">
    <div>
        <h1><fmt:message key="registration.welcome_message"/></h1>
    </div>
    <nav>
        <ul class="header">
            <li>
                <a href="index.jsp"><fmt:message key="button.go_home"/></a>
            </li>
            <c:if test="${user.id!= null}">
                <li>
                    <a href="controller?command=signOut"><fmt:message key="button.signOut"/></a>
                </li>
                <li>
                    <a href="controller?command=viewUserCabinet"><fmt:message key="button.personal_cabinet"/></a>
                </li>
            </c:if>
            <c:if test="${user.id == null}">
                <li>
                    <a href="index.jsp"><fmt:message key="button.signIn"/></a>
                </li>
            </c:if>
            <c:if test="${roleId == 1}">
                <li>
                    <a href="controller?command=viewUserTable">User Table</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
<hr>
<div class="main-content">
    <div class="signUpForm">
        <form action="controller" method="post">
            <table width="100%" cellspacing="0" cellpadding="4">
                <tr>
                    <td align="right" width="200" title=""><fmt:message key="role_name"/></td>
                    <td>
                        <label>
                            <select name="role">
                                <option value="STUDENT" selected><fmt:message key="student_title"/></option>
                                <option value="TUTOR"><fmt:message key="tutor_title"/></option>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="200" title="${name_pattern}">
                        <fmt:message key="registration.enter_name_message"/></td>
                    <td>
                        <label>
                            <input type="text" size="50" name="name" placeholder="name" pattern=".{2,20}$" required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td align="right" title="<${email_pattern}>">
                        <fmt:message key="registration.enter_email_message"/></td>
                    <td>
                        <label>
                            <input type="email" size="50" placeholder="email" name="email"
                                   pattern="^(\w+[\.-]?\w+[@][a-z]{2,10}[.][a-zA-Z]{2,4})$"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td align="right" width="100" title="${login_pattern}">
                        <fmt:message key="registration.enter_login_message"/></td>
                    <td>
                        <label>
                            <input type="text" size="50" name="login" placeholder="login"
                                   pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,20}$" required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td align="right" title="">
                        <fmt:message key="registration.enter_password_message"/></td>
                    <td>
                        <label>
                            <input type="password" size="50" name="password" placeholder="password"
                                   pattern="^[a-zA-Z][a-zA-Z0-9-_.]{6,15}$" required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit" name="command" value="signUp">
                            <strong><fmt:message key="button.signUp"/></strong>
                        </button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<hr>
<div class="footer">
    <p>@2019 Copyright Yuri L. </p>
</div>
</body>
</html>