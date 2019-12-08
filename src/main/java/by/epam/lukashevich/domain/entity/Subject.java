package by.epam.lukashevich.domain.entity;

public class Subject extends AbstractEntity {

    private String name;

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Subject subject = (Subject) o;

        return name != null ? name.equals(subject.name) : subject.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" +
                "{name='" + name + '\'' +
                '}';
    }
}