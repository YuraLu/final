package by.epam.lukashevich.domain.command;

public enum CommandName {

    SIGN_IN("signIn"),
    SIGN_UP("signUp"),
    SIGN_OUT("signOut"),
    MISSING("missing"),
    VIEW_SIGN_UP("viewSignUp"),

    VIEW_USER_CABINET("viewUserCabinet"),

    VIEW_USER_TABLE("viewUserTable"),

    CHANGE_USER_STATUS("changeUserStatus"),
    CHANGE_USER_BAN_STATUS("changeUserBanStatus"),

    VIEW_INDEX("viewIndex"),

    VIEW_TEST_TABLE("viewTestTable"),
    VIEW_TEST_ADD_PAGE("viewTestAddPage"),
    ADD_TEST("addTest"),
    EDIT_TEST("editTest"),
    DELETE_TEST("deleteTest"),
    VIEW_TEST_WORK_PAGE("viewTestWorkPage"),


    VIEW_SUBJECT_TABLE("viewSubjectTable"),
    VIEW_SUBJECT_ADD_PAGE("viewSubjectAddPage"),
    ADD_SUBJECT("addSubject"),
    DELETE_SUBJECT("deleteSubject"),

    VIEW_QUESTION_TABLE("viewQuestionTable"),
    VIEW_QUESTION_ADD_PAGE("viewQuestionAddPage"),
    ADD_QUESTION("addQuestion"),
    EDIT_QUESTION("editQuestion"),
    DELETE_QUESTION("deleteQuestion"),
    VIEW_QUESTION_WORK_PAGE("viewQuestionWorkPage");

   private String name;

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
