package by.epam.lukashevich.domain.util.builder.impl;

import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;
import by.epam.lukashevich.domain.util.builder.AssignmentBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssignmentBuilderImpl implements AssignmentBuilder {

    private int id;
    private Test test;
    private User student;
    private int score;
    private List<Reply> replies;
    private Date date;

    public AssignmentBuilderImpl() {
        replies = new ArrayList<>();
    }

    public AssignmentBuilderImpl(int id) {
        this.id = id;
    }

    @Override
    public Assignment build() {
        final Assignment assignment = new Assignment();
        assignment.setId(id);
        assignment.setTest(test);
        assignment.setStudent(student);
        assignment.setScore(score);
        assignment.setReplies(replies);
        assignment.setDate(date);
        return assignment;
    }

    @Override
    public AssignmentBuilder withTest(Test test) {
        this.test = test;
        return this;
    }

    @Override
    public AssignmentBuilder withUser(User student) {
        this.student = student;
        return this;
    }

    @Override
    public AssignmentBuilder withScore(int score) {
        this.score = score;
        return this;
    }

    @Override
    public AssignmentBuilder withReplies(List<Reply> replies) {
        this.replies = replies;
        return this;
    }

    @Override
    public AssignmentBuilder withDate(Date date) {
        this.date = date;
        return this;
    }
}