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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>a
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
        <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
        <div class="col-md-8 col-lg-6">
            <div id="localeDiv">
                <form>
                    <input type="hidden" name="command" value="viewSignUp"/>
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
                            <h3 class="login-heading mb-4"><fmt:message key="registration.welcome_message"/></h3>
                            <form action="controller" method="post">
                                <div class="form-label-group">
                                    <select name="role" class="custom-select select-box" id="FormControlSelect">
                                        <option selected><fmt:message key="role_name"/></option>
                                        <option value="STUDENT"><fmt:message key="student_title"/></option>
                                        <option value="TUTOR"><fmt:message key="tutor_title"/></option>
                                    </select>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="inputUserName" class="form-control" name="name"
                                           placeholder="<fmt:message key="registration.enter_name_message"/>"
                                           pattern=".{2,20}$" required autofocus/>
                                    <label for="inputUserName">
                                        <fmt:message key="registration.enter_name_message"/>
                                    </label>
                                    <small>${name_pattern}</small>
                                </div>
                                <div class="form-label-group">
                                    <input type="email" id="inputEmail" class="form-control" name="email"
                                           placeholder="<fmt:message key="registration.enter_email_message"/>"
                                           pattern="[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}$" required>
                                    <label for="inputEmail">
                                        <fmt:message key="registration.enter_email_message"/></label>
                                    <small>${email_pattern}</small>
                                </div>
                                <hr>
                                <div class="form-label-group">
                                    <input type="text" id="inputLogin" class="form-control" name="login"
                                           placeholder="<fmt:message key="registration.enter_login_message"/>"
                                           pattern="^[a-zA-Z][a-zA-Z0-9-_.]{3,16}$" required>
                                    <label for="inputLogin">
                                        <fmt:message key="registration.enter_login_message"/></label>
                                    <small>${login_pattern}</small>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="inputPassword" class="form-control" name="password"
                                           placeholder="<fmt:message key="registration.enter_password_message"/>"
                                           pattern="^[a-zA-Z][a-zA-Z0-9-_.]{6,16}$" required>
                                    <label for="inputPassword"><fmt:message
                                            key="registration.enter_password_message"/></label>
                                    <small>${password_pattern}</small>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="inputConfirmPassword" class="form-control" name="confirmedPassword"
                                           placeholder="<fmt:message key="registration.enter_password_message"/>"
                                           pattern="^[a-zA-Z][a-zA-Z0-9-_.]{6,16}$" required>
                                    <label for="inputConfirmPassword"><fmt:message
                                            key="registration.enter_confirm_password_message"/></label>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                        type="submit" name="command" value="signUp">
                                    <fmt:message key="button.register"/>
                                </button>
                                <p class="d-block text-center"><fmt:message key="registration.registered_promo"/>
                                <a class="mt-2 small" href="controller?command=viewSignIn">
                                    <fmt:message key="button.signIn"/>
                                </a>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${errorMessage !=null}">
    <script>
        showAlert("<fmt:message key="${errorMessage}"/>");
    </script>
</c:if>
<c:remove var="errorMessage"/>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery.slim.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>