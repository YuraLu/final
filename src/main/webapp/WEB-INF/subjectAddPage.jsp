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
        <fmt:message key="test.edit_title"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
    <script src="js/alert.js"></script>

</head>
<body>

<c:import url="/WEB-INF/jsp/common/header_nav.jsp">
    <c:param name="paramRedirect" value="viewSubjectAddPage"/>
</c:import>
<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.subject.add_page"/></h2>
        </div>
        <form action="controller" method="post">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="subject_name"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <label>
                            <input type="text" name="subjectName" required>
                        </label>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="col">
                <button type="submit" name="command" value="addSubject"
                        class="mt-2">
                    <strong><fmt:message key="button.add"/></strong>
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