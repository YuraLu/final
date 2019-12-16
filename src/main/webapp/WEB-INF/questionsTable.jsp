<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>

<c:import url="WEB-INF/jsp/common/header.jsp">
    <c:param name="page_title" value="title.question.edit_page"/>
</c:import>
<body>
<c:import url="/WEB-INF/jsp/common/headerNav.jsp">
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
                            <td<c:if
                                    test="${answer.isCorrect == true}"> class="bg-info" </c:if>> ${answer.answerText} </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>