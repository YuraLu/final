<%--
  Created by IntelliJ IDEA.
  User: nykec
  Date: 04.10.2019
  Time: 0:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <script src="js/alert.js"></script>

</head>
<body>

<div class="promt_window">
    <div class="overlay" style="display: none;">
        <div class="login-wrapper">
            <div class="login-content" id="loginTarget">
                <a class="close">x</a>
                <h3><fmt:message key="button.logIn"/></h3>

                <form method="post" action="pages">
                    <label for="username">
                        <fmt:message key="enter_login_message"/>
                        <input type="text" name="login" id="username"
                               placeholder="" required/>
                    </label>
                    <label for="password">
                        <fmt:message key="enter_password_message"/>
                        <input type="password" name="password" id="password"
                               placeholder="" required/>
                    </label>

                    <button type="submit" name="action" value="signIn">
                        <strong> <fmt:message key="enter_message"/></strong>
                    </button>
                </form>
            </div>
        </div>
    </div>

    <c:if test="${signInMessage !=null}">
        <script>
            showAlertMessage("<fmt:message key="${signInMessage}"/>");
        </script>
    </c:if>
</div>

<c:remove var="signInMessage"/>
</body>
</html>
