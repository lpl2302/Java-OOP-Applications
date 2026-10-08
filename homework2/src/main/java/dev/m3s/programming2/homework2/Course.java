package dev.m3s.programming2.homework2;

public class Course {

    // Course - Attributes
    private String name;
    private String courseCode; // Code of the course, e.g., 811322A.
    private Character courseBase; // One of the values ‘A’, ‘P’ or ‘S’.
    private int courseType; // 0 = Optional, 1 = Mandatory
    private int period; // 1-5
    private double credits; // Valid values are MIN_CREDITS <= credits <= MAX_COURSE_CREDITS
    private boolean numericGrade;

    // Course - constructors
    // without para
    public Course() {
    }

    // with para
    public Course(String name, final int code, Character courseBase, final int type, final int period,
                  final double credits, boolean numericGrade) {
        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        this.numericGrade = numericGrade;
    }

    public Course(Course course) {
        this.name = course.getName();
        this.courseCode = course.getCourseCode();
        this.courseBase = course.getCourseBase();
        this.courseType = course.getCourseType();
        this.period = course.getPeriod();
        this.credits = course.getCredits();
        this.numericGrade = course.isNumericGrade();
    }

    // Methods
    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public String getCourseTypeString() { // return optional or mandatory course type
        if (courseType == ConstantValues.OPTIONAL) {
            return "Optional";
        } else if (courseType == ConstantValues.MANDATORY) {
            return "Mandatory";
        } else {
            return "Unknown"; // throw an exception if courseType is neither 0 nor 1
        }
    }

    public int getCourseType() {
        return this.courseType;
    }

    public void setCourseType(final int type) {
        if (type == ConstantValues.MANDATORY) {
            this.courseType = ConstantValues.MANDATORY;
        } else if (type == ConstantValues.OPTIONAL) {
            this.courseType = ConstantValues.OPTIONAL;
        } else {
            System.out.println("Error: Type must be either " + ConstantValues.OPTIONAL + " or " + ConstantValues.MANDATORY + ".");
        }
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(final int courseCode, Character courseBase) {
        if (0 < courseCode && courseCode < 1000000 && (Character.toUpperCase(courseBase) == 'A' || Character.toUpperCase(courseBase) == 'P' || Character.toUpperCase(courseBase) == 'S')) {
            this.courseCode = String.valueOf(courseCode) + Character.toUpperCase(courseBase);
            this.courseBase = Character.toUpperCase(courseBase);
        }
    }

    public Character getCourseBase() {
        return this.courseBase;
    }

    public int getPeriod() {
        return this.period;
    }

    public void setPeriod(final int period) {
        if (ConstantValues.MIN_PERIOD <= period && period <= ConstantValues.MAX_PERIOD) {
            this.period = period;
        }
    }

    public double getCredits() {
        return this.credits;
    }

    private void setCredits(final double credits) { // set credits if the value is within range
        if (ConstantValues.MIN_CREDITS <= credits && credits <= ConstantValues.MAX_COURSE_CREDITS) {
            this.credits = credits;
        }
    }

    public boolean isNumericGrade() {
        return this.numericGrade;
    }

    public void setNumericGrade(boolean numericGrade) {
        this.numericGrade = numericGrade;
    }

    @Override
    public String toString() {
        String typeCourse = getCourseTypeString();
        return "[" + courseCode + " (" + String.format("%.2f", credits) + " cr), \"" + name + "\". " + typeCourse + ", period: " + period + ".]";
    }
}
