package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;

public class TestQuestion {
//    private static final long serialVersionUID = 1L;
    private int id;
    private Test tests;
    private Question questions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test getTests() {
        return tests;
    }

    public void setTests(Test tests) {
        this.tests = tests;
    }

    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }
}
