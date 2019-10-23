<%@ page contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="locale"
	   value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
	   scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text" var="rb"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:message bundle="${rb}" key="error.error" var="error" />
	<fmt:message bundle="${rb}" key="error.info404" var="info404" />
	<fmt:message bundle="${rb}" key="button.go_home" var="button_home" />

	<br>
	<br>

	<H1>${error}404.</H1>
	<h2>${info404}</h2>

</body>
</html>