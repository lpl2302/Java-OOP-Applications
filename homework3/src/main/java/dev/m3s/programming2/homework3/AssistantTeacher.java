package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class AssistantTeacher extends Employee implements Teacher{
    private List<DesignatedCourse> courses = new ArrayList<>();

    public AssistantTeacher(String lname, String fname) {
        super(lname, fname);
        this.courses = new ArrayList<>();
    }

    @Override
    protected String getEmployeeIdString() {
        return "OY_ASSISTANT_";
    }

    @Override
    public String getCourses() {
        StringBuilder sb = new StringBuilder();
        for (DesignatedCourse course : courses) {
            sb.append(course.toString()).append("\n");
        }
        return sb.toString();
    }

    public void setCourses(List<DesignatedCourse> courses) {
        if (courses != null) {
            this.courses = courses;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double salary = calculatePayment();
        String formattedSalary = String.format("%.2f", salary);

        sb.append("Teacher id: " + getIdString()).append("\n");
        sb.append("First name: " + getFirstName() + ", Last name: " + getLastName() + "\n");
        sb.append("Birthdate: "  + getBirthDate() + "\n");
        sb.append("Salary: " + formattedSalary + "\n");
        sb.append("Assistant for courses:\n");
        sb.append(getCourses());

        return sb.toString();
    }
}
