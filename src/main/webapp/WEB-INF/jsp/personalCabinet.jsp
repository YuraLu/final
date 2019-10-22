<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text" var="rb"/>

<fmt:message bundle="${rb}" key="registration.enter_name_message" var="enter_name"/>
<fmt:message bundle="${rb}" key="registration.enter_email_message" var="enter_email"/>
<fmt:message bundle="${rb}" key="registration.enter_login_message" var="login_message"/>
<fmt:message bundle="${rb}" key="registration.enter_password_message" var="password_message"/>
<fmt:message bundle="${rb}" key="registration.enter_new_password_message" var="new_password_message"/>
<fmt:message bundle="${rb}" key="test_list" var="test_list"/>

<fmt:message bundle="${rb}" key="button.go_home" var="button_home"/>
<fmt:message bundle="${rb}" key="button.logout" var="button_logout"/>
<fmt:message bundle="${rb}" key="button.edit" var="button_edit"/>
<fmt:message bundle="${rb}" key="button.test_add" var="test_add"/>

<fmt:message bundle="${rb}" key="button.subject_add" var="subject_add"/>
<fmt:message bundle="${rb}" key="button.question_add" var="question_add"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
</head>
<body>

<div id="header">
    <div>
        <h1>${test_page_title}</h1>
    </div>

    <div class="container">
        <div class="new-select-style-locale">
            <form style="display: inline; margin-left: 20px">
                <input type="hidden" name="command" value="goToShowTestInfoPageCommand"/>
                <div class="new-select-style-locale" style="margin-left: 340px; margin-top: -25px; ">
                    <label for="locale"></label>
                    <select id="locale" name="locale" onchange="submit()">
                        <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                        <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                    </select>
                </div>
            </form>
        </div>

        <nav>
            <c:if test="${sessionScope.userId != null}">
                <h4>
                    <c:out value="${logged_message } - ${sessionScope.name}(${sessionScope.userId})(Role - ${sessionScope.role})"/>
                </h4>

            </c:if>
            <ul class="header" style="margin-right: -23%; margin-top: 3%">
                <c:if test="${sessionScope.userId != null}">
                    <li>
                        <a href="pages?command=logout">${button_logout}</a>
                    </li>
                    <li>
                        <a href="pages?command=viewPersonalCabinet">${button_personal_cabinet}</a>
                        <input type="hidden" name="userId" value="${sessionScope.userId}">
                        <input type="hidden" name="role" value="${sessionScope.role}">
                    </li>
                    <li>
                        <a href="pages?command=showAllTests">${test_list}</a>
                    </li>
                    <li>
                        <a href="pages?command=goToAddNewTest">${test_add}</a>
                    </li>
                    <li>
                        <a href="pages?command=goToAddNewSubject">${subject_add}</a>
                    </li>
                    <li>
                        <a href="pages?command=goToAddNewQuestion">${question_add}</a>
                    </li>
                </c:if>


                <c:if test="${sessionScope.userId == null}">
                    <li>
                        <a href="pages?command=goToRegistrationPage"> ${button_register} </a>
                    </li>
                    <%--                    <li>--%>
                    <%--                        <a href="" class="overlayLink"> ${login_message} </a>--%>
                    <%--                    </li>--%>
                </c:if>

                <c:if test="${sessionScope.userId == null}">

                    <div class="promt_window">
                        <div class="overlay">
                            <div class="login-wrapper">
                                <div class="login-content" id="loginTarget">
                                    <a class="close">x</a>
                                    <h3> ${login_message} </h3>

                                    <form method="post" action="pages">
                                        <label for="login">
                                                ${login_message}
                                            <input type="text" name="login" id="login"
                                                   placeholder="" required/>
                                        </label>
                                        <label for="password">
                                                ${password_message}
                                            <input type="password" name="password" id="password"
                                                   placeholder="" required/>
                                        </label>

                                        <button type="submit" name="action" value="signIn">
                                            <strong>${login_message}</strong>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                    <c:remove var="signInMessage"/>
                    <a href="pages?command=goToRegistrationPage"> ${button_register} </a>
                </c:if>
            </ul>
        </nav>
    </div>
</div>


<div class="main-content">
    <form action="pages" method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    ${test_edit_title}
                </h2>
            </caption>
            <input type="hidden" name="userId" value="${user.id}"/>

            <tr>
                <th>${enter_name}:</th>
                <td>
                    <input type="text" name="email" value="${user.name}"/>

                </td>
            </tr>
            <tr>
                <th>${enter_email}:</th>
                <td>
                    <input type="text" name="email" value="${user.email}"/>
                </td>
            </tr>
            <tr>
                <th>${login_message}:</th>
                <td>
                    ${user.login}
                </td>
            </tr>
            <tr>
                <th>${password_message}:</th>
                <td>
                    ${user.password}
                </td>
            </tr>
            <tr>
                <th>${new_password_message}:</th>
                <td>
                    <input type="text" name="newPassword"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="hidden" name="command" value="editUser"/>
                    <input type="submit" value="${button_edit}"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="footer">
    <hr>
    <p>@2019 Copyright Yuri L. </p>
</div>
</body>
</html>