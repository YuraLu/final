package by.epam.trjava.tutorsystem.dao.impl;

import by.epam.trjava.tutorsystem.dao.ReplyDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Reply;

import java.util.List;

public class SQLReplyDAO implements ReplyDAO {
    @Override
    public List<Reply> findAll() throws DAOException {
        return null;
    }

    @Override
    public void add(Reply newReply) throws DAOException {

    }

    @Override
    public Reply findById(int replyId) throws DAOException {
        return null;
    }
}
