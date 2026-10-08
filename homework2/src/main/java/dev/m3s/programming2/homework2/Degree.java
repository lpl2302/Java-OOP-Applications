package dev.m3s.programming2.homework2;

public class Degree {

    // attributes
    private static final int MAX_COURSES = 50;
    private int count = 0;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse [] myCourses = new StudentCourse[MAX_COURSES];

    // methods
    public StudentCourse [] getCourses() {
        return this.myCourses;
    }

    public void addStudentCourses(StudentCourse [] courses) {
        // if the object is not null
        if (courses != null) {
            for (StudentCourse course : courses) { // add courses
                addStudentCourse(course);
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        // if the method is not null and the number of added courses is less than MAX_COURSES
        if (course != null && count < MAX_COURSES) {
            myCourses[count++] = course; // udpate the value of count
            return true;
        }
        return false;
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
