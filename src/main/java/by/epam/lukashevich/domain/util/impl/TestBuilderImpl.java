package by.epam.lukashevich.domain.util.impl;

import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.util.TestBuilder;

public class TestBuilderImpl implements TestBuilder {
    private int id;
    private String title;
    private String description;
    private int subjectId;
    private int authorId;

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
        test.setSubjectId(subjectId);
        test.setAuthorId(authorId);
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
    public TestBuilder withSubjectId(int subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    @Override
    public TestBuilder withAuthorId(int authorId) {
        this.authorId = authorId;
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

    public int getSubjectId() {
        return subjectId;
    }

    public int getAuthorId() {
        return authorId;
    }
}
