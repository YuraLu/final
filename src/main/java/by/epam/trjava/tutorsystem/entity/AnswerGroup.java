package by.epam.trjava.tutorsystem.entity;


import java.io.Serializable;
import java.util.Objects;

public class AnswerGroup implements Serializable {
    private int id;
    private int answerId;
    private int questionId;

    public AnswerGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        AnswerGroup that = (AnswerGroup) o;
        return id == that.id &&
                answerId == that.answerId &&
                questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerId, questionId);
    }
}
