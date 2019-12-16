<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="customtags" prefix="ctg" %>
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
    <c:param name="paramRedirect" value="viewPassTestPage"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="row px-lg-3 justify-content-lg-end">
            <div class="col-lg-9">
                <h2><fmt:message key="title.test.pass_test"/></h2>
            </div>

            <div class="col-lg-3-auto mx-2 px-2">
                <p>Today is <ctg:date/></p>
            </div>
        </div>
        <form action="controller" method="post">
            <div class="col">
                <div class="form-group">
                    <label for="inputTestTitle"><fmt:message key="test_title"/></label>
                    <input type="text" class="form-control" name="testTitle" id="inputTestTitle"
                           value="${assignment.test.title}" disabled>
                </div>
                <div class="form-group">
                    <label for="inputTestAuthor"><fmt:message key="test_author"/></label>
                    <input type="text" class="form-control" name="testAuthor" id="inputTestAuthor"
                           value=" ${assignment.test.author.name}" disabled>
                </div>
                <div class="form-group">
                    <label for="inputTestSubject"><fmt:message key="test_subject"/></label>
                    <input type="text" class="form-control" name="testSubject" id="inputTestSubject"
                           value="${assignment.test.subject.name}" disabled>
                </div>
                <div class="form-group">
                    <label for="inputTestDescription"><fmt:message key="test_description"/></label>
                    <input type="text" class="form-control" name="testDescription" id="inputTestDescription"
                           value="${assignment.test.description}" disabled>
                </div>
                <hr>
                <div class="form-group">
                    <h4><fmt:message key="test_question"/></h4>
                    <p>Total amount of questions: <strong>${numberOfQuestions}</strong></p>
                </div>
            </div>
            <div class="col">
                <button type="submit" name="command" value="getNextTestQuestion"
                        class="mt-2">
                    <strong><fmt:message key="button.start_test"/></strong>
                </button>

            </div>
        </form>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>