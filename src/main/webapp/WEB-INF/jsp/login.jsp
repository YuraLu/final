<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="locale"
	   value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
	   scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text" var="rb"/>

<%--<fmt:setLocale value="${sessionScope.local}" />--%>
<fmt:message bundle="${rb}" key="message.welcome_message" var="welcom_message" />

<fmt:message bundle="${rb}" key="login.login_pattern" var="login_pattern" />
<fmt:message bundle="${rb}" key="login.pass_pattern" var="pass_pattern" />
<fmt:message bundle="${rb}" key="login.wrong_params" var="login_wrong_params" />

<fmt:message bundle="${rb}" key="registration.enter_login_message" var="login_message"/>
<fmt:message bundle="${rb}" key="registration.enter_password_message" var="password_message"/>

<fmt:message bundle="${rb}" key="button.register" var="button_register"/>
<fmt:message bundle="${rb}" key="button.go_home" var="button_home"/>
<fmt:message bundle="${rb}" key="button.personal_cabinet" var="button_personal_cabinet"/>
<fmt:message bundle="${rb}" key="button.logIn" var="button_login"/>
<fmt:message bundle="${rb}" key="button.logout" var="button_logout"/>


<fmt:message bundle="${rb}" key="message.logged_message" var="logged_message"/>
<fmt:message bundle="${rb}" key="message.logout_message" var="logout_message"/>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

<div id="header">
	<div>
		<h1>${test_page_title}</h1>
	</div>

	<div class="container">
		<div class="new-select-style-locale">
			<form style="display: inline; margin-left: 20px">
				<input type="hidden" name="command" value="goToLoginPage"/>
				<div class="new-select-style-locale" style="margin-left: 340px; margin-top: -25px; ">
					<label for="locale"></label>
					<select id="locale" name="locale" onchange="submit()">
						<option value="en_EN" ${locale == 'en_EN' ? 'selected' : ''}>English</option>
						<option value="ru_RU" ${locale == 'ru_RU' ? 'selected' : ''}>Русский</option>
					</select>
				</div>
			</form>
		</div>
	</div>

	<c:if test="${sessionScope.userId != null}">
		<center>
			<h1>${title_personal_cabinet}</h1>
			<h2>
				<a href="pages?command=goToAddNewTest">${test_add}</a>
				<a href="pages?command=showAllTests">${test_list}</a>
			</h2>
		</center>
	</c:if>

	<nav>
		<ul class="header" style="margin-right: -23%; margin-top: 3%">
			<c:if test="${sessionScope.userId != null}">
				<li>
					<a href="pages?command=logout">${button_logout}</a>
				</li>
				<li>
					<a href="pages?command=viewUserCabinet">${button_personal_cabinet}</a>
					<input type="hidden" name="userId" value="${sessionScope.userId}">
				</li>
			</c:if>

			<c:if test="${sessionScope.userId == null}">

				<div class="promt_window">
					<div class="overlay" style="display: none;">
						<div class="login-wrapper">
							<div class="login-content" id="loginTarget">
								<a class="close">x</a>
								<h3> ${login_message} </h3>

								<form method="post" action="pages">
									<label for="username">
											${login_message}
										<input type="text" name="login" id="username"
											   placeholder="" required/>
									</label>
									<label for="password">
											${password_message}
										<input type="password" name="password" id="password"
											   placeholder="" required/>
									</label>

									<button type="submit" name="action" value="signIn">
										<strong>${login_message}</strong>
									</button>
								</form>
							</div>
						</div>
					</div>

					<c:if test="${signInMessage !=null}">
						<script>
							showAlertMessage("<fmt:message key="${signInMessage}"/>");
						</script>
					</c:if>
				</div>
				<c:remove var="signInMessage"/>
				<a href="pages?command=goToRegistrationPage"> ${button_register} </a>
			</c:if>
		</ul>
	</nav>

	<table align="center" width="80%" cellspacing="0" cellpadding="4">
		<tr>
			<td align="center" width="70">
				<form action="pages" method="post">
					<input type="hidden" name="command" value="goToShowAllTestsPageCommand"/>
					<input type="submit" size="50" value="${button_home}"/>
				</form>
			</td>
			<td align="center" width="70">
				<form action="pages" method="post">
					<input type="hidden" name="command" value="goToRegistrationPage"/>
					<input type="submit" value="${button_register}"/>
				</form>
			</td>
			<td align="right" width="70">
				<form action="pages" method="post">
					<input type="hidden" name="command" value="goToLoginPage"/>
					<input type="submit" value="${button_login}"/>
				</form>
			</td>
			<td align="right" width="300">
				<c:if test="${!empty sessionScope.userId}">
					<c:out value="${logged_message } - "/>
				</c:if>
			</td>
			<td align="left" width="200">
				<c:if test="${!empty sessionScope.userId}">
					<input type="button" value="${sessionScope.user.name}"/>
				</c:if>
			</td>
			<td align="right" width="70">
				<form action="pages" method="post">
					<c:if test="${!empty sessionScope.userId}">
						<input type="hidden" name="command" value="logOutCommand"/>
						<input type="submit" value="${logout_message}"/>
					</c:if>
				</form>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<hr>
</div>

	<h1>${welcom_message}</h1>

	<form action="pages" method="post">
		<table width="100%" cellspacing="0" cellpadding="4">
			<tr>
				<td align="right" width="100">${login_message}</td>
				<td><input type="text" size="49" name="login" required /></td>
			</tr>
			<tr>
				<td align="right">${password_message}</td>
				<td><input type="password" size="50" name="password" required /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="hidden" name="command" value="authorization">
					<input type="submit" value="${button_login}" /></td>
			</tr>
		</table>
	</form>

	<h3>
	<c:if test="${!empty requestScope.wrong_params}">
		<c:out value="${login_wrong_params}" />
		</c:if>
	</h3>


</body>
</html>