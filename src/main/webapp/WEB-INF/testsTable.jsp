<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>

<fmt:setBundle basename="text"/>

<c:import url="/WEB-INF/form/add_new_test.jsp"/>
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
        <fmt:message key="table.test_title"/>
    </title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="css/sticky-footer-navbar.css">
    <script src="js/alert.js"></script>

</head>
<body>

<c:import url="/WEB-INF/jsp/common/header_nav.jsp" >
    <c:param name="paramRedirect" value="viewTestTable"/>
</c:import>

<!-- Begin page content -->
<div class="container">
    <div class="m-t-1">
        <div class="col">
            <h2><fmt:message key="title.test_list"/></h2>
            <p><fmt:message key="title.testTable_intro"/></p>
            <div class="mb-3">
                <a href="" class="overlayLink btn btn-primary mb-3" role="button">
                    <fmt:message key="button.add"/>
                </a>
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

<c:import url="/WEB-INF/jsp/common/footer.jsp"/>

</body>
</html>