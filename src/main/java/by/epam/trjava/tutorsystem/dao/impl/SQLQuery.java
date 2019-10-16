package by.epam.trjava.tutorsystem.dao.impl;

public class SQLQuery {

    //User
    static final String QUERY_GET_ALL_USER = "SELECT * FROM users";
    static final String QUERY_CHECK_USER = "SELECT * FROM users WHERE login=? and password=?";
    static final String QUERY_GET_USER = "SELECT * FROM users WHERE id=?";
    static final String QUERY_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    static final String QUERY_USER_EXISTS_CHECK = "SELECT id FROM users WHERE login=?";
    static final String QUERY_REGISTER_USER = "INSERT INTO users(email, login, password, name) VALUES(?, ?, ?, ?)";
    static final String QUERY_DELETE_USER = "DELETE FROM users where id = ?";
    static final String QUERY_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE login = ?";


    //Test
    static final String QUERY_GET_ALL_TEST = "SELECT * FROM tests";
    static final String QUERY_ADD_TEST = "INSERT INTO tests (title, description, " +
            "subjectId, authorId) VALUES (?, ?, ?, ?)";
    static final String QUERY_GET_TEST = "SELECT * FROM tests WHERE id = ?";
    static final String QUERY_DELETE_TEST = "DELETE FROM tests where id = ?";
    static final String QUERY_UPDATE_TEST = "UPDATE tests SET title = ?, description = ?, subjectId = ? WHERE id = ?";


    //Subject
    static final String QUERY_GET_ALL_SUBJECT = "SELECT * FROM subjects";
    static final String QUERY_ADD_SUBJECT = "INSERT INTO subjects (name) VALUES (?)";
    static final String QUERY_GET_SUBJECT = "SELECT * FROM subjects WHERE id = ?";
    static final String QUERY_DELETE_SUBJECT = "DELETE FROM subjects where id = ?";

    //Role
    static final String QUERY_GET_ALL_ROLE = "SELECT * FROM roles";
    static final String QUERY_ADD_ROLE = "INSERT INTO roles (name) VALUES (?)";
    static final String QUERY_GET_ROLE = "SELECT * FROM roles WHERE id = ?";
    static final String QUERY_DELETE_ROLE = "DELETE FROM roles where id = ?";


}
