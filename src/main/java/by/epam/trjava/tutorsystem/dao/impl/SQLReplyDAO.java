package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.ReplyDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.dao.pool.JDBCUtils;
import by.epam.trjava.tutorsystem.dao.pool.ConnectionPool;
import by.epam.trjava.tutorsystem.dao.pool.exception.ConnectionPoolException;
import by.epam.trjava.tutorsystem.entity.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.trjava.tutorsystem.dao.impl.SQLQuery.*;

public class SQLReplyDAO implements ReplyDAO {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public SQLReplyDAO() {
    }

    @Override
    public List<Reply> findAll() throws DAOException {
        List<Reply> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ALL_REPLY);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int assigmentId = rs.getInt("assigmentId");
                int answerId = rs.getInt("answerId");
                int questionId = rs.getInt("questionId");

                Reply reply = new Reply();
                reply.setId(id);
                reply.setAssignmentId(assigmentId);
                reply.setAnswerId(answerId);
                reply.setQuestionId(questionId);

                list.add(reply);
            }
        } catch (SQLException e) {
            throw new DAOException("Problem to get list of Replies", e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return list;
    }

    @Override
    public boolean add(Reply newReply) throws DAOException {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_ADD_ANSWER);
            st.setInt(1, newReply.getAssignmentId());
            st.setInt(2, newReply.getAnswerId());
            st.setInt(2, newReply.getQuestionId());
            rowUpdated = st.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Can't add Reply with id=" + newReply.getId(), e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return rowUpdated;
    }

    @Override
    public Reply findById(int replyId) throws DAOException {
        Reply reply = null;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = pool.takeConnection();
            st = con.prepareStatement(QUERY_GET_ANSWER);
            st.setInt(1, replyId);
            rs = st.executeQuery();

            if (rs.next()) {
                int assigmentId = rs.getInt("assigmentId");
                int answerId = rs.getInt("answerId");
                int questionId = rs.getInt("questionId");

                reply = new Reply();
                reply.setId(replyId);
                reply.setAssignmentId(assigmentId);
                reply.setAnswerId(answerId);
                reply.setQuestionId(questionId);
            }
        } catch (SQLException e) {
            throw new DAOException("Can't make statement for Reply with id=" + replyId, e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, st, con);
//            pool.closeConnection(rs, st, con);
        }
        return reply;
    }
}
