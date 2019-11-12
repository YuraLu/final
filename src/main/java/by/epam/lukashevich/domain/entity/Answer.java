package by.epam.lukashevich.domain.entity;

import java.util.Objects;

public class Answer extends AbstractEntity {
    private String answerText;
    private boolean isCorrect;

    public Answer() {
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        this.isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return isCorrect == answer.isCorrect &&
                Objects.equals(answerText, answer.answerText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), answerText, isCorrect);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
