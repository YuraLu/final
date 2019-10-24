package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface ReplyService {

    List<Reply> findAll() throws ServiceException;

    Reply findById(String replyId) throws ServiceException;

    void add(Reply newReply) throws ServiceException;
}
