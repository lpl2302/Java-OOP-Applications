package dev.m3s.programming2.homework3;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private int currentYear = Year.now().getValue();
    private int id;
    private int startYear = currentYear; //Default is current year
    private int graduationYear = 0;
    private int degreeCount = 3;
    private List<Degree> degrees = new ArrayList<>(degreeCount);

    // Constructors
    public Student(String lName, String fName) {
        super(lName, fName);
        this.id = getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID);
        for (int i = 0; i < degreeCount; i++) {
            degrees.add(new Degree());
        }
    }

    // Methods
    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID) {
            this.id = id;
        }
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(final int startYear) {
        if (startYear > 2000 && startYear <= currentYear) {
            this.startYear = startYear;
        }
    }

    public int getGraduationYear() {
        return this.graduationYear;
    }

    public String setGraduationYear(final int graduationYear) {
        // check if student completed all required credits
        if (!canGraduate()) {
            return "Check amount of required credits";
        }

        // from the start of the studies to this date
        if (graduationYear < startYear || graduationYear > currentYear) {
            return "Check graduation year";
        }

        // both cases are fine
        this.graduationYear = graduationYear;
        return "Ok";
    }

    public void setDegreeTitle(final int i, String dName) {
        if (i >= 0 && i < degrees.size() && dName != null) {
            degrees.get(i).setDegreeTitle(dName);  // set title
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degrees.size() && course != null) {
            return degrees.get(i).addStudentCourse(course);
        } else {
            return false;
        }
    }

    public int addCourses(final int i, List<StudentCourse> courses) {
        if (i >= 0 && i < degrees.size() && courses != null) {
            int addedCourses = 0;
            for (StudentCourse course : courses) {
                if (course != null && degrees.get(i).addStudentCourse(course)) {
                    addedCourses++;
                }
            }
            return addedCourses;
        } else {
            return 0;
        }
    }

    public void printCourses() {
        for (Degree degree : degrees) {
            if (degree != null) {
                degree.printCourses();
            }
        }
    }

    public void printDegrees() {
        for (Degree degree : degrees) {
            if (degree != null) {
                System.out.println(degree);
            }
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degrees.size() && title != null) {
            degrees.get(i).setTitleOfThesis(title);
        } else {
            System.out.println("Invalid index or null title.");
        }
    }

    public boolean hasGraduated(){
        if (graduationYear != 0 & graduationYear <= currentYear & canGraduate()){
            return true;
        } else{
            return false;
        }
    }

    private boolean canGraduate(){
        Degree bachelor = degrees.get(ConstantValues.BACHELOR_TYPE);
        Degree master = degrees.get(ConstantValues.MASTER_TYPE);
        boolean bachelorCompleted = bachelor.getTitleOfThesis() != ConstantValues.NO_TITLE
                && bachelor.getCredits() >= ConstantValues.BACHELOR_CREDITS
                && bachelor.getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.BACHELOR_MANDATORY;
        boolean masterCompleted = master.getTitleOfThesis() != ConstantValues.NO_TITLE
                && master.getCredits() >= ConstantValues.MASTER_CREDITS
                && bachelor.getCreditsByType(ConstantValues.MANDATORY) >= ConstantValues.MASTER_MANDATORY;

        return bachelorCompleted && masterCompleted;
    }

    public int getStudyYears(){
        if(hasGraduated()){
            return (getGraduationYear() - getStartYear());
        }else{
            return (currentYear - getStartYear());
        }
    }

    @Override
    String getIdString() {
        return "Student ID: " + id;
    }

    //TO DO:  GPA for bachelor and master
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIdString()).append("\n");
        sb.append("First name: ").append(getFirstName()).append(", Last name: ").append(getLastName()).append("\n");
        sb.append("Date of birth: \"").append(getBirthDate()).append("\"\n");
        sb.append("Status: ").append(hasGraduated() ? "The student has graduated in " + graduationYear  : "The student has not graduated, yet").append("\n");
        sb.append("Start year: ").append(startYear).append(" (studies have lasted for ").append(getStudyYears()).append(" years)").append("\n");

        // Calculate total credits, bachelor credits, and master credits
        double bachelorCredits = degrees.get(ConstantValues.BACHELOR_TYPE).getCredits();
        double masterCredits = degrees.get(ConstantValues.MASTER_TYPE).getCredits();

        double bachelorGPA = degrees.get(ConstantValues.BACHELOR_TYPE).getGPA(ConstantValues.ALL).get(2);
        double masterGPA = degrees.get(ConstantValues.MASTER_TYPE).getGPA(ConstantValues.ALL).get(2);

        double totalCredits = bachelorCredits + masterCredits;

        // Get total GPA by dividing total grade of both degrees by the total numeric course coutn of both degrees
        double totalGPA = (degrees.get(ConstantValues.BACHELOR_TYPE).getGPA(ConstantValues.ALL).get(0) + degrees.get(ConstantValues.MASTER_TYPE).getGPA(ConstantValues.ALL).get(0))
                / (degrees.get(ConstantValues.BACHELOR_TYPE).getGPA(ConstantValues.ALL).get(1) + degrees.get(ConstantValues.MASTER_TYPE).getGPA(ConstantValues.ALL).get(1));

        //Format the GPA
        totalGPA = Double.parseDouble(String.format("%.2f", totalGPA));

        // Append total credits and total GPA
        sb.append("Total credits: " + totalCredits + " (GPA = " + totalGPA + ")").append("\n");

        // Get status of degrees
        String bachelorCreditStatus = (bachelorCredits >= ConstantValues.BACHELOR_CREDITS) ? "Total bachelor credits completed (" + bachelorCredits + "/180.0)" : "Missing bachelor credits " + String.format("%.1f", ConstantValues.BACHELOR_CREDITS - bachelorCredits) + " (" + bachelorCredits + "/180.0)";
        String mastersCreditStatus = (masterCredits >= ConstantValues.MASTER_CREDITS) ? "Total master's credits completed (" + masterCredits + "/120.0)" : "Missing master's credits " + String.format("%.1f", ConstantValues.MASTER_CREDITS - masterCredits) + " (" + masterCredits + "/120.0)";

        String bachelorMandatoryStatus = (bachelorCredits >= ConstantValues.BACHELOR_MANDATORY) ? "All mandatory bachelor credits completed (" + bachelorCredits + "/150)" : "Missing mandatory bachelor credits " + String.format("%.1f", ConstantValues.BACHELOR_MANDATORY - bachelorCredits) + " (" + bachelorCredits + "/150.0)";
        String masterMandatoryStatus = (masterCredits >= ConstantValues.MASTER_MANDATORY) ? "All mandatory master credits completed (" + masterCredits + "/50)" : "Missing mandatory master credits " + String.format("%.1f", ConstantValues.MASTER_MANDATORY - masterCredits) + " (" + masterCredits + "/50.0)";

        String bachelorGPAStatus = "GPA of Bachelor study: " + bachelorGPA;
        String masterGPAStatus = "GPA of Master study: " + masterGPA;

        // Append bachelor credits (all and mandatory)
        sb.append("Bachelor credits: ").append(bachelorCredits).append("\n");
        sb.append(bachelorCreditStatus).append("\n");
        sb.append(bachelorMandatoryStatus).append("\n");
        sb.append(bachelorGPAStatus).append("\n");
        // Append title of BSc Thesis
        Degree bachelorDegree = degrees.get(ConstantValues.BACHELOR_TYPE);
        if (bachelorDegree != null) {
            sb.append("Title of BSc Thesis: \"").append(bachelorDegree.getTitleOfThesis()).append("\"\n");
        }

        // Append master credits (all and mandatory)
        sb.append("Master credits: ").append(masterCredits).append("\n");
        sb.append(mastersCreditStatus).append("\n");
        sb.append(masterMandatoryStatus).append("\n");
        sb.append(masterGPAStatus).append("\n");
        // Append title of MSc Thesis
        Degree masterDegree = degrees.get(ConstantValues.MASTER_TYPE);
        if (masterDegree != null) {
            sb.append("Title of MSc Thesis: \"").append(masterDegree.getTitleOfThesis()).append("\"\n");
        }

        return sb.toString();
    }

}
