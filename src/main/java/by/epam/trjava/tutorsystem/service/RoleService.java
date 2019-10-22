package by.epam.trjava.tutorsystem.service;

import by.epam.trjava.tutorsystem.entity.Role;
import by.epam.trjava.tutorsystem.service.exception.ServiceException;

import java.util.List;

public interface RoleService {
    Role findById(int id) throws ServiceException;

    Role findRoleIdByName(String roleName) throws ServiceException;

    void delete(int roleId) throws ServiceException;

    void add(Role role) throws ServiceException;

    List<Role> findAll() throws ServiceException;
}
