package by.epam.lukashevich.domain.util.impl;

import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.entity.user.UserRole;
import by.epam.lukashevich.domain.util.UserBuilder;

public class UserBuilderImpl implements UserBuilder {

    private int id;
    private String name;
    private String email;
    private String login;
    private String password;
    private UserRole role;
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
    public UserBuilder withRole(UserRole role) {
        this.role = role;
        return this;
    }

    @Override
    public UserBuilder withIsBanned(boolean banned) {
        this.banned = banned;
        return this;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isBanned() {
        return banned;
    }
}
