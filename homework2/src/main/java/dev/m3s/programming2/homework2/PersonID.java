package dev.m3s.programming2.homework2;

public class PersonID {

    private String birthDate = ConstantValues.NO_BIRTHDATE; //Format: dd.mm.yyyy

    public String getBirthDate() {
        if (birthDate != null) {
            return birthDate;
        } else {
            return ConstantValues.NO_BIRTHDATE;
        }
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
