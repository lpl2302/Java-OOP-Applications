package dev.m3s.programming2.homework3;

import java.util.Random;

public abstract class Person {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NO_BIRTHDATE; // in the format dd.mm.yyyy

    // Constructors
    public Person(String lastName, String firstName) {
        if (lastName != null){
            this.lastName = lastName;
        }

        if (firstName != null){
            this.firstName = firstName;
        }
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

    public String getBirthDate() {
        return this.birthDate;
    }

    public String setBirthDate(String personId) {
        PersonID personID = new PersonID();
        if(personId != null){
            if (personID.setPersonID(personId) == "Ok") {
                this.birthDate = personID.getBirthDate();
                return this.birthDate;
            }
        }
        return "No change";
    }

    protected int getRandomId(final int min, final int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min; // Generates a random number between min and max (inclusive)
    }

    abstract String getIdString();
}
