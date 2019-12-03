package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.ReplyDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.dao.pool.connection.ConnectionWrapper;
import by.epam.lukashevich.dao.pool.connection.ProxyConnection;
import by.epam.lukashevich.dao.pool.impl.DatabaseConnectionPool;
import by.epam.lukashevich.dao.util.SQLUtil;
import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lukashevich.dao.util.SQLQuery.*;

public class SQLReplyDAOImpl implements ReplyDAO {
    private final DatabaseConnectionPool pool = DatabaseConnectionPool.getInstance();

    @Override
    public List<Reply> findAll() throws DAOException {
        List<Reply> list = new ArrayList<>();
        try (ProxyConnection proxyConnection = pool.getConnection();
             Connection con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_ALL_REPLIES)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Reply reply = SQLUtil.getReply(rs);
                list.add(reply);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't create list of replies in findAll()", e);
        }
        return list;
    }

    @Override
    public Reply findById(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(GET_REPLY_BY_ID)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return SQLUtil.getReply(rs);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't find reply in findById()", e);
        }
        throw new DAOException("No reply with ID=" + id + ".");
    }

    @Override
    public boolean add(Reply reply) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(ADD_NEW_REPLY)) {

            con.setAutoCommit(false);

            for (Answer answerItem : reply.getAnswers()) {
                st.setInt(1, reply.getAssignmentId());
                st.setInt(2, answerItem.getId());
                st.setInt(3, reply.getQuestion().getId());
                st.addBatch();
            }

            st.executeBatch();
            con.commit();
            con.setAutoCommit(true);

            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception during add()", e);
        }
    }

    @Override
    public boolean update(Reply reply) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DAOException {
        try (ProxyConnection proxyConnection = pool.getConnection();
             ConnectionWrapper con = proxyConnection.getConnectionWrapper();
             PreparedStatement st = con.prepareStatement(DELETE_REPLY)) {
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("SQL Exception can't delete reply with id=" + id, e);
        }
    }
}
