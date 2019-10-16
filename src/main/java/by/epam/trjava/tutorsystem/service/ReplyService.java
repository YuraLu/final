package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.Reply;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface ReplyService {

    List<Reply> findAll() throws ServiceException;

    void add(Reply newReply) throws ServiceException;

    Reply findById(String replyId) throws ServiceException;
}
