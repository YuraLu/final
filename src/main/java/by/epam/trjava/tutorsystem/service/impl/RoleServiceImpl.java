package by.epam.trjava.tutorsystem.service.impl;

import by.epam.trjava.tutorsystem.dao.DAOFactory;
import by.epam.trjava.tutorsystem.dao.RoleDAO;
import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Role;
import by.epam.trjava.tutorsystem.service.RoleService;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO = DAOFactory.getInstance().getRoleDAO();

    public RoleServiceImpl() {
    }

    @Override
    public Role findById(int id) throws ServiceException {
        try {
            return roleDAO.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Service can't find role", e);
        }
    }

    @Override
    public Role findRoleIdByName(String roleName) throws ServiceException {
        try {
            return roleDAO.findRoleIdByName(roleName);
        } catch (DAOException e) {
            throw new ServiceException("Can't find role by name", e);
        }
    }

    @Override
    public void delete(int roleId) throws ServiceException {
        try {
            roleDAO.delete(roleId);
        } catch (DAOException e) {
            throw new ServiceException("Service can't delete role", e);
        }

    }

    @Override
    public void add(Role role) throws ServiceException {
        try {
            roleDAO.add(role);
        } catch (DAOException e) {
            throw new ServiceException("Can't add role", e);
        }
    }

    @Override
    public List<Role> findAll() throws ServiceException {
        try {
            return roleDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("No roles", e);
        }
    }
}
