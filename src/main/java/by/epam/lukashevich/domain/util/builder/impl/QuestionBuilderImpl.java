package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.util.builder.QuestionBuilder;

import java.util.List;

public class QuestionBuilderImpl implements QuestionBuilder {

    private int id;
    private String questionText;
    private List<Answer> answers;

    public QuestionBuilderImpl() {
    }

    public QuestionBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public Question build() {
        final Question question = new Question();
        question.setId(id);
        question.setQuestionText(questionText);
        question.setAnswers(answers);
        return question;
    }

    @Override
    public QuestionBuilder withText(String questionText) {
        this.questionText = questionText;
        return this;
    }

    @Override
    public QuestionBuilder withAnswerList(List<Answer> answers) {
        this.answers = answers;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
