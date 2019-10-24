package by.epam.lukashevich.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Assignment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int testId;
    private int studentId;

    public Assignment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return id == that.id &&
                testId == that.testId &&
                studentId == that.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, testId, studentId);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", testId=" + testId +
                ", studentId=" + studentId +
                '}';
    }
}
