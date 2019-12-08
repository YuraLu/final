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
       <fmt:message key="nav.question_table"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
    <script src="js/alert.js"></script>
</head>
<body>

<c:import url="/WEB-INF/jsp/common/header_nav.jsp" >
    <c:param name="paramRedirect" value="viewQuestionTable"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.question_list"/></h2>
            <p><fmt:message key="page.questionTable_intro"/></p>
        </div>
        <form action="controller" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th><fmt:message key="table.question_text"/></th>
                    <th><fmt:message key="table.answer_option"/></th>
                    <th><fmt:message key="table.answer_option"/></th>
                    <th><fmt:message key="table.answer_option"/></th>
                    <th><fmt:message key="table.answer_option"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${questionList}" var="question">
                    <tr>
                        <td>
                            <a href="controller?command=viewQuestionWorkPage&questionId=${question.id}" class="mt-2">
                                <strong><fmt:message key="more_detail"/></strong>
                            </a>
                        </td>
                        <td> ${question.questionText} </td>
                        <c:forEach items="${question.answers}" var="answer">
                            <td<c:if test="${answer.isCorrect == true}"> class="bg-info" </c:if>> ${answer.answerText} </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>