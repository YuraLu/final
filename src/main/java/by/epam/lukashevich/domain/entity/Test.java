package by.epam.lukashevich.domain.entity;

import java.io.Serializable;
import java.util.Objects;

public class Test implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String description;
    private int subjectId;
    private int authorId;

    public Test() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id &&
                subjectId == test.subjectId &&
                authorId == test.authorId &&
                Objects.equals(title, test.title) &&
                Objects.equals(description, test.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, subjectId, authorId);
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subjectId=" + subjectId +
                ", authorId=" + authorId +
                '}';
    }
}
