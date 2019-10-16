package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Collection;

public class Role  {
//    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Collection<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
