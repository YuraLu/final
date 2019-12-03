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
    <title><fmt:message key="title.test.add_page"/></title>
</head>
<body>
<div class="promt_window">
    <div class="overlay" style="display: none;z-index: 2;">
        <div class="login-wrapper">
            <div class="login-content" id="loginTarget">
                <a class="close">x</a>
                <form action="controller" method="post">
                    <div class="col">
                        <div class="form-group">
                            <h2><fmt:message key="title.question.add_page"/></h2>
                        </div>
                    </div>
                    <div class="col">
                        <div class="form-group">
                            <label for="inputQuestionText"><fmt:message key="question_text"/></label>
                            <input type="text" class="form-control" name="questionText" id="inputQuestionText"
                                   placeholder="<fmt:message key="question_text"/>">
                        </div>
                        <div id="answerList" class="mb-3">
                            <h5><fmt:message key="answer_text"/>:</h5>
                            <h6><fmt:message key="table.question_add_answer_option.intro_text"/></h6>

                            <div id="answer0" class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <input type="checkbox" name="answerCorrect[]" id="a1" value="0"
                                               aria-label="Checkbox for following text input"/>
                                    </div>
                                </div>
                                <input type="text" name="questionAnswer[]" class="form-control"
                                       aria-label="Text input with checkbox"
                                       placeholder="<fmt:message key="table.question_add_answer.option"/>">
                            </div>

                            <div id="answer1" class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <input type="checkbox" name="answerCorrect[]" id="a2" value="1"
                                               aria-label="Checkbox for following text input"/>
                                    </div>
                                </div>
                                <input type="text" name="questionAnswer[]" class="form-control"
                                       aria-label="Text input with checkbox"
                                       placeholder="<fmt:message key="table.question_add_answer.option"/>">
                            </div>

                            <div id="answer2" class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <input type="checkbox" name="answerCorrect[]" id="a3" value="2"
                                               aria-label="Checkbox for following text input"/>
                                    </div>
                                </div>
                                <input type="text" name="questionAnswer[]" class="form-control"
                                       aria-label="Text input with checkbox"
                                       placeholder="<fmt:message key="table.question_add_answer.option"/>">
                            </div>

                            <div id="answer3" class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <input type="checkbox" name="answerCorrect[]" id="a4" value="3"
                                               aria-label="Checkbox for following text input"/>
                                    </div>
                                </div>
                                <input type="text" name="questionAnswer[]" class="form-control"
                                       aria-label="Text input with checkbox"
                                       placeholder="<fmt:message key="table.question_add_answer.option"/>">
                            </div>

                        </div>
                    </div>
                    <div class="col">
                        <input type="hidden" name="testId" value="${test.id}">
                        <button type="submit" name="command" value="addQuestion" class="btn btn-primary mb-3">
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
