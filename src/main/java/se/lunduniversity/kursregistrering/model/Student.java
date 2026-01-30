package se.lunduniversity.kursregistrering.model;

public class Student {
    private final int id;
    private String firstName;
    private String lastName;
    private String program;

    public Student(int id, String firstName, String lastName, String program) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getProgram() { return program; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setProgram(String program) { this.program = program; }

    public String getFullName() { return firstName + " " + lastName; }
}
