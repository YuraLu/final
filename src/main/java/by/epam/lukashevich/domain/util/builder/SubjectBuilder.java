package by.epam.lukashevich.domain.util.builder;

import by.epam.lukashevich.domain.entity.Subject;

public interface SubjectBuilder {
    Subject build();

    SubjectBuilder withName(String name);
}
