package by.epam.lukashevich.domain.entity.user;

public enum UserRole {
    ADMIN(1),
    STUDENT(2),
    TUTOR(3);
    private int roleId;

    UserRole(int id) {
        roleId = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public static UserRole fromValue(int value) {
        for (UserRole item : UserRole.values()) {
            if (item.roleId == value) {
                return item;
            }
        }
        throw new IllegalArgumentException(String.valueOf(value));
    }
}
