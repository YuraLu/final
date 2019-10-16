package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

public class Question {
//    private static final long serialVersionUID = 1L;
    private int id;
    private String text;
    private Collection<AnswerGroup> answerGroups;
    private Collection<Reply> replies;
    private Collection<TestQuestion> testQuestions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Collection<AnswerGroup> getAnswerGroups() {
        return answerGroups;
    }

    public void setAnswerGroups(Collection<AnswerGroup> answerGroups) {
        this.answerGroups = answerGroups;
    }

    public Collection<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Reply> replies) {
        this.replies = replies;
    }

    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(Collection<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }
}
