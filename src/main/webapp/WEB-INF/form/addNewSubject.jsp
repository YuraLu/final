<%@ page contentType="text/html;charset=windows-1251;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="locale"
       value="${not empty param.locale ? param.locale : not empty locale ? locale : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="css/popUp.css" type="text/css"/>
    <script src="js/popUpWindow.js"></script>
    <title><fmt:message key="title.subject.add_page"/></title>
</head>
<body>
<div class="promt_window">
    <div class="overlay" style="display: none;z-index: 2;">
        <div class="login-wrapper">
            <div class="login-content" id="loginTarget">
                <a class="close">x</a>
                <h3><fmt:message key="title.subject.add_page"/></h3>
                <form action="controller" method="post">
                    <div class="col">
                        <div class="form-group">
                            <label for="inputSubjectName"><fmt:message key="subject_name"/></label>
                            <input type="text" class="form-control" name="subjectName" id="inputSubjectName"
                                   value="" placeholder="">
                        </div>
                    </div>
                    <div class="col">
                        <button type="submit" name="command" value="addSubject" class="btn btn-primary mb-3">
                            <strong><fmt:message key="button.add"/></strong>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>