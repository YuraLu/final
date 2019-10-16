package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Collection;

public class Answer {
//    private static final long serialVersionUID = 1L;
    private int id;
    private String text;
    private int isCorrect;
    private Collection<AnswerGroup> answerGroups;
    private Collection<Reply> replies;

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

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
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

}
