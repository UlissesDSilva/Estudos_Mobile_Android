package dsm.quixada.componentesbasicos;

public class Alunos {
    private static int countId = 0;

    private int id;
    private String name;
    private String course;
    private String campus;
    private String hours;

    public Alunos(String name, String course, String campus, String hours) {
        this.id = countId++;
        this.name = name;
        this.course = course;
        this.campus = campus;
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
