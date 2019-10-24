package by.epam.lukashevich.domain.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Answer implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String text;
    private boolean isCorrect;
    private List<AnswerGroup> answerGroups;
    private List<Reply> replies;

    public Answer() {
    }

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

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        isCorrect = correct;
    }

    public List<AnswerGroup> getAnswerGroups() {
        return answerGroups;
    }

    public void setAnswerGroups(List<AnswerGroup> answerGroups) {
        this.answerGroups = answerGroups;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                isCorrect == answer.isCorrect &&
                Objects.equals(text, answer.text) &&
                Objects.equals(answerGroups, answer.answerGroups) &&
                Objects.equals(replies, answer.replies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, isCorrect, answerGroups, replies);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                ", answerGroups=" + answerGroups +
                ", replies=" + replies +
                '}';
    }
}
