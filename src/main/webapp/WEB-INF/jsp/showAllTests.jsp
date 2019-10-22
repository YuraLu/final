<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<%--<c:import url="/WEB-INF/forms/sign_in.jsp"/>--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text" var="rb"/>

<fmt:message bundle="${rb}" key="title.main" var="title_main"/>
<fmt:message bundle="${rb}" key="title.personal_cabinet" var="title_personal_cabinet"/>
<fmt:message bundle="${rb}" key="button.edit" var="test_edit"/>
<fmt:message bundle="${rb}" key="test.button_delete" var="test_delete"/>
<fmt:message bundle="${rb}" key="test_list" var="test_list"/>`
<fmt:message bundle="${rb}" key="test.welcome_message" var="test.welcome_message"/>
<fmt:message bundle="${rb}" key="test_id" var="test_id"/>
<fmt:message bundle="${rb}" key="test.page_add_title" var="addPage_title"/>
<fmt:message bundle="${rb}" key="test_title" var="test_title"/>
<fmt:message bundle="${rb}" key="test_description" var="test_description"/>
<fmt:message bundle="${rb}" key="test_subject" var="test_subject_id"/>
<fmt:message bundle="${rb}" key="test_author" var="test_author_id"/>
<fmt:message bundle="${rb}" key="button.test_add" var="test_add"/>
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
        <nav>
            <c:if test="${user.id != null}">
                <h4>
                   ${logged_message } - ${user.name}(${user.id})
                </h4>

            </c:if>
            <ul class="header" style="margin-right: -23%; margin-top: 3%">
                <li>
                    <a href="${prev_request}">BACK</a>
                </li>
                <c:if test="${user.id != null}">
                    <li>
                        <a href="pages?command=logout">${button_logout}</a>
                    </li>
                    <li>
                        <a href="pages?command=viewPersonalCabinet">${button_personal_cabinet}</a>
                        <input type="hidden" name="userId" value="${user.id}">
                    </li>
                </c:if>

                <c:if test="${user.id == null}">
                    <li>
                        <a href="pages?command=goToRegistrationPage"> ${button_register} </a>
                    </li>
                    <%--                    <li>--%>
                    <%--                        <a href="" class="overlayLink"> ${login_message} </a>--%>
                    <%--                    </li>--%>
                </c:if>

                <c:if test="${user.id == null}">

                    <div class="promt_window">
                        <div class="overlay" style="display: block">
                            <div class="login-wrapper">
                                <div class="login-content" id="loginTarget">
                                        <%--                                    <a class="close">x</a>--%>
                                        <%--                                    <h3> ${login_message} </h3>--%>

                                    <form action="pages" method="post">
                                        <label for="login">
                                                ${login_message}
                                            <input type="text" name="login" id="login"
                                                   placeholder="${login_message}" required/>
                                        </label>
                                        <label for="password">
                                                ${password_message}
                                            <input type="password" name="password" id="password"
                                                   placeholder="" required/>
                                        </label>
                                        <button type="submit" name="command" value="authorization">
                                            <strong>${login_message}</strong>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </ul>
        </nav>
    </div>
</div>

<div class="main-content">

    <table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
        <caption><font size="6">${test_list}</font></caption>
        <tr>
            <th>${test_id}</th>
            <th>${test_title}</th>
            <th>${test_description}</th>
            <th>${test_subject_id}</th>
            <th>${test_author_id}</th>
        </tr>
        <c:forEach items="${testList}" var="test">
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
<%--                <c:if test="${sessionScope.userId !=null && sessionScope.role != 'STUDENT'}">--%>
                    <td>
                        <a href="pages?command=testInfo&testId=${test.id}/>">${test_edit}</a>
                        <a href="pages?command=deleteTest&testId=${test.id}/>">${test_delete}</a>
                    </td>
<%--                </c:if>--%>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <br>
</div>

<div class="footer">
    <hr>
    <p>@2019 Copyright Yuri L. </p>
</div>
</body>
</html>