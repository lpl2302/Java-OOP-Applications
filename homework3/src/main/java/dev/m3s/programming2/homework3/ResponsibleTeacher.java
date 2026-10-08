package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class ResponsibleTeacher extends Employee implements Teacher, Payment {
    private List<DesignatedCourse> courses;

    public ResponsibleTeacher(String lname, String fname) {
        super(lname, fname);
        this.courses = new ArrayList<>();
    }

    @Override
    protected String getEmployeeIdString() {
        return "OY_TEACHER_";
    }

    public void setCourses(List<DesignatedCourse> courses) {
        if (courses != null) {
            this.courses = courses;
        } else {
            this.courses = new ArrayList<>();
        }
    }

    @Override
    public String getCourses() {
        StringBuilder sb = new StringBuilder();
        for (DesignatedCourse course : courses) {
            if (course.isResponsible()) {
                sb.append("Responsible teacher: ").append(course.toString()).append("\n");
            }
            else {
                sb.append("Teacher: ").append(course.toString()).append("\n");
            }
        }
        return sb.toString();
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
        sb.append("Teacher for courses:\n");
        sb.append(getCourses());

        return sb.toString();
    }
}
