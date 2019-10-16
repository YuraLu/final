<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="locale" var="rb"/>
	<fmt:message bundle="${rb}" key="locale.default.locale_button.en" var="locale_button_en" />
	<fmt:message bundle="${rb}" key="locale.default.locale_button.ru" var="locale_button_ru" />

	<fmt:message bundle="${rb}" key="locale.addNewTest.test_title" var="test_title"/>
	<fmt:message bundle="${rb}" key="locale.addNewTest.test_description" var="test_description"/>
	<fmt:message bundle="${rb}" key="locale.addNewTest.test_subject" var="test_subject_id"/>
	<fmt:message bundle="${rb}" key="locale.addNewTest.test_author" var="test_author_id"/>

	<fmt:message bundle="${rb}" key="locale.default.go_to_home_page" var="home" />
<%--	<fmt:message bundle="${rb}" key="locale.tests_info.rate_date" var="rate_date" />--%>
<%--	<fmt:message bundle="${rb}" key="locale.tests_info.rate_value" var="rate_value" />--%>
<%--	<fmt:message bundle="${rb}" key="locale.tests_info.rate_author" var="rate_author" />--%>
<%--	<fmt:message bundle="${rb}" key="locale.tests_info.rate_comment" var="rate_comment" />--%>
<%--	<fmt:message bundle="${rb}" key="locale.tests_info.rate_table_title" var="rate_table_title" />--%>
	<fmt:message bundle="${rb}" key="locale.default.page_from" var="from" />
	<fmt:message bundle="${rb}" key="locale.default.page" var="page" />
<%--	<fmt:message bundle="${rb}" key="locale.addNewRate.enter_add_rate" var="add_rate" />--%>
	<fmt:message bundle="${rb}" key="locale.default.next_page" var="next_page" />
	<fmt:message bundle="${rb}" key="locale.default.previous_page" var="previous_page" />
	<fmt:message bundle="${rb}" key="locale.default.go_to_home_page" var="home" />
	<fmt:message bundle="${rb}" key="enter_login_message" var="login" />
	<fmt:message bundle="${rb}" key="registration_message" var="registration" />
	<fmt:message bundle="${rb}" key="locale.default.logged_message" var="logged_message" />
	<fmt:message bundle="${rb}" key="locale.default.logout_message" var="logout_message" />
