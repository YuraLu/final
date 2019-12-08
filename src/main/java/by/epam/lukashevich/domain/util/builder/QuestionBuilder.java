package by.epam.lukashevich.domain.util.builder;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;

import java.util.List;

public interface QuestionBuilder {
    Question build();

    QuestionBuilder withText(String text);

    QuestionBuilder withAnswerList(List<Answer> answers);
}