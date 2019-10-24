package by.epam.lukashevich.domain.util;


import by.epam.lukashevich.domain.entity.Test;

public interface TestBuilder {
    Test build();

    TestBuilder withTitle(String title);

    TestBuilder withDescription(String description);

    TestBuilder withSubjectId(int subjectId);

    TestBuilder withAuthorId(int authorId);

}
