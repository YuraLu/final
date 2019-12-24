package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.util.builder.AnswerBuilder;

public class AnswerBuilderImpl implements AnswerBuilder {
    private int id;
    private String text;
    private boolean isCorrect;

    public AnswerBuilderImpl() {
    }

    public AnswerBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public Answer build() {
        final Answer answer = new Answer();
        answer.setId(id);
        answer.setAnswerText(text);
        answer.setIsCorrect(isCorrect);
        return answer;
    }

    @Override
    public AnswerBuilder withText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public AnswerBuilder isCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
