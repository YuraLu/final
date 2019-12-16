<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setBundle basename="text"/>

<c:import url="WEB-INF/jsp/common/header.jsp">
    <c:param name="page_title" value="title.error_page"/>
</c:import>
<body>

<!-- Begin page content -->

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div class="container">
    <div class="col m-auto">
        <a href="controller?command=viewIndex"><fmt:message key="button.go_home"/></a>
        <p>
            <fmt:message key="${errorMessage}"/>
        </p>
    </div>
</div>

<%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
