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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>
        <fmt:message key="test.edit_title"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
            aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="controller?command=viewIndex"><fmt:message key="title.main"/></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:if test="${roleId == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=viewUserTable">User Table</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewTestTable">Test Table</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewSubjectTable">Subject Table</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewQuestionTable">Question Table</a>
            </li>
        </ul>

        <div class="nav-tabs " id="localeDivNav">
            <form>
                <input type="hidden" name="command" value="viewTestAddPage"/>
                <label for="locale"></label>
                <select id="locale" name="locale" onchange="submit()">
                    <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
        </div>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewUserCabinet">
                    <fmt:message key="title.personal_cabinet"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=signOut"><fmt:message key="button.signOut"/></a>
            </li>
        </ul>
    </div>
</nav>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.test.work_current"/></h2>
        </div>
        <form action="controller" method="post">
            <div class="col">
                <c:if test="${test != null}">
                    <div class="form-group">
                        <label for="inputTestAuthor"><fmt:message key="test_author"/></label>
                        <input type="text" class="form-control" name="testAuthor" id="inputTestAuthor"
                               value=" ${test.author.name}" disabled>
                        <input type="hidden" name="testAuthorId" value="${test.author.id}">
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="inputTestTitle"><fmt:message key="test_title"/></label>
                    <input type="text" class="form-control" name="testTitle" id="inputTestTitle"
                           value="${test.title}" placeholder="">
                </div>
                <div class="form-group">
                    <label for="inputTestDescription"><fmt:message key="test_title"/></label>
                    <input type="text" class="form-control" name="testDescription" id="inputTestDescription"
                           value="${test.description}" placeholder="">
                </div>

                <div class="form-group">
                    <h5><fmt:message key="test_subject"/></h5>
                    <select name="testSubjectId">
                        <option></option>
                        <c:forEach items="${subjectList}" var="subject">
                            <option value="${subject.id}"
                                    <c:if test="${subject.name == test.subject.name}">selected</c:if> >${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <h5><fmt:message key="test_question"/></h5>
                    <select name="testQuestionId">
                        <option></option>
<%--                        <c:forEach items="${test.questions}" var="question">--%>
<%--                            <option value="${question.id}"--%>
<%--                                    <c:if test="${question.questionText == test.question.questionText}">selected</c:if> >${question.questionText}</option>--%>
<%--                        </c:forEach>--%>
                    </select>

                </div>

            </div>

            <input type="hidden" name="testId" value="${test.id}">
            <div class="col">
                <c:if test="${test == null}">
                    <input type="hidden" name="testAuthorId" value="${userId}">
                    <button type="submit" name="command" value="addTest"
                            class="mt-2">
                        <strong><fmt:message key="button.add"/></strong>
                    </button>
                </c:if>
                <c:if test="${test != null}">
                    <input type="hidden" name="testAuthorId" value="${test.author.id}">
                    <input type="hidden" name="testSubjectId" value="${test.subject.id}">

                    <button type="submit" name="command" value="viewTestAddPage"
                            class="mt-2">
                        <strong><fmt:message key="button.add"/></strong>
                    </button>
                    <button type="submit" name="command" value="editTest"
                            class="mt-2">
                        <strong><fmt:message key="button.edit"/></strong>
                    </button>
                    <button type="submit" name="command" value="deleteTest"
                            class="mt-2">
                        <strong><fmt:message key="button.delete"/></strong>
                    </button>
                </c:if>
            </div>

        </form>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <span class="text-muted"><fmt:message key="footer.copyRight"/></span>
    </div>
</footer>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="js/jquery.slim.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>