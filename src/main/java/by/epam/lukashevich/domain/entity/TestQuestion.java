package by.epam.lukashevich.domain.entity;

import java.util.Objects;

public class TestQuestion extends AbstractEntity {
    private Test test;
    private Question question;

    public TestQuestion() {
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
        TestQuestion that = (TestQuestion) o;
        return Objects.equals(test, that.test) &&
                Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), test, question);
    }

    @Override
    public String toString() {
        return "TestQuestion{" +
                "test=" + test +
                ", question=" + question +
                '}';
    }
}
