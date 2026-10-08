package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class Degree {

    // attributes
    private static final int MAX_COURSES = 50;
    private int count = 0;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private List<StudentCourse> myCourses = new ArrayList<>(MAX_COURSES);

    // methods
    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<StudentCourse> getCourses() {
        return this.myCourses;
    }

    public void addStudentCourses(List<StudentCourse> courses) {
        if (courses != null) {
            for (StudentCourse course : courses) {
                addStudentCourse(course);
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && count < MAX_COURSES) {
            myCourses.add(course);
            count++;
            return true; // Course added successfully
        }
        return false; // Course could not be added
    }

    public String getDegreeTitle() {
        return this.degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        if (degreeTitle != null) {
            this.degreeTitle = degreeTitle;
        }
    }

    public String getTitleOfThesis() {
        return this.titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis) {
        if (titleOfThesis != null) {
            this.titleOfThesis = titleOfThesis;
        }
    }

    public double getCreditsByBase(Character base) {
        // return the sum of the credits for all completed courses for a given base (‘P’, ‘A’ or ‘S’).
        double totalCredits = 0.0;
        char upperBase = Character.toUpperCase(base);
        if (upperBase != 'A' && upperBase != 'P' && upperBase != 'S') {
            return 0.0;
        }

        for (StudentCourse course : myCourses) {
            if (course != null && course.getCourse().getCourseBase() == base && isCourseCompleted(course)) {
                totalCredits += course.getCourse().getCredits();
            }
        }
        return totalCredits;
    }

    public double getCreditsByType(final int courseType) {
        // similar to getcreditsbybase, with type instead of base
        double totalCredits = 0.0;
        if (courseType != ConstantValues.OPTIONAL && courseType != ConstantValues.MANDATORY) { // if not optional/mandatory
            return 0.0;
        }

        for (StudentCourse course : myCourses) {
            if (course != null && course.getCourse().getCourseType() == courseType && isCourseCompleted(course)) {
                totalCredits += course.getCourse().getCredits();
            }
        }
        return totalCredits;
    }

    public double getCredits() {
        // return sum of all completed courses
        double totalCredits = 0.0;
        for (StudentCourse course : myCourses) {
            if (isCourseCompleted(course)) {
                totalCredits += course.getCourse().getCredits();
            }
        }
        return totalCredits;
    }

    private boolean isCourseCompleted(StudentCourse c) {
        if (c != null && c.isPassed()) {
            return true;
        } else {
            return false;
        }
    }

    public void printCourses() {
        StringBuilder sb = new StringBuilder();
        int courseCount = 0;
        for (StudentCourse course: myCourses) {
            if (course != null) {
                courseCount++;
                sb.append("\n" + "        " + courseCount + ". " + course.toString());
            }
        }
        System.out.println(sb.toString());
    }

    public List<Double> getGPA(int type) {
        List<Double> gpaList = new ArrayList<>();
        double sum = 0.0;
        double count = 0;
        double average = 0.0;

        if (type != ConstantValues.OPTIONAL && type != ConstantValues.MANDATORY && type != ConstantValues.ALL) {
            gpaList.add(0.0);
            gpaList.add(0.0);
            gpaList.add(0.0);
            return gpaList;
        }

        List<StudentCourse> coursesByType = new ArrayList<>();
        for (StudentCourse course : myCourses) {
            if (type == ConstantValues.ALL || (course != null && course.getCourse().getCourseType() == type)) {
                coursesByType.add(course);
            }
        }

        for (StudentCourse course : coursesByType) {
            if (course.getCourse().isNumericGrade()) {
                sum += course.getGradeNum();
                count++;
            }
        }

        average = count > 0 ? sum / count : 0.0;
        average = Double.parseDouble(String.format("%.2f", average));

        gpaList.add(sum);
        gpaList.add(count);
        gpaList.add(average);

        return gpaList;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Degree [Title: \"" + degreeTitle + "\" (courses: " + count + ")\n");
        result.append("Thesis title: \"" + titleOfThesis + "\"");

        int courseCount = 0;
        for (StudentCourse course : myCourses) {
            if (course != null) {
                courseCount++;
                result.append("\n" + "        " + courseCount + ". " + course.toString());
            }
        }

        result.append("]");
        return result.toString();
    }
}
