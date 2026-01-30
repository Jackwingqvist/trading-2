package se.lunduniversity.kursregistrering.model;

public class Enrollment {

    private Student student;
    private Course course;
    private String status;

    public Enrollment(Student student, Course course, String status) {
        this.student = student;
        this.course = course;
        this.status = status;
    }

    // Grund-getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    // Setters (för uppdatera vald)
    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Hjälp-getters (om du vill använda PropertyValueFactory också)
    public String getStudentName() {
        return student.getFullName();
    }

    public String getCourseCode() {
        return course.getCode();
    }

    public String getCourseName() {
        return course.getName();
    }

    public double getCredits() {
        return course.getCredits();
    }
}

