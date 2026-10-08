package dev.m3s.programming2.homework3;

import java.time.Year;

public class DesignatedCourse {

    private Course course;
    private boolean responsible;
    private int year; // year when the teacher is set for the course,
                      // 2000 <= year <= (current year + 1)

    // Constructors
    public DesignatedCourse() {
    }

    public DesignatedCourse(Course course, boolean resp, int year) {
        this.course = course;
        this.responsible = resp;
        this.year = year;
    }

    // Methods
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        if (course != null) {
            this.course = course;
        }
    }

    public boolean isResponsible() {
        return this.responsible;
    }

    public boolean getResponsible() {
        return this.responsible;
    }

    public void setResponsible(boolean responsible) {
        this.responsible = responsible;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        if (year >= 2000 && year <= Year.now().getValue() + 1) {
            this.year = year;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[course=");
        sb.append(course.toString());
        sb.append(", year=").append(year).append("]");
        return sb.toString();
    }
}
