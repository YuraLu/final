package by.epam.lukashevich.domain.entity;


import java.util.Objects;

public class Reply extends AbstractEntity {
    private int assignmentId;
    private int answerId;
    private int questionId;
    private Assignment assignment;
    private Answer answer;
    private Question question;

    public Reply() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reply reply = (Reply) o;
        return assignmentId == reply.assignmentId &&
                answerId == reply.answerId &&
                questionId == reply.questionId &&
                Objects.equals(assignment, reply.assignment) &&
                Objects.equals(answer, reply.answer) &&
                Objects.equals(question, reply.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assignmentId, answerId, questionId, assignment, answer, question);
    }

    @Override
    public String toString() {
        return "Reply{" +
                "assignmentId=" + assignmentId +
                ", answerId=" + answerId +
                ", questionId=" + questionId +
                ", assignment=" + assignment +
                ", answer=" + answer +
                ", question=" + question +
                '}';
    }
}
