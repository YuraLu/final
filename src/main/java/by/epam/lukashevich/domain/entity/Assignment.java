package by.epam.lukashevich.domain.entity;

import by.epam.lukashevich.domain.entity.user.User;

import java.util.Objects;

public class Assignment extends AbstractEntity {
    private int testId;
    private int studentId;
    private Test test;
    private User student;

    public Assignment() {
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

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assignment that = (Assignment) o;
        return testId == that.testId &&
                studentId == that.studentId &&
                Objects.equals(test, that.test) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), testId, studentId, test, student);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "testId=" + testId +
                ", studentId=" + studentId +
                ", test=" + test +
                ", student=" + student +
                '}';
    }
}
