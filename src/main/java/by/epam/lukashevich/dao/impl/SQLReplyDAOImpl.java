package by.epam.lukashevich.dao.impl;

import by.epam.lukashevich.dao.ReplyDAO;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Reply;

import java.util.List;

public class SQLReplyDAOImpl implements ReplyDAO {
    @Override
    public List<Reply> findAll() throws DAOException {
        return null;
    }

    @Override
    public Reply findById(int id) throws DAOException {
        return null;
    }

    @Override
    public boolean add(Reply reply) throws DAOException {
        return false;
    }
}
