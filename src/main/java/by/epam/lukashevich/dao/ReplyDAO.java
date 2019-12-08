package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Reply;

import java.util.List;

public interface ReplyDAO extends CommonDAO<Reply> {

    @Override
    List<Reply> findAll() throws DAOException;

    @Override
    Reply findById(Integer id) throws DAOException;

    @Override
    boolean add(Reply reply) throws DAOException;

    @Override
    boolean update(Reply reply) throws DAOException;

    @Override
    boolean delete(Integer id) throws DAOException;
}