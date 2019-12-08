package by.epam.lukashevich.dao.impl.util;

public final class SQLQuery {
    private SQLQuery() {
    }

    public static final String GET_TEST_BY_ID = "SELECT " +
            "t.id, t.title, t.description, " +
            "s.id, s.name," +
            "u.id, u.name, u.email, u.login, u.password, u.roleId, u.banned" +
            " FROM tests t" +
            " INNER JOIN subjects s on s.id = t.subjectId " +
            " INNER JOIN users u on u.id = t.authorId " +
            " WHERE t.id=?";

    public static final String GET_ALL_TESTS = "SELECT " +
            "t.id, t.title, t.description, " +
            "s.id, s.name," +
            "u.id, u.name, u.email, u.login, u.password, u.roleId, u.banned" +
            " FROM tests t" +
            " INNER JOIN subjects s on s.id = t.subjectId " +
            " INNER JOIN users u on u.id = t.authorId ";

    public static final String ADD_NEW_TEST = "INSERT INTO tests " +
            "(title, description, subjectId, authorId) " +
            "VALUES(?,?,?,?)";

    public static final String UPDATE_TEST = "UPDATE tests SET " +
            "title=?, description =?, subjectId=?" +
            " WHERE id = ?";

    public static final String GET_TEST_BY_TITLE = "SELECT id FROM tests where title=?";

    public static final String DELETE_TEST = "DELETE FROM tests WHERE id = ?";

    public static final String ADD_QUESTIONS_LIST_FOR_TEST_ID = "INSERT INTO testquestions " +
            "(testId, questionId) VALUES(?,?)";

    /////////////////////////
    public static final String GET_USER_BY_ID = "SELECT " +
            "u.id, u.name, u.email, u.login, u.roleId, u.banned" +
            " FROM users u" +
            " WHERE u.id=?";

    public static final String GET_ALL_USERS = "SELECT " +
            "u.id, u.name, u.email, u.login, u.roleId, u.banned" +
            " FROM users u" +
            " INNER JOIN roles r" +
            " ON u.roleId = r.id";

    public static final String ADD_NEW_USER = "INSERT INTO users " +
            "(name, email, login, password, roleId, banned) " +
            "VALUES(?,?,?,?,?,?)";

    public static final String GET_USER_BY_LOGIN = "SELECT id FROM users where login=?";

    public static final String GET_USER_BY_LOGIN_AND_PASS = "SELECT " +
            "u.id, u.name, u.email, u.login, u.roleId, u.banned" +
            " FROM users u" +
            " WHERE u.login=? and u.password=?";

    public static final String UPDATE_USER_STATUS = "UPDATE users SET roleId = ? WHERE id = ?";

    public static final String UPDATE_USER_BAN_STATUS = "UPDATE users SET banned = ? WHERE id = ?";

    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";

    public static final String UPDATE_USER = "UPDATE users SET " +
            "name=?, email =? WHERE id = ?";

    public static final String DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    ////////////////////////////////////////
    public static final String GET_ALL_SUBJECTS = "SELECT s.id, s.name FROM subjects s";
    public static final String GET_SUBJECT_BY_ID = "SELECT s.id, s.name FROM subjects s WHERE s.id=?";
    public static final String GET_SUBJECT_BY_NAME = "SELECT s.id FROM subjects s WHERE s.name=?";
    public static final String ADD_NEW_SUBJECT = "INSERT INTO subjects (name) VALUES(?)";
    public static final String DELETE_SUBJECT = "DELETE FROM subjects WHERE id = ?";
    //////////////////////////
    public static final String GET_QUESTION_BY_ID = "SELECT " +
            "q.id, q.text, " +
            "a.id, a.text, a.correct" +
            " FROM questions q" +
            " INNER JOIN answergroups ag ON ag.questionId = q.id " +
            " INNER JOIN answers a on a.id = ag.answerId" +
            " WHERE q.id = ? ";

    public static final String GET_ALL_QUESTIONS = "SELECT " +
            "q.id, q.text, " +
            "a.id, a.text, a.correct" +
            " FROM questions q" +
            " INNER JOIN answergroups ag ON ag.questionId = q.id " +
            " INNER JOIN answers a on a.id = ag.answerId ";

