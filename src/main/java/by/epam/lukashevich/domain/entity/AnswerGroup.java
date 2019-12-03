package by.epam.lukashevich.domain.entity;


import java.util.Objects;

public class AnswerGroup extends AbstractEntity {
    private Answer answer;
    private Question question;

    public AnswerGroup() {
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
        AnswerGroup that = (AnswerGroup) o;
        return Objects.equals(answer, that.answer) &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), answer, question);
    }

    @Override
    public String toString() {
        return "AnswerGroup{" +
                "answer=" + answer +
                ", question=" + question +
                '}';
    }
}
