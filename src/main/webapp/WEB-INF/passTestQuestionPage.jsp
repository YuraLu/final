<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>

<c:import url="WEB-INF/jsp/common/header.jsp">
    <c:param name="page_title" value="title.test.result_page"/>
</c:import>
<body>
<c:import url="/WEB-INF/jsp/common/headerNav.jsp">
    <c:param name="paramRedirect" value="viewPassTestQuestionPage"/>
</c:import>

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

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>