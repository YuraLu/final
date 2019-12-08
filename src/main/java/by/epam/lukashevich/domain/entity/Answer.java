package by.epam.lukashevich.domain.entity;

public class Answer extends AbstractEntity {
    private String answerText;
    private boolean isCorrect;

    public Answer() {
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
        this.isCorrect = correct;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Answer answer = (Answer) o;

        if (isCorrect != answer.isCorrect) return false;
        return answerText != null ? answerText.equals(answer.answerText) : answer.answerText == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + (answerText != null ? answerText.hashCode() : 0);
        result = 37 * result + (isCorrect ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" +
                "{answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}