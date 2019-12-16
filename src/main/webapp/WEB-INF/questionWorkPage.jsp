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

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>