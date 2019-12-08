package by.epam.lukashevich.domain.util.builder;


import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Subject;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;

import java.util.List;

public interface TestBuilder {
    Test build();

    TestBuilder withTitle(String title);

    TestBuilder withDescription(String description);

    TestBuilder withSubject(Subject subject);

    TestBuilder withAuthor(User author);

    TestBuilder withQuestions(List<Question> questions);
}