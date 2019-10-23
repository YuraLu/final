package by.epam.lukashevich.domain.util;

import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.UserRole;

public interface UserBuilder {

    User build();

    UserBuilder withPassword(String password);

    UserBuilder withLogin(String login);

    UserBuilder withName(String name);

    UserBuilder withEmail(String email);

    UserBuilder withRole(UserRole role);

    UserBuilder withIsBanned(boolean banned);
}