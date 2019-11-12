package by.epam.lukashevich.domain.entity;


import java.util.Objects;

public class AnswerGroup extends AbstractEntity {
    private int answerId;
    private int questionId;

    public AnswerGroup() {
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
        if (!super.equals(o)) return false;
        AnswerGroup that = (AnswerGroup) o;
        return answerId == that.answerId &&
                questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), answerId, questionId);
    }

    @Override
    public String toString() {
        return "AnswerGroup{" +
                "answerId=" + answerId +
                ", questionId=" + questionId +
                '}';
    }
}
