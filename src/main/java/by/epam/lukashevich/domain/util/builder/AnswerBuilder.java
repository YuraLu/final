package by.epam.lukashevich.domain.util.builder;

import by.epam.lukashevich.domain.entity.Answer;

public interface AnswerBuilder {

    Answer build();

    AnswerBuilder withText(String text);

    AnswerBuilder isCorrect(boolean isCorrect);
}