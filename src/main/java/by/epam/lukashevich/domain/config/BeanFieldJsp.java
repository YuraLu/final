package by.epam.lukashevich.domain.config;

public final class BeanFieldJsp {

    private BeanFieldJsp() {
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //USER
    public static final String USER_OBJECT = "user";
    public static final String USERS_LIST = "userList";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CONFIRMED_PASSWORD = "confirmedPassword";
    public static final String USER_NEW_PASSWORD = "newPassword";
    public static final String USER_ROLE = "role";
    public static final String USER_ROLE_ID = "roleId";
    public static final String USER_BANNED = "banned";
    public static final String USER_FOR_ACTION = "userForAction";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //TEST
    public static final String TEST_OBJECT = "test";
    public static final String TEST_LIST = "testList";
    public static final String TEST_ID = "testId";
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final String TEST_SUBJECT_ID = "testSubjectId";
    public static final String TEST_AUTHOR_ID = "testAuthorId";
    public static final String PASS_TEST_FINISH_STATUS = "passTestFinishStatus";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //SUBJECT
    public static final String SUBJECT_LIST = "subjectList";
    public static final String SUBJECT_NAME = "subjectName";
    public static final String SUBJECT_FOR_ACTION = "subjectForAction";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //ANSWER
    public static final String ANSWER_CORRECT = "answerCorrect[]";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //QUESTION
    public static final String QUESTION_OBJECT = "question";
    public static final String QUESTION_LIST = "questionList";
    public static final String QUESTION_ID = "questionId";
    public static final String QUESTION_TEXT = "questionText";
    public static final String QUESTION_ANSWERS = "questionAnswer[]";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //ASSIGNMENT
    public static final String ASSIGNMENT_OBJECT = "assignment";
    public static final String ASSIGNMENT_SCORE = "assignmentScore";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String NUMBER_OF_QUESTIONS = "numberOfQuestions";
    public static final String CURRENT_QUESTION_NUMBER = "currentQuestionNumber";
    public static final String REPLIES = "replies";
    //////////////////////////////////////////////////////////////////////////////
    public static final String ALLOWED = "allowed";
    public static final String SECURITY_MESSAGE = "securityMessage";
    public static final String MESSAGE_TO_JSP = "errorMessage";
    public static final String MESSAGE_TO_JSP_PASSWORD = "errorMessagePassword";
    public static final String MESSAGE_TO_SIGN_UP = "errorMessageSingUp";
    public static final String MESSAGE_TO_EDIT_USER = "errorMessageEditUser";
    public static final String MESSAGE_ERRORS_TO_JSP = "errors";
    public static final String REDIRECT_COMMAND = "redirectTo";
}