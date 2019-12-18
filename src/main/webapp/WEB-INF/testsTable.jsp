<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>

<c:import url="/WEB-INF/form/addNewTest.jsp"/>

<c:import url="WEB-INF/jsp/common/header.jsp">
    <c:param name="page_title" value="title.test_title"/>
</c:import>
<body>
<c:import url="/WEB-INF/jsp/common/headerNav.jsp">
    <c:param name="paramRedirect" value="viewTestTable"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.test_list"/></h2>
            <p><fmt:message key="title.testTable_intro"/></p>
            <div class="mb-3">
                <c:if test="${roleId == 1 || roleId == 3}">
                    <a href="" class="overlayLink btn btn-primary mb-3" role="button">
                        <fmt:message key="button.add"/>
                    </a>
                </c:if>
            </div>
        </div>
        <form action="controller" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th></th>
                    <th><fmt:message key="test_title"/></th>
                    <th><fmt:message key="test_description"/></th>
                    <th><fmt:message key="test_subject"/></th>
                    <th><fmt:message key="test_author"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${testList}" var="test">
                    <tr>
                        <td>
                            <a href="controller?command=viewTestWorkPage&testId=${test.id}" class="mt-2">
                                <strong><fmt:message key="more_detail"/></strong>
                            </a>
                        </td>
                        <td> ${test.title} </td>
                        <td> ${test.description} </td>
                        <td> ${test.subject.name} </td>
                        <td> ${test.author.name} </td>
                        <td>
                            <a href="controller?command=viewPassTestPage&testId=${test.id}" class="mt-2">
                                <strong><fmt:message key="pass_test"/></strong>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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