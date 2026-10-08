package dev.m3s.programming2.homework1;

import java.time.Year;
import java.util.Random;


public class Student {

    // Student - attributes
    private String firstName;
    private String lastName;
    private int id;
    private double bachelorCredits; // min = 0, max = 300, min 180 to graduate
    private double masterCredits; // min = 0, max = 300, min 120 to graduate
    private String titleOfMastersThesis;
    private String titleOfBachelorsThesis;
    private int startYear = Year.now().getValue();
    private int graduationYear;
    private String birthDate;

    // Student - constructors
    // without parameters
    public Student() {
        // The id for the student will be set in the constructor
        this.id = getRandomId();
        this.firstName = ConstantValues.NO_NAME;
        this.lastName = ConstantValues.NO_NAME;
        this.birthDate = ConstantValues.NO_BIRTHDATE;
        this.titleOfBachelorsThesis = ConstantValues.NO_TITLE;
        this.titleOfMastersThesis = ConstantValues.NO_TITLE;
    }

    // with parameters
    public Student(String lname, String fname) {
        if (lname != null) {
            this.lastName = lname;
        } else {
            this.lastName = ConstantValues.NO_NAME;
        }

        if (fname != null) {
            this.firstName = fname;
        } else {
            this.firstName = ConstantValues.NO_NAME;
        }

        this.id = getRandomId();
        this.birthDate = ConstantValues.NO_BIRTHDATE;
        this.titleOfBachelorsThesis = ConstantValues.NO_TITLE;
        this.titleOfMastersThesis = ConstantValues.NO_TITLE;
    }

