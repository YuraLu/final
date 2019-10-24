package by.epam.lukashevich.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Reply implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int assignmentId;
    private int answerId;
    private int questionId;

    public Reply() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return id == reply.id &&
                assignmentId == reply.assignmentId &&
                answerId == reply.answerId &&
                questionId == reply.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignmentId, answerId, questionId);
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", assignmentId=" + assignmentId +
                ", answerId=" + answerId +
                ", questionId=" + questionId +
                '}';
    }
}
