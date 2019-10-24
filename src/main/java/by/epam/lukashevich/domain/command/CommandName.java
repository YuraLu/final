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
    VIEW_TEST_EDIT_PAGE("viewTestEditPage"),
    ADD_TEST("addTest"),
    EDIT_TEST("editTest"),
    DELETE_TEST("deleteTest"),
    VIEW_TEST_ADD_PAGE("viewTestAddPage");

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
