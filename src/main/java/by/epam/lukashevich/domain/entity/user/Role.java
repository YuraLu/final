package by.epam.lukashevich.domain.entity.user;

public enum Role {
    ADMIN(1),
    STUDENT(2),
    TUTOR(3),
    GUEST(4);

    private static final long serialVersionUID = 1L;
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public static Role fromValue(int value) {
        for (Role item : Role.values()) {
            if (item.id == value) {
                return item;
            }
        }
        throw new IllegalArgumentException(String.valueOf(value));
    }

    public int getId() {
        return id;
    }
}