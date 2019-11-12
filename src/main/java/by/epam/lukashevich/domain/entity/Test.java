package by.epam.lukashevich.domain.entity;

import by.epam.lukashevich.domain.entity.user.User;

import java.util.List;
import java.util.Objects;

public class Test extends AbstractEntity {

    private String title;
    private String description;
    private Subject subject;
    private User author;
    private List<Question> questions;

    public Test() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Test test = (Test) o;
        return Objects.equals(title, test.title) &&
                Objects.equals(description, test.description) &&
                Objects.equals(subject, test.subject) &&
                Objects.equals(author, test.author) &&
                Objects.equals(questions, test.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, subject, author, questions);
    }

    @Override
    public String toString() {
        return "Test{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subject=" + subject +
                ", author=" + author +
                ", questions=" + questions +
                '}';
    }
}
