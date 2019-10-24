package by.epam.lukashevich.domain.util;

import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.Role;

public interface UserBuilder {

    User build();

    UserBuilder withPassword(String password);

    UserBuilder withLogin(String login);

    UserBuilder withName(String name);

    UserBuilder withEmail(String email);

    UserBuilder withRole(Role role);

    UserBuilder withIsBanned(boolean banned);
}