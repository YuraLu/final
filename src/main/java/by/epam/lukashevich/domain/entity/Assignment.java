package by.epam.lukashevich.domain.entity;

import by.epam.lukashevich.domain.entity.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Assignment extends AbstractEntity {
    private Test test;
    private User student;
    private int score;
    private List<Reply> replies;
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Assignment that = (Assignment) o;

        if (score != that.score) return false;
        if (test != null ? !test.equals(that.test) : that.test != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        if (replies != null ? !replies.equals(that.replies) : that.replies != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + (test != null ? test.hashCode() : 0);
        result = 37 * result + (student != null ? student.hashCode() : 0);
        result = 37 * result + score;
        result = 37 * result + (replies != null ? replies.hashCode() : 0);
        result = 37 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String toString() {
        return getClass().getSimpleName() + "@" +
                "{test = " + test +
                ", student=" + student +
                ", score=" + score +
                ", replies=" + replies +
                ", date=" + date +
                '}';
    }
}