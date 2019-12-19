package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.util.builder.SubjectBuilder;

public class SubjectBuilderImpl implements SubjectBuilder {
    private int id;
    private String name;

    public SubjectBuilderImpl() {
    }

    public SubjectBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public Subject build() {
        final Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        return subject;
    }

    @Override
    public SubjectBuilder withName(String name) {
        this.name = name;
        return this;
    }
}
