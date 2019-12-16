<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>

<c:import url="/WEB-INF/form/addNewSubject.jsp"/>

<c:import url="WEB-INF/jsp/common/header.jsp">
    <c:param name="page_title" value="table.subject_title"/>
</c:import>
<body>
<c:import url="/WEB-INF/jsp/common/headerNav.jsp">
    <c:param name="paramRedirect" value="viewSubjectTable"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.subject_list"/></h2>
            <p><fmt:message key="page.subjectTable_intro"/></p>

            <div class="mb-3">
                <c:if test="${roleId == 1 || roleId == 3}">
                    <a href="" class="overlayLink btn btn-primary mb-3" role="button">
                        <fmt:message key="table.button_addSubject"/>
                    </a>
                </c:if>
            </div>
        </div>
        <form action="controller" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="subject_name"/></th>
                    <th><fmt:message key="table.chooseForAction"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${subjectList}" var="subject">
                    <tr>
                        <td> ${subject.name} </td>
                        <td>
                            <label>
                                <input type="radio" name="subjectForAction" value="${subject.id}" required>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="col">
                <button type="submit" name="command" value="deleteSubject" class="mt-2 btn btn-primary mb-3">
                    <strong><fmt:message key="button.delete"/></strong>
                </button>
            </div>
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