<%--	<fmt:message bundle="${rb}" key="locale.addNewRate.rate_added_message" var="added_rate_success_message" />--%>
<%--	<fmt:message bundle="${rb}" key="locale.addNewRate.double_add_message" var="double_add_rate_message" />--%>

	<table align="center" width="80%" cellspacing="0" cellpadding="4">
		<tr>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="goToShowAllTestsPageCommand"/>
					<input type="submit" size="50" value="${home}" />
				</form>
			</td>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="changeLocale"/> 
					<input type="hidden" name="locale" value="ru"/> 
					<input type="submit" size="50" value="${locale_button_ru}" />
				</form>
			</td>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="changeLocale"/> 
					<input type="hidden" name="locale" value="en"/> 
					<input type="submit" value="${locale_button_en}" />
				</form>
			</td>
			<td align="center" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="goToRegistrationPage"/>
					<input type="submit" value="${registration}" />
				</form>
			</td>
			<td align="right" width="70">
				<form action="Servlet" method="post">
					<input type="hidden" name="command" value="goToLoginPage"/>
					<input type="submit" value="${login}" />
				</form>
			</td>
			<td align="right" width="300">
				<c:if test="${!empty sessionScope.user}">
					<c:out value="${logged_message } - " />
				</c:if>
			</td>
			<td align="left" width="200">
				<c:if test="${!empty sessionScope.user}">
					<input type="button" value="${sessionScope.user.userFirstName}" />
				</c:if>
			</td>
			<td align="right" width="70">
				<form action="Servlet" method="post">
					<c:if test="${!empty sessionScope.user}">
						<input type="hidden" name="command" value="logOutCommand"/>
						<input type="submit" value="${logout_message}" />
					</c:if>
				</form>
			</td>
		</tr>
	</table>
	<br>
	<br>

	<table width="100%" cellspacing="0" cellpadding="4">

		<tr>
			<td align="left" width="30%">
				<c:out value="${test_title}" />
			</td>
			<td align="left" width="70%">
				<c:out value="${sessionScope.testInfo.testTitle}" />
			</td>
		</tr>
		<tr>
			<td align="left"><c:out value="${test_director}" />
			</td>
			<td align="left">
				<c:out value="${sessionScope.testInfo.testDirector}" />
			</td>
		</tr>
		<tr>
			<td align="left"><c:out value="${test_released_year}" />
			</td>
			<td align="left">
				<c:out value="${sessionScope.testInfo.testReleasedYear}" />
			</td>
		</tr>
		<tr>
			<td align="left"><c:out value="${test_rate}" />
			</td>
			<td align="left">
				<c:out value="${sessionScope.testInfo.testAVGRate}" />
			</td>
		</tr>

	</table>


	<table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
		
		<caption><font size="6">${rate_table_title}</font></caption>
		
		<tr>
		
			<th>${rate_date}</th>
			<th>${rate_value}</th>
			<th>${rate_author}</th>
			<th>${rate_comment}</th>
		</tr>
		<c:set var="count" scope="request" value="${0}"></c:set>
		<c:forEach items="${sessionScope.ratesList}" var="rates">
			<fmt:setLocale value="ru-RU}" />
			<fmt:formatDate pattern="dd-MM-yyyy" value="${rates.rateDate}" dateStyle="short" var="date" />
			<c:if test="${count >= sessionScope.concreteTestFirstRow && count < sessionScope.concreteTestLastRow}">
				<tr>
					<td align="center" width="200">
						<c:out value="${date}">
						</c:out>
					</td>
					<fmt:setLocale value="${sessionScope.local}" />
					<td align="center" width="75">
						<c:out value="${rates.rateValue}">
						</c:out>
					</td>
					<td align="center" width="200">
						<c:out value="${rates.userFirstName}">
						</c:out> 
						<c:out value="${rates.userLastName}">
						</c:out>
					</td>
					<td align="center" width="100">
						<c:out value="${rates.rateComment}">
						</c:out>
					</td>
				</tr>
			</c:if>
			<c:set var="count" value="${count + 1}"></c:set>
		</c:forEach>
	</table>
	<br>
	<form action="Servlet" method="post">
		<c:if test="${count > sessionScope.concreteTestLastRow}">
			<input type="hidden" name="first_row_parameter_name" value="concreteTestFirstRow"/>
			<input type="hidden" name="last_row_parameter_name" value="concreteTestLastRow"/>
			<input type="hidden" name="number_of_pages_parameter_name" value="concreteTestNumberOfPages"/>
			<input type="hidden" name="current_page_number_parameter_name" value="concreteTestNumberOfCurrentPage"/>
			<input type="hidden" name="current_page_URL" value="Servlet?command=goToShowConcreteTestInfoPageCommand"/>
			<input type="hidden" name="command" value="showNextPage"/> 
			<input type="submit" value="${next_page }"/>
		</c:if>
	</form>
	<form action="Servlet" method="post">
		<c:if test="${sessionScope.concreteTestFirstRow > 0}">
			<input type="hidden" name="first_row_parameter_name" value="concreteTestFirstRow"/>
			<input type="hidden" name="last_row_parameter_name" value="concreteTestLastRow"/>
			<input type="hidden" name="number_of_pages_parameter_name" value="concreteTestNumberOfPages"/>
			<input type="hidden" name="current_page_number_parameter_name" value="concreteTestNumberOfCurrentPage"/>
			<input type="hidden" name="current_page_URL" value="Servlet?command=goToShowConcreteTestInfoPageCommand"/>
			<input type="hidden" name="command" value="showPreviousPage"/> 
			<input type="submit" value="${previous_page }"/>
		</c:if>
	</form>

	<h3>
		<c:out
			value="${page } ${sessionScope.concreteTestNumberOfCurrentPage } ${from } ${sessionScope.concreteTestNumberOfPages}" />
	</h3>

	<br>

	<form action="Servlet" method="post">
		<input type="hidden" name="command" value="goToAddNewRate">
		<c:if test="${!empty sessionScope.user}">
			<input type="submit" value="${add_rate}" />
		</c:if>
	</form>

	<br>
	
	<h3>
		<c:if test="${!empty param.success_param}">
		<c:out value="${added_rate_success_message}" />
		</c:if>
	</h3>
	
	<h3>
		<c:if test="${!empty requestScope.double_add_param}">
		<c:out value="${double_add_rate_message}" />
		</c:if>
	</h3>

</body>
</html>