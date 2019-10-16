<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text" var="rb"/>
<html lang=" en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.main"/></title>
</head>
<body>
<div id="header">
    <div>
        <h1><fmt:message key="title.main"/></h1>
    </div>

    <div class="container">
        <div class="new-select-style-locale">
            <form style="display: inline; margin-left: 20px">
                <input type="hidden" name="action" value="main"/>
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
</div>

<c:if test="${sessionScope.userId != null && sessionScope.userStatusId != null}">
    <center>
        <h1><fmt:message key="title.personal_cabinet"/></h1>
        <h2>
            <a href="new"><fmt:message key="add_test"/></a>
            <a href="list"><fmt:message key="test_list"/></a>
        </h2>
    </center>
</c:if>

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

            <div class="promt_window">
                <div class="overlay" style="display: none;">
                    <div class="login-wrapper">
                        <div class="login-content" id="loginTarget">
                            <a class="close">x</a>
                            <h3><fmt:message key="button.signIn"/></h3>

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
                                    <strong> <fmt:message key="button.signIn"/></strong>
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

            <a href="pages?command=goToRegistrationPage">
                <fmt:message key="button.signUp"/>
            </a>

        </c:if>
    </ul>
</nav>

<hr>



<fmt:message bundle="${rb}" key="test_description" var="test_description"/>
<fmt:message bundle="${rb}" key="test_subject" var="test_subject_id"/>
<fmt:message bundle="${rb}" key="test_author" var="test_author_id"/>
<fmt:message bundle="${rb}" key="add_test" var="add_test"/>
<fmt:message bundle="${rb}" key="button.go_home" var="home"/>

<fmt:message bundle="${rb}" key="default.page_from" var="from"/>
<fmt:message bundle="${rb}" key="default.page" var="page"/>
<fmt:message bundle="${rb}" key="default.next_page" var="next_page"/>
<fmt:message bundle="${rb}" key="default.previous_page" var="previous_page"/>
<fmt:message bundle="${rb}" key="enter_login_message" var="login"/>
<fmt:message bundle="${rb}" key="registration_message" var="registration"/>

<fmt:message bundle="${rb}" key="logged_message" var="logged_message"/>
<fmt:message bundle="${rb}" key="logout_message" var="logout_message"/>

<table align="center" width="80%" cellspacing="0" cellpadding="4">
    <tr>
        <td align="center" width="70">
            <form action="pages" method="post">
                <input type="hidden" name="command" value="goToShowAllTestsPageCommand">
                <input type="submit" size="50" value="${home}"/>
            </form>
        </td>

        <td align="center" width="70">
            <form action="pages" method="post">
                <input type="hidden" name="command" value="goToRegistrationPage">
                <input type="submit" value="${registration}"/>
            </form>
        </td>
        <td align="right" width="70">
            <form action="pages" method="post">
                <input type="hidden" name="command" value="goToLoginPage">
                <input type="submit" value="${login}"/>
            </form>
        </td>
        <td align="right" width="300">
            <c:if test="${!empty sessionScope.user}">
                <c:out value="${logged_message } - "/>
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
                    <input type="hidden" name="command" value="logOutCommand">
                    <input type="submit" value="${logout_message}"/>
                </c:if>
            </form>
        </td>
    </tr>
</table>
<br>
<br>


<table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
    <caption><font size="6">${test_list_title}</font></caption>

    <tr>
        <th>${test_title}</th>
        <th>${test_description}</th>
        <th>${test_subject_id}</th>
        <th>${test_author_id}</th>
    </tr>
    <c:set var="count" scope="request" value="${0}"></c:set>
    <c:forEach items="${sessionScope.testsList}" var="tests">
        <c:if
                test="${count >= sessionScope.allTestsFirstRow && count < sessionScope.allTestsLastRow}">
            <tr>
                <td align="center" width="75">
                    <c:out value="${tests.id}">
                    </c:out>
                </td>
                <td align="center" width="200">
                    <a href="pages?command=concreteTest&test_id=${tests.id}">${tests.title}
                    </a>
                </td>
                <td align="center" width="100">
                    <c:out value="${tests.description}">
                    </c:out>
                </td>
                <td align="center" width="100">
                    <c:out value="${tests.subjectId}">
                    </c:out>
                </td>
                <td align="center" width="75">
                    <c:out value="${tests.authorId}">
                    </c:out>
                </td>
            </tr>
        </c:if>
        <c:set var="count" value="${count + 1}"></c:set>
    </c:forEach>
</table>
<br>
<form action="pages" method="post">
    <c:if test="${count > sessionScope.allTestsLastRow}">
        <input type="hidden" name="first_row_parameter_name" value="allTestsFirstRow">
        <input type="hidden" name="last_row_parameter_name" value="allTestsLastRow">
        <input type="hidden" name="number_of_pages_parameter_name" value="allTestsNumberOfPages">
        <input type="hidden" name="current_page_number_parameter_name" value="allTestsCurrentPageNumber">
        <input type="hidden" name="current_page_URL" value="pages?command=goToShowAllTestsPageCommand">
        <input type="hidden" name="command" value="showNextPage">
        <input type="submit" value="${next_page }">
    </c:if>
</form>

<form action="pages" method="post">
    <c:if test="${sessionScope.allTestsFirstRow > 0}">
        <input type="hidden" name="first_row_parameter_name" value="allTestsFirstRow">
        <input type="hidden" name="last_row_parameter_name" value="allTestsLastRow">
        <input type="hidden" name="number_of_pages_parameter_name" value="allTestsNumberOfPages">
        <input type="hidden" name="current_page_number_parameter_name" value="allTestsCurrentPageNumber">
        <input type="hidden" name="current_page_URL" value="pages?command=goToShowAllTestsPageCommand">
        <input type="hidden" name="command" value="showPreviousPage">
        <input type="submit" value="${previous_page }">
    </c:if>
</form>

<h4>
    <c:out
            value="${page} ${sessionScope.allTestsCurrentPageNumber} ${from } ${sessionScope.allTestsNumberOfPages}"/>
</h4>
<br>
<c:if
        test="${!empty sessionScope.user && sessionScope.user.userRole == 'ADMIN'}">
    <form action="pages" method="post">
        <input type="hidden" name="command" value="goToAddNewTest"/>
        <input type="submit" value="${add_test}"/>
    </form>
</c:if>
<br>
</body>
</html>