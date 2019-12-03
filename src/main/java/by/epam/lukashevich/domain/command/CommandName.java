package by.epam.lukashevich.domain.command;

public enum CommandName {

    VIEW_INDEX("viewIndex"),
    VIEW_SIGN_IN("viewSignIn"),
    VIEW_SIGN_UP("viewSignUp"),
    VIEW_USER_CABINET("viewUserCabinet"),
    VIEW_USER_TABLE("viewUserTable"),
    CHANGE_USER_STATUS("changeUserStatus"),
    CHANGE_USER_BAN_STATUS("changeUserBanStatus"),
    SIGN_IN("signIn"),
    SIGN_UP("signUp"),
    SIGN_OUT("signOut"),
    EDIT_USER("editUser"),

    VIEW_TEST_TABLE("viewTestTable"),
    VIEW_TEST_ADD_PAGE("viewTestAddPage"),
    VIEW_TEST_WORK_PAGE("viewTestWorkPage"),
    VIEW_PASS_TEST_PAGE("viewPassTestPage"),
    VIEW_PASS_TEST_QUESTION_PAGE("viewPassTestQuestionPage"),
    VIEW_PASS_TEST_RESULT_PAGE("viewPassTestResultPage"),
    ADD_TEST("addTest"),
    DELETE_TEST("deleteTest"),
    ABORT_TEST("abortTest"),
    FINISH_TEST("finishTest"),

    GET_NEXT_TEST_QUESTION("getNextTestQuestion"),

    VIEW_SUBJECT_TABLE("viewSubjectTable"),
    VIEW_SUBJECT_ADD_PAGE("viewSubjectAddPage"),
    ADD_SUBJECT("addSubject"),
    DELETE_SUBJECT("deleteSubject"),

    VIEW_QUESTION_TABLE("viewQuestionTable"),
    VIEW_QUESTION_ADD_PAGE("viewQuestionAddPage"),
    ADD_QUESTION("addQuestion"),
    DELETE_QUESTION("deleteQuestion"),
    VIEW_QUESTION_WORK_PAGE("viewQuestionWorkPage"),

    MISSING("missing");

    private final String name;

    CommandName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CommandName fromValue(String value) {
        for (CommandName item : CommandName.values()) {
            if (item.name.equalsIgnoreCase(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
