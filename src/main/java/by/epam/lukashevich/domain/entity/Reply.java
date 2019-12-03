package by.epam.lukashevich.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reply extends AbstractEntity {

    private int assignmentId;
    private Question question;
    private List<Answer> answers;


    public Reply() {
        answers = new ArrayList<>();
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reply reply = (Reply) o;
        return assignmentId == reply.assignmentId &&
                Objects.equals(question, reply.question) &&
                Objects.equals(answers, reply.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assignmentId, question, answers);
    }

    @Override
    public String toString() {
        return "Reply{" +
                "assignmentId=" + assignmentId +
                ", question=" + question +
                ", answers=" + answers +
                '}';
    }
}