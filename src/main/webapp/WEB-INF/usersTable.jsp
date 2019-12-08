<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>
        <fmt:message key="title.personal_cabinet"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
    <script src="js/alert.js"></script>

</head>
<body>

<c:import url="/WEB-INF/jsp/common/header_nav.jsp" >
    <c:param name="paramRedirect" value="viewUserTable"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.user_list"/></h2>
            <p><fmt:message key="title.userTable_intro"/></p>
        </div>
        <form action="controller" method="post">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th><fmt:message key="table.message.user.userStatus"/></th>
                    <th><fmt:message key="table.message.user.name"/></th>
                    <th><fmt:message key="table.message.user.email"/></th>
                    <th><fmt:message key="table.message.user.login"/></th>
                    <th><fmt:message key="table.message.user.banned"/></th>
                    <th><fmt:message key="table.chooseForAction"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>
                            <c:if test="${user.role.id == 1}"><fmt:message key="title.admin"/></c:if>
                            <c:if test="${user.role.id == 2}"><fmt:message key="title.student"/></c:if>
                            <c:if test="${user.role.id == 3}"><fmt:message key="title.tutor"/></c:if>
                        </td>
                        <td> ${user.name} </td>
                        <td> ${user.email} </td>
                        <td> ${user.login} </td>
                        <td>
                            <c:choose>
                                <c:when test="${user.banned}"><fmt:message key="table.user_banned"/></c:when>
                                <c:otherwise><fmt:message key="table.user_not_banned"/></c:otherwise>
                            </c:choose>

                        </td>
                        <td>
                            <label>
                                <input type="radio" name="userForAction" value="${user.id}"/>
                            </label>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="col">
                <button type="submit" name="command" value="changeUserStatus"
                        class="mt-2">
                    <strong><fmt:message key="table.button.change_status"/></strong>
                </button>
                <button type="submit" name="command" value="changeUserBanStatus"
                        class="mt-2">
                    <strong><fmt:message key="table.button.change_ban_status"/></strong>
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

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>

</body>
</html>