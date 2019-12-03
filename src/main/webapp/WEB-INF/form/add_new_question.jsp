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
                <h2><fmt:message key="title.question.add_page"/></h2>
                <form action="controller" method="post">
                    <div class="col">
                        <div class="form-group">
                            <label for="inputQuestionText"><fmt:message key="question_text"/></label>
                            <input type="text" class="form-control" name="questionText" id="inputQuestionText"
                                   value=" ${question.questionText}" placeholder="">
                        </div>
                        <div id="answerList" class="mb-3">
                            <div id="answer0" class="field-group">
                                <h5><fmt:message key="answer_text"/>:</h5>
                                <div class="inlineBox">
                                    <input class="" type="checkbox" name="answerCorrect[]" id="a1" value="1"/>
                                    <label for="a1" class="">This answer option is correct</label>
                                    <br/>
                                    <textarea name="questionAnswer[]" cols="95" rows="2"></textarea>
                                    <br/>
                                </div>
                            </div>
                            <div id="answer1" class="field-group">
                                <h5><fmt:message key="answer_text"/>:</h5>
                                <div class="inlineBox">
                                    <input class="" type="checkbox" name="answerCorrect[]" id="a2" value="2"/>
                                    <label for="a2" class="">This answer option is correct</label>
                                    <br/>
                                    <textarea name="questionAnswer[]" cols="95" rows="2"></textarea>
                                    <br/>
                                </div>
                            </div>
                            <div id="answer2" class="field-group">
                                <h5><fmt:message key="answer_text"/>:</h5>
                                <div class="inlineBox">
                                    <input class="" type="checkbox" name="answerCorrect[]" id="a3" value="3"/>
                                    <label for="a3" class="">This answer option is correct</label>
                                    <br/>
                                    <textarea name="questionAnswer[]" cols="95" rows="2"></textarea>
                                    <br/>
                                </div>
                            </div>
                            <div id="answer3" class="field-group">
                                <h5><fmt:message key="answer_text"/>:</h5>
                                <div class="inlineBox">
                                    <input class="" type="checkbox" name="answerCorrect[]" id="a4" value="4"/>
                                    <label for="a4" class="">This answer option is correct</label>
                                    <br/>
                                    <textarea name="questionAnswer[]" cols="95" rows="2"></textarea>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <input type="hidden" name="testId" value="${testId}">
                        <button type="submit" name="command" value="addQuestion" class="mt-2">
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
