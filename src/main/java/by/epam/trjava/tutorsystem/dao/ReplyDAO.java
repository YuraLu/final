package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Reply;

import java.util.List;

public interface ReplyDAO {

    List<Reply> findAll() throws DAOException;

    void add(Reply newReply) throws DAOException;

    Reply findById(int replyId) throws DAOException;
}
