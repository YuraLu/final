package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.TestDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.util.TestBuilder;
import by.epam.lukashevich.domain.util.impl.TestBuilderImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLTestDAOImpl implements TestDAO {
    private static final String GET_TEST_BY_ID = "SELECT " +
            "t.id," +
            "t.title," +
            "t.description," +
            "t.subjectId," +
            "t.authorId" +
            " FROM tests t" +
            " WHERE id=?";

    private static final String GET_ALL_TESTS = "SELECT " +
            "t.id," +
            "t.title," +
            "t.description," +
            "t.subjectId," +
            "t.authorId" +
            " FROM tests t";

    private static final String ADD_NEW_TEST =
            "INSERT INTO tests " +
                    "(title, description, subjectId, authorId) " +
                    "VALUES(?,?,?,?)";


    private static final String UPDATE_TEST = "UPDATE tests SET " +
            "title=?, " +
            "description =?, " +
            "subjectId=?" +
            " WHERE id = ?";

    private static final String GET_TEST_BY_TITLE =
            "SELECT id FROM tests where title=?";


    private static final String DELETE_TEST = "DELETE FROM tests where id = ?";


    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Test> findAll() throws DAOException {
        List<Test> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_TESTS)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Test test = getTest(rs);
                list.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("SQL Exception can't create list of tests in findAll()", e);
        }
        return list;
    }

    @Override
    public Test findById(int id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_TEST_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return getTest(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find test in findById()", e);
        }
        throw new DAOException("No test with ID=" + id + ".");
    }

    @Override
    public boolean add(Test test) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_TEST)) {

            if (isTitleUsed(con, test.getTitle())) {
                throw new DAOException("Title is already is use!");
            }
            st.setString(1, test.getTitle());
            st.setString(2, test.getDescription());
            st.setInt(3, test.getSubjectId());
            st.setInt(4, test.getAuthorId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during registration()", e);
        }
        return true;
    }

    @Override
    public boolean update(Test test) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(UPDATE_TEST)) {

            st.setString(1, test.getTitle());
            st.setString(2, test.getDescription());
            st.setInt(3, test.getSubjectId());
            st.setInt(4, test.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during test update()", e);
        }
    }

    @Override
    public boolean delete(int id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_TEST_BY_ID);
             PreparedStatement stDelete = con.prepareStatement(DELETE_TEST)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                stDelete.setInt(1,id);
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete testwith id=" + id, e);
        }
        throw new DAOException("No test with ID=" + id + ".");
    }

    private boolean isTitleUsed(Connection connection, String title) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(GET_TEST_BY_TITLE)) {
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            return rs.first();
        }
    }

    private Test getTest(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        int subjectId = Integer.parseInt(rs.getString("subjectId"));
        int authorId = Integer.parseInt(rs.getString("authorId"));

        TestBuilder testBuilder = new TestBuilderImpl(id);
        return testBuilder.withTitle(title)
                .withDescription(description)
                .withSubjectId(subjectId)
                .withAuthorId(authorId)
                .build();
    }
}
