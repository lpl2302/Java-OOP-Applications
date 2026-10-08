package dev.m3s.programming2.homework2;

import java.time.Year;
import java.util.Random;

public class Student {

    // Attributes
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private PersonID personID = new PersonID();
    private int id;
    private int startYear = Year.now().getValue(); //Default is current year
    private int graduationYear = 0;
    private int degreeCount;
    private Degree[] degrees;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    // Constructors
    public Student() {
        this.firstName = ConstantValues.NO_NAME;
        this.lastName = ConstantValues.NO_NAME;
        this.id = getRandomId();
        this.degreeCount = 3; // Default num
        this.degrees = new Degree[degreeCount]; // Default arr
        this.startYear = Year.now().getValue();

        // Initialize all degrees in the array
        for (int i = 0; i < degreeCount; i++) {
            degrees[i] = new Degree();
        }
    }

    public Student(String lname, String fname) {
        this(); // Call the no-argument constructor

        if (lname != null) {
            this.lastName = lname;
        }

        if (fname != null) {
            this.firstName = fname;
        }

        this.id = getRandomId();
    }

    // Methods
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID) {
            this.id = id;
        }
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int startYear) {
        if (startYear > 2000 && startYear <= Year.now().getValue()) { // Conditions to set start year
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
        if (graduationYear < startYear || graduationYear > Year.now().getValue()) {
            return "Check graduation year";
        }

        // both cases are fine
        this.graduationYear = graduationYear;
        return "Ok";
    }

    public void setDegreeTitle(final int i, String dName) {
        if (0 <= i && i <= degreeCount && dName != null) {
            degrees[i].setDegreeTitle(dName);  // set title
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (0 <= i && i <= degreeCount && course != null) {
            return degrees[i].addStudentCourse(course); // add course
        } else {
            return false;
        }
    }

    public int addCourses(final int i, StudentCourse [] courses) {
        if (0 <= i && i <= degreeCount && courses != null) {
            int count = 0;
            for (StudentCourse course : courses) {
                if (course != null && degrees[i].addStudentCourse(course)) {
                    count++;
                }
            } return count;
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
        if (i >= 0 && i < degreeCount && title != null) {
            degrees[i].setTitleOfThesis(title);
        }
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public String setBirthDate(String personId) {
        if (personId != null) {
            if (personID.setPersonId(personId) == "Ok") {
                this.birthDate = personID.getBirthDate();
                return this.birthDate;
            }
        }
        return "No change";
    }

    public boolean hasGraduated() {
        if (graduationYear != 0 & graduationYear <= Year.now().getValue() & canGraduate()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean canGraduate() {
        Degree bachelor = degrees[ConstantValues.BACHELOR_TYPE];
        Degree master = degrees[ConstantValues.MASTER_TYPE];
        boolean bachelorCompleted = bachelor.getTitleOfThesis() != ConstantValues.NO_TITLE &&
                bachelor.getCredits() >= ConstantValues.BACHELOR_CREDITS;
        boolean masterCompleted = master.getTitleOfThesis() != ConstantValues.NO_TITLE &&
                master.getCredits() >= ConstantValues.MASTER_CREDITS;

        return bachelorCompleted && masterCompleted;
    }

    public int getStudyYears() {
        if (hasGraduated()) {
            return (getGraduationYear() - getStartYear());
        } else {
            return (Year.now().getValue() - getStartYear());
        }
    }

    private int getRandomId() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student id: ").append(id).append("\n");
        sb.append("First name: ").append(firstName).append(", Last name: ").append(lastName).append("\n");
        sb.append("Date of birth: \"").append(birthDate).append("\"\n");
        sb.append("Status: ").append(hasGraduated() ? "The student has graduated in " + graduationYear  : "The student has not graduated, yet").append("\n");
        sb.append("Start year: ").append(startYear).append(" (studies have lasted for ").append(getStudyYears()).append(" years)").append("\n");

        // Calculate total credits, bachelor credits, and master credits
        double bachelorCredits = degrees[ConstantValues.BACHELOR_TYPE].getCredits();
        double masterCredits = degrees[ConstantValues.MASTER_TYPE].getCredits();
        double totalCredits = bachelorCredits + masterCredits;

        // Append total credits
        sb.append("Total credits: ").append(totalCredits).append("\n");

        // Get status of degrees
        String bachelorCreditStatus = (bachelorCredits >= ConstantValues.BACHELOR_CREDITS) ? "Total bachelor credits completed (" + bachelorCredits + "/180.0)" : "Missing bachelor credits " + String.format("%.1f", ConstantValues.BACHELOR_CREDITS - bachelorCredits) + " (" + bachelorCredits + "/180.0)";
        String mastersCreditStatus = (masterCredits >= ConstantValues.MASTER_CREDITS) ? "Total master's credits completed (" + masterCredits + "/120.0)" : "Missing master's credits " + String.format("%.1f", ConstantValues.MASTER_CREDITS - masterCredits) + " (" + masterCredits + "/120.0)";

        // Append bachelor credits
        sb.append("Bachelor credits: ").append(bachelorCredits).append("\n");
        sb.append(bachelorCreditStatus).append("\n");
        // Append title of BSc Thesis
        Degree bachelorDegree = degrees[ConstantValues.BACHELOR_TYPE];
        if (bachelorDegree != null) {
            sb.append("Title of BSc Thesis: \"").append(bachelorDegree.getTitleOfThesis()).append("\"\n");
        }

        // Append master credits
        sb.append("Master credits: ").append(masterCredits).append("\n");
        sb.append(mastersCreditStatus).append("\n");
        // Append title of MSc Thesis
        Degree masterDegree = degrees[ConstantValues.MASTER_TYPE];
        if (masterDegree != null) {
            sb.append("Title of MSc Thesis: \"").append(masterDegree.getTitleOfThesis()).append("\"\n");
        }

        return sb.toString();
    }
}
