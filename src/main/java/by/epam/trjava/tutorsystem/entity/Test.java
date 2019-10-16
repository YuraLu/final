package by.epam.trjava.tutorsystem.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Test  {
//    private static final long serialVersionUID = 1L;
    private int id;
    private String title;
    private String description;
    private Collection<Assignment> assignments;
    private Collection<TestQuestion> testQuestions;
//    private Subject subject;
    private int subjectId;
//    private User author;
    private int authorId;

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

    public Collection<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Collection<Assignment> assignments) {
        this.assignments = assignments;
    }

    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(Collection<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }

//    public Subject getSubject() {
//        return subject;
//    }
//
//    public void setSubject(Subject subject) {
//        this.subject = subject;
//        setSubjectId(subject.getId());
//    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getSubjectId() {
        return subjectId;
    }

//    public User getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(User author) {
//        this.author = author;
//        setAuthorId(author.getId());
//    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", assigmentsById=" + assignments +
                ", testquestionsById=" + testQuestions +
                ", subjectId=" + subjectId +
                ", authorId=" + authorId +
                '}';
    }
}
