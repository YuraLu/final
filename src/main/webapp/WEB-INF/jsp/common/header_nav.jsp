<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<head>
    <title></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
            aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="controller?command=viewIndex"><fmt:message key="nav.title_main"/></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:if test="${roleId == 1}">
                <li class="nav-item">
                    <a class="nav-link ${pageContext.request.requestURI.endsWith('/usersTable') ? 'active' : ''}"
                       href="controller?command=viewUserTable">
                        <fmt:message key="nav.user_table"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link ${pageContext.request.requestURI.endsWith('/testsTable') ? 'active' : ''}"
                   href="controller?command=viewTestTable">
                    <fmt:message key="nav.test_table"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${pageContext.request.requestURI.endsWith('/subjectsTable') ? 'active' : ''}"
                   href="controller?command=viewSubjectTable">
                    <fmt:message key="nav.subject_table"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link ${pageContext.request.requestURI.endsWith('/questionsTable') ? 'active' : ''}"
                   href="controller?command=viewQuestionTable">
                    <fmt:message key="nav.question_table"/>
                </a>
            </li>
        </ul>
        <div id="localeDiv" class="nav-item">
            <c:import url="/WEB-INF/jsp/common/localeDiv.jsp">
                <c:param name="paramRedirect" value="${param.paramRedirect}"/>
            </c:import>
        </div>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link ${pageContext.request.requestURI.endsWith('/userCabinet') ? 'active' : ''}"
                   href="controller?command=viewUserCabinet">
                    <fmt:message key="nav.personal_cabinet"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=signOut"><fmt:message key="nav.button_signOut"/></a>
            </li>

        </ul>
    </div>
</nav>
</body>
</html>