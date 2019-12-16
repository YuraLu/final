package by.epam.lukashevich.domain.util.builder;

import by.epam.lukashevich.domain.entity.Assignment;
import by.epam.lukashevich.domain.entity.Reply;
import by.epam.lukashevich.domain.entity.Test;
import by.epam.lukashevich.domain.entity.user.User;

import java.util.Date;
import java.util.List;

public interface AssignmentBuilder {

    Assignment build();

    AssignmentBuilder withTest(Test test);

    AssignmentBuilder withUser(User student);

    AssignmentBuilder withScore(int score);

    AssignmentBuilder withReplies(List<Reply> replies);

    AssignmentBuilder withDate(Date date);
}