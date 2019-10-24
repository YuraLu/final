package by.epam.lukashevich.domain.util.config;

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
    public static final String USER_NEW_PASSWORD = "newPassword";
    public static final String USER_ROLE = "role";
    public static final String USER_ROLE_ID = "roleId";
    public static final String USER_STUDENT = "student";
    public static final String USER_TUTOR = "tutor";
    public static final String USER_FOR_ACTION = "userForAction";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //TEST
    public static final String TEST_OBJECT = "test";
    public static final String TEST_LIST = "testList";

    public static final String TEST_ID = "testId";
    public static final String TEST_TITLE = "testTitle";
    public static final String TEST_DESCRIPTION = "testDescription";
    public static final String TEST_SUBJECT = "testSubjectId";
    public static final String TEST_AUTHOR = "testAuthorId";
    public static final String TEST_FOR_ACTION = "testForAction";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //SUBJECT
    public static final String SUBJECT_LIST = "subjectList";

    public static final String SUBJECT_ID = "subjectId";
    public static final String SUBJECT_NAME = "subjectName";
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //ANSWER
    public static final String ANSWER_LIST = "answerList";

    public static final String ANSWER_ID = "answerId";
    public static final String ANSWER_NAME = "answerName";
    public static final String ANSWER_CORRECT = "answerCorrect";

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int ERROR_NUMBER_500 = 500;
    public static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
    public static final String PARAMETER_WRONG_PARAMS = "wrong_params";
    public static final String PARAMETER_COMMAND = "command";

    public static final Integer ERROR_NUMBER_403 = 403;

    public static final String REDIRECT_COMMAND = "redirectTo";
}