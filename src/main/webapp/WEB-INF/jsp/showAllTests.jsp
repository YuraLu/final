<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text" var="rb"/>

<%--<fmt:setLocale value="${sessionScope.local}" />--%>

<fmt:message bundle="${rb}" key="title.main" var="title_main"/>
<fmt:message bundle="${rb}" key="title.personal_cabinet" var="title_personal_cabinet"/>
<fmt:message bundle="${rb}" key="test.button_edit" var="test_edit"/>
<fmt:message bundle="${rb}" key="test.button_delete" var="test_delete"/>
<fmt:message bundle="${rb}" key="test_list" var="test_list"/>`
<fmt:message bundle="${rb}" key="test.welcome_message" var="test.welcome_message"/>
<fmt:message bundle="${rb}" key="test_id" var="test_id"/>
<fmt:message bundle="${rb}" key="test.page_add_title" var="addPage_title"/>
<fmt:message bundle="${rb}" key="test_title" var="test_title"/>
<fmt:message bundle="${rb}" key="test_description" var="test_description"/>
<fmt:message bundle="${rb}" key="test_subject" var="test_subject_id"/>
<fmt:message bundle="${rb}" key="test_author" var="test_author_id"/>
<fmt:message bundle="${rb}" key="test.button_add" var="test_add"/>
<fmt:message bundle="${rb}" key="test_added_message" var="added_test_success_message"/>

<fmt:message bundle="${rb}" key="registration.enter_login_message" var="login_message"/>
<fmt:message bundle="${rb}" key="registration.enter_password_message" var="password_message"/>

<fmt:message bundle="${rb}" key="button.register" var="button_register"/>
<fmt:message bundle="${rb}" key="button.go_home" var="button_home"/>
<fmt:message bundle="${rb}" key="button.personal_cabinet" var="button_personal_cabinet"/>
<fmt:message bundle="${rb}" key="button.logIn" var="button_login"/>
<fmt:message bundle="${rb}" key="button.logout" var="button_logout"/>


<fmt:message bundle="${rb}" key="message.logged_message" var="logged_message"/>
<fmt:message bundle="${rb}" key="message.logout_message" var="logout_message"/>
<html lang=" en">
<head>
    <meta charset="UTF-8">
    <title>${title_main}</title>
</head>
<body>
<div id="header">
    <div>
        <h1>${title_main}</h1>
    </div>

    <div class="container">
        <div class="new-select-style-locale">
            <form style="display: inline; margin-left: 20px">
                <input type="hidden" name="command" value="goToShowAllTestsPageCommand"/>
                <div class="new-select-style-locale" style="margin-left: 340px; margin-top: -25px; ">
                    <label for="locale"></label>
                    <select id="locale" name="locale" onchange="submit()">
                        <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                        <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                    </select>
                </div>
            </form>
        </div>
    </div>


    <c:if test="${sessionScope.userId != null}">
        <center>
            <h1>${title_personal_cabinet}</h1>
            <h2>
                <a href="pages?command=goToAddNewTest">${test_add}</a>
                <a href="pages?command=showAllTests">${test_list}</a>
            </h2>
        </center>
    </c:if>

    <nav>
        <ul class="header" style="margin-right: -23%; margin-top: 3%">
            <c:if test="${sessionScope.userId != null}">
                <li>
                    <a href="pages?command=logout">${button_logout}</a>
                </li>
                <li>
                    <a href="pages?command=viewUserCabinet">${button_personal_cabinet}</a>
                    <input type="hidden" name="userId" value="${sessionScope.userId}">
                </li>
            </c:if>

            <c:if test="${sessionScope.userId == null}">

                <div class="promt_window">
                    <div class="overlay" style="display: none;">
                        <div class="login-wrapper">
                            <div class="login-content" id="loginTarget">
                                <a class="close">x</a>
                                <h3> ${login_message} </h3>

                                <form method="post" action="pages">
                                    <label for="username">
                                            ${login_message}
                                        <input type="text" name="login" id="username"
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

                    <c:if test="${signInMessage !=null}">
                        <script>
                            showAlertMessage("<fmt:message key="${signInMessage}"/>");
                        </script>
                    </c:if>
                </div>
                <c:remove var="signInMessage"/>
                <a href="pages?command=goToRegistrationPage"> ${button_register} </a>
            </c:if>
        </ul>
    </nav>

    <table align="center" width="80%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="center" width="70">
                <form action="pages" method="post">
                    <input type="hidden" name="command" value="goToShowAllTestsPageCommand"/>
                    <input type="submit" size="50" value="${button_home}"/>
                </form>
            </td>
            <td align="center" width="70">
                <form action="pages" method="post">
                    <input type="hidden" name="command" value="goToRegistrationPage"/>
                    <input type="submit" value="${button_register}"/>
                </form>
            </td>
            <td align="right" width="70">
                <form action="pages" method="post">
                    <input type="hidden" name="command" value="goToLoginPage"/>
                    <input type="submit" value="${button_login}"/>
                </form>
            </td>
            <td align="right" width="300">
                <c:if test="${!empty sessionScope.userId}">
                    <c:out value="${logged_message } - "/>
                </c:if>
            </td>
            <td align="left" width="200">
                <c:if test="${!empty sessionScope.userId}">
                    <input type="button" value="${sessionScope.user.name}"/>
                </c:if>
            </td>
            <td align="right" width="70">
                <form action="pages" method="post">
                    <c:if test="${!empty sessionScope.userId}">
                        <input type="hidden" name="command" value="logOutCommand"/>
                        <input type="submit" value="${logout_message}"/>
                    </c:if>
                </form>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <hr>
</div>


<table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
    <caption><font size="6">${test_list}</font></caption>
    <tr>
        <th>${test_id}</th>
        <th>${test_title}</th>
        <th>${test_description}</th>
        <th>${test_subject_id}</th>
        <th>${test_author_id}</th>
    </tr>
    <c:forEach items="${sessionScope.testList}" var="test">
        <tr>
            <td align="center" width="75">
                <c:out value="${test.id}">
                </c:out>
            </td>
            <td align="center" width="100">
                <c:out value="${test.title}">
                </c:out>
            </td>
            <td align="center" width="100">
                <c:out value="${test.description}">
                </c:out>
            </td>
            <td align="center" width="100">
                <c:out value="${test.subjectId}">
                </c:out>
            </td>
            <td align="center" width="100">
                <c:out value="${test.authorId}">
                </c:out>
            </td>
<%--                <c:if--%>
<%--                        test="${!empty sessionScope.user && sessionScope.user.userRole == 'ADMIN'}">--%>
            <td>
                <a href="pages?command=testInfo&testId=<c:out value='${test.id}' />">${test_edit}</a>
                <a href="pages?command=deleteTest&testId=<c:out value='${test.id}' />">${test_delete}</a>
            </td>
<%--                </c:if>--%>
        </tr>
    </c:forEach>
</table>
<br>


<br>
<c:if
        test="${!empty sessionScope.user && sessionScope.user.userRole == 'ADMIN'}">
<form action="pages" method="post">
    <input type="hidden" name="command" value="goToAddNewTest"/>
    <input type="submit" value="${test_add}"/>
</form>
</c:if>
<br>
</body>
</html>