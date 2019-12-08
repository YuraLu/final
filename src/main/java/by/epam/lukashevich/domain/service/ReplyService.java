package by.epam.lukashevich.domain.service;

import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public interface ReplyService {

    List<Reply> findAll() throws ServiceException;

    Reply findById(int id) throws ServiceException;

    void add(Reply reply) throws ServiceException;
}