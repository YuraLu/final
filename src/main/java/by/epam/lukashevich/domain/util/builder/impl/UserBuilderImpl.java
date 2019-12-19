package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.util.builder.UserBuilder;

public class UserBuilderImpl implements UserBuilder {

    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private Role role;
    private boolean banned;

    public UserBuilderImpl() {
    }

    public UserBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public User build() {
        final User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        user.setBanned(banned);
        return user;
    }

    @Override
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public UserBuilder isBanned(boolean banned) {
        this.banned = banned;
        return this;
    }
}

