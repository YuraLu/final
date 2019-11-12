package by.epam.lukashevich.domain.service.impl;

import by.epam.lukashevich.dao.ReplyDAO;
import by.epam.lukashevich.dao.factory.DAOFactory;
import by.epam.lukashevich.dao.exception.DAOException;
import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.service.ReplyService;
import by.epam.lukashevich.domain.service.exception.ServiceException;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private final ReplyDAO replyDAO = DAOFactory.getInstance().getReplyDAO();

    public ReplyServiceImpl() {
    }

    @Override
    public List<Reply> findAll() throws ServiceException {
        try {
            return replyDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No replys", e);
        }
    }

    @Override
    public Reply findById(int id) throws ServiceException {
        try {
            return replyDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Can't find reply", e);
        }
    }

    @Override
    public void add(Reply reply) throws ServiceException {
        try {
            replyDAO.add(reply);
        } catch (DAOException e) {
            throw new ServiceException("Can't add reply", e);
        }
    }
}
