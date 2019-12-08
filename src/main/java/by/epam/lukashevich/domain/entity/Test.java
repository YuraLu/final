package by.epam.lukashevich.domain.entity;

import by.epam.lukashevich.domain.entity.user.User;

import java.util.List;

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

        if (title != null ? !title.equals(test.title) : test.title != null) return false;
        if (description != null ? !description.equals(test.description) : test.description != null) return false;
        if (subject != null ? !subject.equals(test.subject) : test.subject != null) return false;
        if (author != null ? !author.equals(test.author) : test.author != null) return false;
        return questions != null ? questions.equals(test.questions) : test.questions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + (title != null ? title.hashCode() : 0);
        result = 37 * result + (description != null ? description.hashCode() : 0);
        result = 37 * result + (subject != null ? subject.hashCode() : 0);
        result = 37 * result + (author != null ? author.hashCode() : 0);
        result = 37 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" +
                "{title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subject=" + subject +
                ", author=" + author +
                ", questions=" + questions +
                '}';
    }
}
