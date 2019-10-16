package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Assignment {
//    private static final long serialVersionUID = 1L;
    private int id;
    private Test test;
    private User user;
    private Collection<Reply> replies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }
}
