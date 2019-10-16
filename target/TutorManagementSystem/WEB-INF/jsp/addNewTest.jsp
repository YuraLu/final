<%@ page contentType="text/html; charset=utf-8"
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
<fmt:setBundle basename="text" var="rb"/>
<fmt:message bundle="${rb}" key="addNewTest.welcome_message" var="welcome_message"/>

<fmt:message bundle="${rb}" key="test_title" var="test_title"/>
<fmt:message bundle="${rb}" key="test_description" var="test_description"/>
<fmt:message bundle="${rb}" key="test_subject" var="test_subject_id"/>
<fmt:message bundle="${rb}" key="test_author" var="test_author_id"/>
<fmt:message bundle="${rb}" key="add_test" var="add_test"/>
<fmt:message bundle="${rb}" key="test_added_message" var="added_test_success_message"/>

<fmt:message bundle="${rb}" key="button.go_home" var="home"/>
<fmt:message bundle="${rb}" key="enter_login_message" var="login"/>
<fmt:message bundle="${rb}" key="registration_message" var="registration"/>
<fmt:message bundle="${rb}" key="logged_message" var="logged_message"/>
<fmt:message bundle="${rb}" key="logout_message" var="logout_message"/>


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

<table align="center" width="80%" cellspacing="0" cellpadding="4">
    <tr>
        <td align="center" width="70">
            <form action="Servlet" method="post">
                <input type="hidden" name="command" value="goToShowAllMoviesPageCommand"/>
                <input type="submit" size="50" value="${home}"/>
            </form>
        </td>

        <td align="center" width="70">
            <form action="Servlet" method="post">
                <input type="hidden" name="command" value="goToRegistrationPage"/>
                <input type="submit" value="${registration}"/>
            </form>
        </td>
        <td align="right" width="70">
            <form action="Servlet" method="post">
                <input type="hidden" name="command" value="goToLoginPage"/>
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
            <form action="Servlet" method="post">
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

<h1>${welcome_message}</h1>

<form action="pages" method="post">
    <table width="100%" cellspacing="0" cellpadding="4">
        <tr>
            <td align="right" width="200">${test_title}</td>
            <td><input type="text" size="50" name="title" required/></td>
        </tr>
        <tr>
            <td align="right">${test_description}</td>
            <td><input type="text" size="50" name="description" required/></td>
        </tr>
        <tr>
            <td align="right" title="${test_subject_id}">${test_subject_id}</td>
            <td><input type="text" size="50" name="subjectId" pattern="[0-9]" required/></td>
        </tr>
		<tr>
			<td align="right" title="${test_author_id}">${test_author_id}</td>
			<td><input type="text" size="50" name="authorId" pattern="[0-9]" required/></td>
		</tr>
        <tr>
            <td></td>
            <td><input type="hidden" name="command" value="addNewMovie">
                <input type="submit" value="${add_test}"/></td>
        </tr>
    </table>
</form>

<h3>
    <c:if test="${!empty param.success_param}">
        <c:out value="${added_test_success_message}"/>
    </c:if>
</h3>

</body>
</html>