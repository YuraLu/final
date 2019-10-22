package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Objects;

public class TestQuestion implements Serializable {
    private int id;
    private int testID;
    private int questionId;

    public TestQuestion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
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
        TestQuestion that = (TestQuestion) o;
        return id == that.id &&
                testID == that.testID &&
                questionId == that.questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testID, questionId);
    }
}
