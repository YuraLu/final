<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>
        <fmt:message key="title.main"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/signIn.css"/>
    <script src="js/alert.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image">
        </div>
        <div class="col-md-8 col-lg-6">
            <div id="localeDiv">
                <form>
                    <input type="hidden" name="command" value="viewSignIn"/>
                    <label for="locale"></label>
                    <select id="locale" name="locale" onchange="submit()">
                        <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                        <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                    </select>
                </form>
            </div>
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading mb-4"><fmt:message key="signIn_page.welcome"/></h3>
                            <form action="controller" method="post">
                                <div class="form-label-group">
                                    <input type="text" id="inputLogin" name="login" class="form-control"
                                           placeholder="Login" required autofocus>
                                    <label for="inputLogin">
                                        <fmt:message key="registration.enter_login_message"/>
                                    </label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="inputPassword" name="password" class="form-control"
                                           placeholder="Password" required>
                                    <label for="inputPassword">
                                        <fmt:message key="registration.enter_password_message"/>
                                    </label>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login
                                                    text-uppercase font-weight-bold mb-2"
                                        type="submit" name="command" value="signIn">
                                    <fmt:message key="button.signIn"/>
                                </button>
                                <div class="text-center">
                                    <a class="small" href="controller?command=viewSignUp">
                                        <fmt:message key="button.signUp"/>
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${errorMessage != null}">
    <script>
        showAlert("<fmt:message key="${errorMessage}"/>");
    </script>

    <c:if test="${errorMessage == 'message.successful_login'}">
        <c:remove var="errorMessage"/>
        <c:redirect url = "controller?command=viewUserCabinet"/>
    </c:if>
</c:if>
<c:remove var="errorMessage"/>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery.slim.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>