    // Student - methods
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
        } else {
            System.out.println("Invalid ID! Allowed values are 1-100.");
        }
    }

    public double getBachelorCredits() {
        return this.bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits) {
        if (bachelorCredits >= ConstantValues.MIN_CREDITS && bachelorCredits <= ConstantValues.MAX_CREDITS) {
            this.bachelorCredits = bachelorCredits;
        }
    }

    public double getMasterCredits() {
        return this.masterCredits;
    }

    public void setMasterCredits(final double masterCredits) {
        if (masterCredits >= ConstantValues.MIN_CREDITS && masterCredits <= ConstantValues.MAX_CREDITS) {
            this.masterCredits = masterCredits;
        }
    }

    public String getTitleOfMastersThesis() {
        return this.titleOfMastersThesis;
    }

    public void setTitleOfMastersThesis(final String titleOfMastersThesis) {
        if (titleOfMastersThesis != null) { // not null
            this.titleOfMastersThesis = titleOfMastersThesis;
        }
    }

    public String getTitleOfBachelorsThesis() {
        return this.titleOfBachelorsThesis;
    }

    public void setTitleOfBachelorsThesis (final String titleOfBachelorsThesis) {
        if (titleOfBachelorsThesis != null) { // not null
            this.titleOfBachelorsThesis = titleOfBachelorsThesis;
        }
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(final int startYear) {
        if (startYear > 2000 && startYear <= Year.now().getValue()) { // Conditions to set start year
            this.startYear = startYear;
        } else {
            this.startYear = Year.now().getValue(); // Set start year to current year if start year is invalid
        }
    }

    public int getGraduationYear() {
        return this.graduationYear;
    }

    public String setGraduationYear(final int graduationYear) {
        // Check gradutation status
        if (!canGraduate()) {
            return "Check the required studies";
        }

        // Check timeframe
        int currentYear = Year.now().getValue();
        if (graduationYear > currentYear || graduationYear < startYear) {
            return "Check graduation year";
        }

        // Both conditions met
        this.graduationYear = graduationYear;
        return "Ok";
    }

    public boolean hasGraduated() {
        if (graduationYear == 0) {
            return false;
        }

        int currentYear = Year.now().getValue();

        // Check if the current year >= the graduation year
        return currentYear >= graduationYear;
    }

    private boolean canGraduate() {
        // Check required credits
        if (bachelorCredits >= ConstantValues.BACHELOR_CREDITS) {
            if (masterCredits >= ConstantValues.MASTER_CREDITS) {
                if (titleOfBachelorsThesis != null && titleOfBachelorsThesis != ConstantValues.NO_TITLE) {
                    if (titleOfMastersThesis != null && titleOfMastersThesis != ConstantValues.NO_TITLE) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public int getStudyYears() {
        // Get the current year
        int currentYear = Year.now().getValue();

        startYear = getStartYear();

        int endYear;
        if (graduationYear == 0) {
            endYear = currentYear;
        } else {
            endYear = graduationYear;
        }

        // Number of study years
        return endYear - startYear;
    }

    private int getRandomId() {
        Random rand = new Random();
        return rand.nextInt(100) + 1; // Generates a random number between 1 and 100 (inclusive)
    }

    @Override
    public String toString() {

        // Status of graduation
        String status;
        if (graduationYear == 0) {
            status = "The student has not graduated, yet.";
        } else {
            status = "The student has graduated in " + graduationYear;
        }

        // Calculate the duration of studies
        int currentYear = Year.now().getValue();
        int yearsOfStudy;
        if (graduationYear == 0) {
            yearsOfStudy = currentYear - startYear;
        } else {
            yearsOfStudy = graduationYear - startYear;
        }

        String bachelorCreditStatus = (bachelorCredits >= 180.0) ? "All required bachelor credits completed" + "(" + bachelorCredits + "/180.0)" : "Missing bachelor credits " + String.format("%.1f", 180.0 - bachelorCredits) + " (" + bachelorCredits + "/180.0)";
        String mastersCreditStatus = (masterCredits >= 120.0) ? "All required master's credits completed" + "(" + masterCredits + "/120.0)" : "Missing master credits " + String.format("%.1f", 120.0 - masterCredits) + " (" + masterCredits + "/120.0)";

        return "Student id: " + id + "\n" +
                "FirstName: " + firstName + ", LastName: " + lastName + "\n" +
                "Date of birth: " + birthDate + "\n" +
                "Status: " + status + "\n" +
                "StartYear: " + startYear + " (studies have lasted for " + yearsOfStudy + " years)\n" +
                "BachelorCredits: " + bachelorCredits + " ==> " + bachelorCreditStatus + "\n" +
                "TitleOfBachelorThesis: " + titleOfBachelorsThesis + "\n" +
                "MasterCredits: " + masterCredits + " ==> " + mastersCreditStatus + "\n" +
                "TitleOfMastersThesis: " + titleOfMastersThesis + "\n";
    }

    public String setPersonId(final String personID) {
        // Check if personId is null
        if (personID == null) {
            return ConstantValues.INVALID_BIRTHDAY;
        }

        // Check if personID has been given the correct way
        if (!checkPersonIDNumber(personID)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }

        // Format the given personID to dd.mm.yyyy + swap the century character with the number
        String formattedDate = "";
        if (personID.charAt(6) == '+') {
            formattedDate = personID.substring(0, 2) + "." + personID.substring(2, 4) + "." + "18" + personID.substring(4, 6);
        } else if (personID.charAt(6) == '-') {
            formattedDate = personID.substring(0, 2) + "." + personID.substring(2, 4) + "." + "19" + personID.substring(4, 6);
        } else if (personID.charAt(6) == 'A') {
            formattedDate = personID.substring(0, 2) + "." + personID.substring(2, 4) + "." + "20" + personID.substring(4, 6);
        }  else {
        return ConstantValues.INVALID_BIRTHDAY; // Handle unexpected century character
        }

        // Ensure the values in that date are correct
        if (!checkBirthdate(formattedDate)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }

        // Check the check character in the original, given personID
        if (!checkValidCharacter(personID)) {
            return ConstantValues.INCORRECT_CHECKMARK;
        }

        // All checks passed, set the birthdate and return "Ok"
        this.birthDate = formattedDate;
        return "Ok";
    }

    private boolean checkPersonIDNumber(final String personID) {
        // Check if personID has 11 characters
        if (personID.length() != 11) {
            return false;
        }

        // Check if the "century character" is valid ('+', '-', or 'A')
        char centuryChar = personID.charAt(6);
        return (centuryChar == '+' || centuryChar == '-' || centuryChar == 'A');
    }

    private boolean checkLeapYear(int year) {
        // divide by 4 with normal year, divide by 400 with thousand year
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkValidCharacter(final String personID) {
        // Get the personal identity code without the last character and century character
        String codeToCheck = personID.substring(0, 6) + personID.substring(7, personID.length() - 1);

        // Convert the code to a long integer
        long codeNumber = Long.parseLong(codeToCheck);

        // Calculate the remainder when dividing the code number by 31
        int remainder = (int) (codeNumber % 31);

        // Define what the valid characters can be
        String validCharacters = "0123456789ABCDEFHJKLMNPRSTUVWXY";

        // Get the expected last character based on the remainder
        char expectedChar = validCharacters.charAt(remainder);

        // Get the actual control character from the person ID
        char actualChar = personID.charAt(personID.length() - 1);

        // Check if the actual last character matches the expected one
        return actualChar == expectedChar;
    }

    private boolean checkBirthdate(final String date) {
        // Split the date string into day, month, and year
        String[] parts = date.split("\\.");
        if (parts.length != 3) {
            return false; // Invalid format
        }

        //Assign date parts into day, month, and year
        int day, month, year;
        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]);
            year = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            return false; // Invalid format
        }

        // Check if year, month, and day are within valid ranges
        if (year <= 0 || month < 1 || month > 12 || day < 1 || day > 31) {
            return false; // Invalid ranges
        }

        // Check if the given days are valid for the given month
        //February
        if (month == 2) {
            if (day > 29) {
                return false;
            } else if (day == 29) { // Leap year
                return checkLeapYear(year);
            } else {
                return true;
            }
            //30-day months
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return day <= 30;
            //31-day months
        } else {
            return true;
        }
    }

}