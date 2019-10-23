<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title><fmt:message key="userstable.caption"/></title>
</head>
<body>

<div id="header">
    <div>
        <h1><fmt:message key="title.main"/></h1>
    </div>
    <nav>
        <ul class="header">
            <li>
                <a href="index.jsp"><fmt:message key="button.go_home"/></a>
            </li>
            <c:if test="${userId != null}">
                <li>
                    <a href="controller?command=signOut"><fmt:message key="button.signOut"/></a>
                </li>
            </c:if>
            <c:if test="${userId == null}">
                <p>You are not registered user?</p>
                <li>
                    <a href="controller?command=viewSignUp"><fmt:message key="button.signUp"/></a>
                </li>
            </c:if>
            <li>
                <a href="controller?command=viewUserCabinet"><fmt:message key="button.personal_cabinet"/></a>
            </li>
        </ul>
    </nav>
</div>
<hr>
<div class="main-content">
    <h1><fmt:message key="userstable.caption"/></h1>
    <form action="controller" method="post">

        <table cellspacing="2" border="1" cellpadding="5" bordercolor="gray">
            <caption><span style="font-size: x-large; ">${test_list}</span></caption>
            <tr>
                <th><fmt:message key="table.message.user.userStatus"/></th>
                <th><fmt:message key="table.message.user.name"/></th>
                <th><fmt:message key="table.message.user.email"/></th>
                <th><fmt:message key="table.message.user.login"/></th>
                <th><fmt:message key="table.message.user.password"/></th>
                <th><fmt:message key="table.message.user.banned"/></th>
                <th><fmt:message key="table.chooseForAction"/></th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td align="center" width="100">
                        <c:if test="${user.role.roleId == 1}">
                            ADMIN
                        </c:if>
                        <c:if test="${user.role.roleId == 2}">
                            STUDENT
                        </c:if>
                        <c:if test="${user.role.roleId == 3}">
                            TUTOR
                        </c:if>
                    </td>
                    <td align="center" width="100"> ${user.name} </td>
                    <td align="center" width="100"> ${user.email} </td>
                    <td align="center" width="100"> ${user.login} </td>
                    <td align="center" width="100"> ${user.password} </td>
                    <td align="center" width="100"> ${user.banned} </td>
                    <td>
                        <label>
                            <input type="radio" name="userForAction" value="${user.id}">
                        </label>
                        <input type="hidden" name="userId" value="${user.id}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <button type="submit" name="command" value="changeUserStatus">
            <strong><fmt:message key="table.button.change_status"/></strong>
        </button>
        <button type="submit" name="command" value="changeUserBanStatus">
            <strong><fmt:message key="table.button.change_ban_status"/></strong>
        </button>
    </form>
</div>
<hr>
<div class="footer">
    <p>@2019 Copyright Yuri L. </p>
</div>
</body>
</html>
