
package by.epam.lukashevich.domain.util.builder.user.impl;

import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.util.builder.impl.UserBuilderImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserBuilderImplTest {

    private final UserBuilderImpl builder = new UserBuilderImpl();
    private final UserBuilderImpl readyBuilder = new UserBuilderImpl(1);

    @Before
    public void init() {

        readyBuilder
                .withLogin("login")
                .withName("name")
                .withEmail("user@mail.ru")
                .withPassword("password")
                .isBanned(false)
                .withRole(Role.ADMIN);
    }

    @Test
    public void testWithLogin() {
        builder.withLogin("login");

        assertEquals(builder.getLogin(), readyBuilder.getLogin());
    }

    @Test
    public void testWithPassword() {
        builder.withPassword("password");

        assertEquals(builder.getPassword(), readyBuilder.getPassword());
    }

    @Test
    public void testWithEmail() {
        builder.withEmail("user@mail.ru");

        assertEquals(builder.getEmail(), readyBuilder.getEmail());
    }

    @Test
    public void testWithName() {
        builder.withName("name");

        assertEquals(builder.getName(), readyBuilder.getName());
    }


    @Test
    public void testWithUserStatus() {
        builder.withRole(Role.ADMIN);

        assertEquals(builder.getRole(), readyBuilder.getRole());
    }


    @Test
    public void testIsBanned() {
        builder.isBanned(false);

        assertFalse(builder.isBanned());
    }

    @Test
    public void testBuild() {
        User user = new User();
        user.setId(1);
        user.setLogin("login");
        user.setName("name");
        user.setEmail("user@mail.ru");
        user.setPassword("password");
        user.setBanned(false);
        user.setRole(Role.ADMIN);

        User result = readyBuilder.build();

        assertEquals(result, user);
    }
}