    public static final String ADD_NEW_QUESTION = "INSERT INTO questions (text) VALUES(?)";

    public static final String GET_QUESTION_BY_TEXT = "SELECT q.id FROM questions q WHERE q.text=?";

    public static final String DELETE_QUESTION = "DELETE FROM questions WHERE id = ?";

    public static final String GET_ALL_QUESTIONS_BY_TEST_ID = "SELECT " +
            "q.id, q.text" +
            " FROM testquestions tq" +
            " INNER JOIN questions q ON tq.questionId = q.id " +
            " WHERE tq.testId=?";

    ////////////////////////
    public static final String GET_ANSWER_BY_ID = "SELECT " +
            "a.id," +
            "a.text," +
            "a.correct" +
            " FROM answers a" +
            " WHERE a.id=?";

    public static final String GET_ALL_ANSWERS = "SELECT " +
            "a.id," +
            "a.text," +
            "a.correct" +
            " FROM answers a";

    public static final String ADD_NEW_ANSWER = "INSERT INTO answers " +
            "(text, correct) " +
            "VALUES(?,?)";

    public static final String UPDATE_ANSWER = "UPDATE answers SET " +
            "text=?, " +
            "correct =? " +
            " WHERE id = ?";

    public static final String GET_ANSWER_BY_TEXT = "SELECT id FROM answers where text=?";

    public static final String DELETE_ANSWER = "DELETE FROM answers where id = ?";

    public static final String GET_ALL_ANSWERS_BY_QUESTION_ID = "SELECT " +
            "a.id," +
            "a.text," +
            "a.correct" +
            " FROM answergroups ag" +
            " INNER JOIN answers a ON ag.answerId = a.id " +
            " WHERE ag.questionId=?";

    public static final String ADD_ANSWERS_LIST_FOR_QUESTION_ID = "INSERT INTO answergroups " +
            "(answerId, questionId) VALUES(?,?)";

    ////////////////////////
    public static final String GET_ASSIGNMENT_BY_ID = "SELECT " +
            "as.id, as.testId, as.studentId, as.score, as.date" +
            " FROM assignments as" +
            " WHERE as.id=?";

    public static final String GET_ALL_ASSIGNMENTS = "SELECT " +
            "as.id, as.testId, as.studentId, as.score, as.date" +
            " FROM assignments as";

    public static final String ADD_NEW_ASSIGNMENT = "INSERT INTO assignments " +
            "(testId, studentId, score, date) " +
            "VALUES(?,?,?,?)";

    public static final String UPDATE_ASSIGNMENT = "UPDATE assignments SET " +
            "score=?" +
            " WHERE id = ?";

    public static final String DELETE_ASSIGNMENT = "DELETE FROM assignments where id = ?";

    public static final String GET_ALL_ASSIGNMENTS_BY_USER_ID = "SELECT " +
            "as.id, as.testId, as.score, as.date" +
            " FROM assignments as" +
            " WHERE as.studentId=?";

    ////////////////////////
    public static final String GET_REPLY_BY_ID = "SELECT " +
            "r.id, r.assignmentId, r.answerId, r.questionId" +
            " FROM replies r" +
            " WHERE r.id=?";

    public static final String GET_ALL_REPLIES = "SELECT " +
            "r.id, r.assignmentId, r.answerId, r.questionId" +
            " FROM replies r";

    public static final String ADD_NEW_REPLY = "INSERT INTO replies " +
            "(assignmentId, answerId, questionId)" +
            "VALUES(?,?,?)";

    public static final String DELETE_REPLY = "DELETE FROM replies where id = ?";

    public static final String GET_ALL_REPLIES_BY_USER_ID = "SELECT " +
            "r.id, r.assignmentId, r.answerId, r.questionId, " +
            "as.studentId" +
            " FROM replies r" +
            " INNER JOIN assignments as ON r.assignmentId = as.id " +
            " WHERE as.studentId=?";
}
