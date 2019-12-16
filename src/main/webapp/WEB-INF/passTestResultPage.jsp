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
    <c:param name="paramRedirect" value="viewPassTestResultPage"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.test.result_page"/></h2>
        </div>
        <c:if test="${passTestFinishStatus == true}">
            <p><fmt:message key="result.results"/> ${assignmentScore}</p>
        </c:if>
        <hr>
        <p><fmt:message key="result.correct_answers"/> ${assignmentScore}
            <fmt:message key="result.questions_from"/> ${replies}.</p>
        <div class="col">
            <a class="btn btn-primary mb-3 " role="button" href="controller?command=viewTestTable">
                <fmt:message key="button.go_to_test_table"/>
            </a>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>