package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.util.builder.ReplyBuilder;

import java.util.ArrayList;
import java.util.List;

public class ReplyBuilderImpl implements ReplyBuilder {
    private int id;
    private int assignmentId;
    private Question question;
    private List<Answer> answers;

    public ReplyBuilderImpl() {
        this.answers = new ArrayList<>();
    }

    public ReplyBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public Reply build() {
        final Reply reply = new Reply();
        reply.setId(id);
        reply.setAssignmentId(assignmentId);
        reply.setQuestion(question);
        reply.setAnswers(answers);
        return reply;
    }

    @Override
    public ReplyBuilder withAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    @Override
    public ReplyBuilder withQuestion(Question question) {
        this.question = question;
        return this;
    }

    @Override
    public ReplyBuilder withAnswers(List<Answer> answers) {
        this.answers = answers;
        return this;
    }
}