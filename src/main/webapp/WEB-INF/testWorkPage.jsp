<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>

<c:import url="/WEB-INF/form/add_new_question.jsp"/>

<c:import url="WEB-INF/jsp/common/header.jsp">
    <c:param name="page_title" value="test.edit_title"/>
</c:import>
<body>
<c:import url="/WEB-INF/jsp/common/headerNav.jsp">
    <c:param name="paramRedirect" value="viewTestWorkPage"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <form action="controller" method="post">
            <div class="col">
                <h2><fmt:message key="title.test.work_current"/></h2>
            </div>

            <div class="col">
                <div class="form-group">
                    <label for="inputTestAuthor"><fmt:message key="test_author"/></label>
                    <input type="text" class="form-control" name="testAuthor" id="inputTestAuthor"
                           value=" ${test.author.name}" disabled>
                    <input type="hidden" name="testAuthorId" value="${test.author.id}">
                </div>
                <div class="form-group">
                    <label for="inputTestTitle"><fmt:message key="test_title"/></label>
                    <input type="text" class="form-control" name="testTitle" id="inputTestTitle"
                           value="${test.title}">
                </div>
                <div class="form-group">
                    <label for="inputTestDescription"><fmt:message key="test_description"/></label>
                    <input type="text" class="form-control" name="testDescription" id="inputTestDescription"
                           value="${test.description}">
                </div>

                <div class="form-group">
                    <h5><fmt:message key="test_subject"/></h5>
                    <label>
                        <select class="custom-select" name="testSubjectId">
                            <option></option>
                            <c:forEach items="${subjectList}" var="subject">
                                <option value="${subject.id}"
                                        <c:if test="${subject.name == test.subject.name}">selected</c:if> >${subject.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>

                <div class="form-group">
                    <h5><fmt:message key="test_question"/></h5>
                    <ul>
                        <c:forEach items="${test.questions}" var="question">
                            <li>
                                <a href="controller?command=viewQuestionWorkPage&questionId=${question.id}&testId=${test.id}"
                                   class="mt-2">
                                        ${question.questionText}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>

            <div class="col">
                <a href="" class="overlayLink btn btn-primary mb-3" role="button">
                    <fmt:message key="table.button_addQuestion"/>
                </a>
            </div>
            <hr>

            <input type="hidden" name="testId" value="${test.id}">
            <input type="hidden" name="testAuthorId" value="${test.author.id}">
            <input type="hidden" name="testSubjectId" value="${test.subject.id}">

            <button type="submit" name="command" value="deleteTest" class="mt-2">
                <strong><fmt:message key="button.delete"/></strong>
            </button>
        </form>
    </div>
</div>
<c:if test="${errorMessage != null}">
    <script>
        showAlert("<fmt:message key="${errorMessage}"/>");
    </script>
</c:if>
<c:remove var="errorMessage"/>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>