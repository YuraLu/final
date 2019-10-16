package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Objects;

public class Reply {
//    private static final long serialVersionUID = 1L;
    private int id;
    private Assignment assignment;
    private Answer answer;
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
