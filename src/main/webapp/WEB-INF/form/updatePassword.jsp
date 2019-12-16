<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="css/popUp.css" type="text/css"/>
    <script src="js/popUpWindow.js"></script>
    <title></title>
</head>
<body>
<div class="promt_window">
    <div class="overlay" style="display: none;z-index: 2;">
        <div class="login-wrapper">
            <div class="login-content" id="loginTarget">
                <a class="close">x</a>
                <h3><fmt:message key="title.password_update"/></h3>
                <form action="controller" method="post">
                    <div class="col">
                        <div class="form-group">
                            <label for="inputOldPassword">
                                <fmt:message key="registration.enter_current_password_message"/>
                            </label>
                            <input type="password" id="inputOldPassword" class="form-control" name="password"
                                   placeholder="<fmt:message key="registration.enter_current_password_message"/>"
                                   pattern="^[a-zA-Z][a-zA-Z0-9-_.]{6,16}$" required>
                            <small>${password_pattern}</small>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword">
                                <fmt:message key="registration.enter_new_password_message"/>
                            </label>
                            <input type="password" id="inputPassword" class="form-control" name="newPassword"
                                   placeholder="<fmt:message key="registration.enter_new_password_message"/>"
                                   pattern="^[a-zA-Z][a-zA-Z0-9-_.]{6,16}$" required>
                            <small>${password_pattern}</small>
                        </div>
                        <div class="form-group">
                            <label for="inputConfirmPassword"><fmt:message
                                    key="registration.enter_confirm_password_message"/></label>
                            <input type="password" id="inputConfirmPassword" class="form-control"
                                   name="confirmedPassword"
                                   placeholder="<fmt:message key="registration.enter_confirm_password_message"/>"
                                   pattern="^[a-zA-Z][a-zA-Z0-9-_.]{6,16}$" required>
                        </div>
                    </div>
                    <div class="col">
                        <button type="submit" name="command" value="updatePassword" class="btn btn-primary mb-3">
                            <strong><fmt:message key="button.change_password"/></strong>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>