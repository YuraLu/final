package by.epam.lukashevich.domain.util.builder;

import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;

public interface UserBuilder {

    User build();

    UserBuilder withPassword(String password);

    UserBuilder withLogin(String login);

    UserBuilder withName(String name);

    UserBuilder withEmail(String email);

    UserBuilder withRole(Role role);

    UserBuilder isBanned(boolean banned);
}