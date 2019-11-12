package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.util.builder.TestBuilder;

import java.util.List;

public class TestBuilderImpl implements TestBuilder {
    private int id;
    private String title;
    private String description;
    private Subject subject;
    private User author;
    private List<Question> questions;

    public TestBuilderImpl() {
    }

    public TestBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public Test build() {
        final Test test = new Test();
        test.setId(id);
        test.setTitle(title);
        test.setDescription(description);
        test.setSubject(subject);
        test.setAuthor(author);
        test.setQuestions(questions);
        return test;
    }

    @Override
    public TestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public TestBuilder withSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public TestBuilder withAuthor(User author) {
        this.author = author;
        return this;
    }

    @Override
    public TestBuilder withQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Subject getSubject() {
        return subject;
    }

    public User getAuthor() {
        return author;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
