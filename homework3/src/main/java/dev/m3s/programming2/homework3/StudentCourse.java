package dev.m3s.programming2.homework3;

import java.time.Year;

public class StudentCourse {

    // StudentCourse – attributes
    private Course course;
    private int gradeNum; //Numerically graded course: 0-5, letter graded course: either ‘F’ (failed) or ‘A’ (accepted).
    private int yearCompleted; // 2000 < yearCompleted <= currentYear

    // StudentCourse - constructors
    // without parameters
    public StudentCourse() {
    }

    // without parameters
    public StudentCourse(Course course, final int gradeNum, final int yearCompleted) { //set given course, gradeNum and yearCompleted
        setCourse(course);
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    // Methods
    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        return this.gradeNum;
    }

    protected void setGrade(int gradeNum) { //check whether the course is graded numerically or not
        // Check and return the gradeNum
        if (course != null) {  // Null check for course
            if (course.isNumericGrade()) {
                if (gradeNum >= 0 && gradeNum <= 5) {
                    this.gradeNum = gradeNum;
                }
            } else {
                char grade = (char) gradeNum;
                if (grade == 'A' || grade == 'a' || grade == 'F' || grade == 'f') {
                    this.gradeNum = Character.toUpperCase(grade);
                }
            }
        }

        if (this.yearCompleted == 0) {
            this.yearCompleted = Year.now().getValue();
        }
    }

    private boolean checkGradeValidity(final int gradeNum) {
        // Allow numeric grades (0-5)
        if (course.isNumericGrade()) {
            return (gradeNum >= 0 && gradeNum <= 5);
        } else {
            return (Character.toUpperCase(gradeNum) == 'A' || Character.toUpperCase(gradeNum) == 'F');
        } // Allow letter grades ('F' or 'A')
    }

    public boolean isPassed() {
        if (course == null) {
            return false; // Safety check
        }

        if (course.isNumericGrade()) {
            return gradeNum > 0; // Passed if grade is between 1-5
        } else {
            // Fix character comparison
            char gradeChar = (char) gradeNum;
            return gradeChar == 'A'; // Passed only if grade is 'A'
        }
    }

    public int getYear() {
        return this.yearCompleted;
    }

    public void setYear(final int year) {
        int currentYear = Year.now().getValue();
        if (2000 < year && year <= currentYear) {
            this.yearCompleted = year;
        }
    }

    public String toString() {
        String grade;

        if (gradeNum == 0) {
            grade = "Not graded";
        } else if (course.isNumericGrade()) {
            grade = String.valueOf(gradeNum);
        } else {
            // Ensure correct character comparison
            char gradeChar = (char) gradeNum;
            grade = (Character.toUpperCase(gradeChar) == 'F') ? "F" : "A";
        }

        return "[" + course.toString() + " Year: " + yearCompleted + ", Grade: " + grade + "]";
    }
}
