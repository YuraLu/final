package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Subject  {
//    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Collection<Test> tests;

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

    public Collection<Test> getTests() {
        return tests;
    }

    public void setTests(Collection<Test> tests) {
        this.tests = tests;
    }
}
