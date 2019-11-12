package by.epam.lukashevich.dao.util;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.util.builder.impl.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    private SQLUtil() {
    }

    public static Test getTest(ResultSet rs) throws SQLException {
        int id = rs.getInt("t.id");
        String title = rs.getString("t.title");
        String description = rs.getString("t.description");

        Subject subject = getSubject(rs);
        User author = getUser(rs);

        return
                new TestBuilderImpl(id).withTitle(title)
                        .withDescription(description)
                        .withSubject(subject)
                        .withAuthor(author)
                        .build();
    }

    public static User getUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("u.id");
        String name = rs.getString("u.name");
        String email = rs.getString("u.email");
        String login = rs.getString("u.login");
        String password = rs.getString("u.password");
        int roleId = rs.getInt("u.roleId");
        boolean isBanned = rs.getBoolean("u.banned");

        Role role = Role.fromValue(roleId);

        return
                new UserBuilderImpl(id).withName(name)
                        .withEmail(email)
                        .withLogin(login)
                        .withPassword(password)
                        .withRole(role)
                        .isBanned(isBanned)
                        .build();
    }

    public static Subject getSubject(ResultSet rs) throws SQLException {
        int id = rs.getInt("s.id");
        String name = rs.getString("s.name");

        return
                new SubjectBuilderImpl(id)
                        .withName(name)
                        .build();
    }

    public static Question getQuestion(ResultSet rs) throws SQLException {
        int id = rs.getInt("q.id");
        String text = rs.getString("q.text");

        return
                new QuestionBuilderImpl(id)
                        .withText(text)
                        .build();
    }

    public static Answer getAnswer(ResultSet rs) throws SQLException {
        int id = rs.getInt("a.id");
        String text = rs.getString("a.text");
        boolean isCorrect = rs.getBoolean("a.correct");

        return
                new AnswerBuilderImpl(id)
                        .withText(text)
                        .isCorrect(isCorrect)
                        .build();
    }
}
