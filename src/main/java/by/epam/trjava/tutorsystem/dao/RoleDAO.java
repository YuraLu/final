package by.epam.trjava.tutorsystem.dao;

import by.epam.trjava.tutorsystem.dao.exception.DAOException;
import by.epam.trjava.tutorsystem.entity.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> findAll() throws DAOException;

    Role findById(int roleId) throws DAOException;

    Role findRoleIdByName(String roleName) throws DAOException;

    boolean add(Role newRole) throws DAOException;

    boolean delete(int roleId) throws DAOException;
}
