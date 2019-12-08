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
      <fmt:message key="title.question.edit_page"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
    <script src="js/alert.js"></script>
</head>
<body>

<c:import url="/WEB-INF/jsp/common/header_nav.jsp" >
    <c:param name="paramRedirect" value="viewQuestionWorkPage"/>
    <c:param name="questionId" value="${question.id}"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="table.question_work_page"/></h2>
        </div>
        <form action="controller" method="post">
            <div class="col">
                <div class="form-group">
                    <label for="inputQuestionText"><fmt:message key="question_text"/></label>
                    <input type="text" class="form-control" name="questionText" id="inputQuestionText"
                           value=" ${question.questionText}" placeholder="">
                </div>

                <div id="answerList" class="mb-3">
                    <c:forEach items="${question.answers}" var="answer" varStatus="a">
                        <div class="field-group">
                            <h5><fmt:message key="answer_text"/>:</h5>
                            <div class="inlineBox">
                                <input class="" type="checkbox" name="answerCorrect[]" id="${a.count}" value="1"
                                       <c:if test="${answer.isCorrect == true}">checked</c:if>/>
                                <label for="${a.count}" class="">This answer option is correct</label>
                                <br/>
                                <label>
                                    <textarea name="questionAnswer[]" cols="95" rows="2">${answer.answerText}</textarea>
                                </label>
                                <br/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <input type="hidden" name="questionId" value="${question.id}">
            <input type="hidden" name="testId" value="${testId}">
            <div class="col">
                <a href="controller?command=viewTestWorkPage&testId=${testId}" class="mt-2">
                    <strong><fmt:message key="button.back"/> </strong>
                </a>
                <button type="submit" name="command" value="deleteQuestion" class="mt-2">
                    <strong><fmt:message key="button.delete"/></strong>
                </button>

            </div>
        </form>
    </div>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>