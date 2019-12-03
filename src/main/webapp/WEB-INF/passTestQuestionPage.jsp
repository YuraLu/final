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
        <a class="navbar-brand" href="controller?command=viewIndex"><fmt:message key="nav.title_main"/></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <c:if test="${roleId == 1}">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=viewUserTable">
                        <fmt:message key="nav.user_table"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewTestTable">
                    <fmt:message key="nav.test_table"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewSubjectTable">
                    <fmt:message key="nav.subject_table"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewQuestionTable">
                    <fmt:message key="nav.question_table"/>
                </a>
            </li>
        </ul>

        <div class="nav-tabs " id="localeDivNav">
            <form>
                <input type="hidden" name="command" value="viewPassTestQuestionPage"/>
                <select id="locale" name="locale" onchange="submit()">
                    <option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
                </select>
            </form>
        </div>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=viewUserCabinet">
                    <fmt:message key="nav.personal_cabinet"/>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=signOut"><fmt:message key="nav.button_signOut"/></a>
            </li>
        </ul>
    </div>
</nav>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <form action="controller" method="post">
            <div class="row">
                <div class="col col-auto">
                    <h2>${question.questionText}</h2>
                    <h6><fmt:message key="title.test.question_page.intro"/></h6>
                </div>
                <div class="col col-auto ml-lg-auto">
                    <button class="mt-2" type="submit" name="command" value="abortTest">
                        <fmt:message key="button.abort_test"/>
                    </button>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <div>
                        <input type="hidden" name="questionId" value="${question.id}">
                        <hr>
                        <div class="field-group">
                            <c:forEach items="${question.answers}" var="answer" varStatus="id">
                                <div class="inlineBox">
                                    <input class="" type="checkbox" name="answerCorrect[]" id="${id.count}"
                                           value="${answer.id}"/>
                                    <label for="${id.count}" class="">${answer.answerText}</label>
                                    <br/>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <div class="col">
                <button type="submit" name="command" value="getNextTestQuestion" class="mt-2">
                    <strong>
                        <c:if test="${currentQuestionNumber < numberOfQuestions}">
                            <fmt:message key="button.next"/>
                        </c:if>
                        <c:if test="${currentQuestionNumber == numberOfQuestions}">
                            <fmt:message key="button.finish_test"/>
                        </c:if>
                    </strong>
                </button>
            </div>
        </form>
        <hr>
        <c:if test="${currentQuestionNumber != null}">
            <p>Question ${currentQuestionNumber} from ${numberOfQuestions}</p>
        </c:if>
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