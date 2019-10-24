package by.epam.lukashevich.dao;

import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Reply;

import java.util.List;

public interface ReplyDAO {

    List<Reply> findAll() throws DAOException;

    Reply findById(int id) throws DAOException;

    boolean add(Reply reply) throws DAOException;
}
