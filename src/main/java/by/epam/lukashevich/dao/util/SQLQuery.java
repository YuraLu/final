package by.epam.lukashevich.dao.util;

public final class SQLQuery {

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

    /////////////////////////
    public static final String GET_USER_BY_ID = "SELECT " +
            "u.id, u.name, u.email, u.login, u.password, u.roleId, u.banned" +
            " FROM users u" +
            " WHERE u.id=?";

    public static final String GET_ALL_USERS = "SELECT " +
            "u.id, u.name, u.email, u.login, u.password, u.roleId, u.banned" +
            " FROM users u" +
            " INNER JOIN roles r" +
            " ON u.roleId = r.id";

    public static final String ADD_NEW_USER = "INSERT INTO users " +
            "(name, email, login, password, roleId) " +
            "VALUES(?,?,?,?,?)";

    public static final String GET_USER_BY_LOGIN = "SELECT id FROM users where login=?";

    public static final String GET_USER_BY_LOGIN_PASS = "SELECT " +
            "u.id, u.name, u.email, u.login, u.password, u.roleId, u.banned" +
            " FROM users u" +
            " WHERE u.login=? and u.password=?";

    public static final String UPDATE_USER_STATUS = "UPDATE users SET roleId = ? WHERE id = ?";

    public static final String UPDATE_USER_BAN_STATUS = "UPDATE users SET banned = ? WHERE id = ?";

    public static final String UPDATE_USER = "UPDATE users SET " +
            "name=?, email =?, login=?, password=?, roleId=?, banned=? WHERE id = ?";

    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    ////////////////////////////////////////
    public static final String GET_SUBJECT_BY_ID = "SELECT s.id, s.name FROM subjects s WHERE s.id=?";

    public static final String GET_ALL_SUBJECTS = "SELECT s.id, s.name FROM subjects s";

    public static final String ADD_NEW_SUBJECT = "INSERT INTO subjects (name) VALUES(?)";

    public static final String GET_SUBJECT_BY_NAME = "SELECT s.id FROM subjects s WHERE s.name=?";

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

}
