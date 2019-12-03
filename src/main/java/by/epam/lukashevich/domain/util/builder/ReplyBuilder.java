package by.epam.lukashevich.domain.util.builder;

import by.epam.lukashevich.domain.entity.Answer;
import by.epam.lukashevich.domain.entity.Question;
import by.epam.lukashevich.domain.entity.Reply;

import java.util.List;

public interface ReplyBuilder {

    Reply build();

    ReplyBuilder withAssignmentId(int assignmentId);

    ReplyBuilder withQuestion(Question question);

    ReplyBuilder withAnswers(List<Answer> answers);

}