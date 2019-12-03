package by.epam.lukashevich.domain.entity;

import by.epam.lukashevich.domain.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Assignment extends AbstractEntity {
    private Test test;
    private User student;
    private int score;
    private List<Reply> replies;
    private String date;

    public Assignment() {
        replies = new ArrayList<>();
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Assignment that = (Assignment) o;
        return score == that.score &&
                Objects.equals(test, that.test) &&
                Objects.equals(student, that.student) &&
                Objects.equals(replies, that.replies) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), test, student, score, replies, date);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "test=" + test +
                ", student=" + student +
                ", score=" + score +
                ", replies=" + replies +
                ", date='" + date + '\'' +
                '}';
    }
}