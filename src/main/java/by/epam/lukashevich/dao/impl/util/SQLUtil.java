package by.epam.lukashevich.dao.impl.util;

import by.epam.lukashevich.domain.entity.*;
import by.epam.lukashevich.domain.entity.user.Role;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.util.builder.impl.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class SQLUtil {

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
        int roleId = rs.getInt("u.roleId");
        boolean isBanned = rs.getBoolean("u.banned");

        Role role = Role.fromValue(roleId);

        return
                new UserBuilderImpl(id)
                        .withName(name)
                        .withEmail(email)
                        .withLogin(login)
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


    public static Assignment getAssignment(ResultSet rs) throws SQLException {
        int id = rs.getInt("as.id");
        int testId = rs.getInt("as.testId");
        int studentId = rs.getInt("as.studentId");
        int score = rs.getInt("as.score");
        Date date = rs.getDate("as.date");

        return
                new AssignmentBuilderImpl(id)
                        .withTest(new TestBuilderImpl(testId).build())
                        .withUser(new UserBuilderImpl(studentId).build())
                        .withScore(score)
                        .withDate(date)
                        .build();
    }

    public static Reply getReply(ResultSet rs) throws SQLException {
        int id = rs.getInt("r.id");
        int assignmentId = rs.getInt("r.assignmentId");
        int answerId = rs.getInt("r.answerId");
        int questionId = rs.getInt("r.questionId");

        List<Answer> answerList = new ArrayList<>();
        answerList.add(new AnswerBuilderImpl(answerId).build());

        return
                new ReplyBuilderImpl(id)
                        .withAssignmentId(assignmentId)
                        .withQuestion(new QuestionBuilderImpl(questionId).build())
                        .withAnswers(answerList)
                        .build();
    }


    public static List<Integer> getIdList(PreparedStatement st) throws SQLException {
        List<Integer> insertedIdsList = new ArrayList<>();
        ResultSet generatedKeys = st.getGeneratedKeys();

        while (generatedKeys.next()) {
            insertedIdsList.add(generatedKeys.getInt(1));
        }

        return insertedIdsList;
    }
}
