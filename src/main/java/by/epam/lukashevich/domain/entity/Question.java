package by.epam.lukashevich.domain.entity;

import java.util.List;

public class Question  extends AbstractEntity {
    private String questionText;
    private List<Answer> answers;

    public Question() {
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        if (questionText != null ? !questionText.equals(question.questionText) : question.questionText != null)
            return false;
        return answers != null ? answers.equals(question.answers) : question.answers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 37 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" +
                "{questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}