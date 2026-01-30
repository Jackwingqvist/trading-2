package se.lunduniversity.kursregistrering.model;

public class Course {
    private final String code;
    private String name;
    private double credits;

    public Course(String code, String name, double credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getCredits() { return credits; }

    public void setName(String name) { this.name = name; }
    public void setCredits(double credits) { this.credits = credits; }
}
