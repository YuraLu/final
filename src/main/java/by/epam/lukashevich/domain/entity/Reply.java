package by.epam.lukashevich.domain.entity;

import java.util.ArrayList;
import java.util.List;

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

        if (assignmentId != reply.assignmentId) return false;
        if (question != null ? !question.equals(reply.question) : reply.question != null) return false;
        return answers != null ? answers.equals(reply.answers) : reply.answers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + assignmentId;
        result = 37 * result + (question != null ? question.hashCode() : 0);
        result = 37 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" +
                "{assignmentId=" + assignmentId +
                ", question=" + question +
                ", answers=" + answers +
                '}';
    }
}