package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.service.ReplyService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    @Override
    public List<Reply> findAll() throws ServiceException {
        return null;
    }

    @Override
    public Reply findById(String replyId) throws ServiceException {
        return null;
    }

    @Override
    public void add(Reply newReply) throws ServiceException {

    }
}
