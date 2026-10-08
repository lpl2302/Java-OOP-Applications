package dev.m3s.programming2.homework1;

public class Main {
    public static void main(String[] args) {
        // 1. Create the first student using the no-parameter constructor
        Student student1 = new Student();

        // 2. Create the second student using the constructor with last name and first name
        Student student2 = new Student("Mouse", "Mickey");

        // 3. Create the third student using the constructor with last name and first name
        Student student3 = new Student("Mouse", "Minnie");

        // 4-11. Set attributes for the first student
        student1.setFirstName("Donald");
        student1.setLastName("Duck");
        student1.setBachelorCredits(120);
        student1.setMasterCredits(180);
        student1.setTitleOfMastersThesis("Masters thesis title");
        student1.setTitleOfBachelorsThesis("Bachelor thesis title");
        student1.setStartYear(2001);
        student1.setGraduationYear(2020);

        // 12-15. Set attributes for the second student
        student2.setPersonId("221199-123A");
        student2.setTitleOfBachelorsThesis("\"A new exciting purpose of life\"");
        student2.setBachelorCredits(65);
        student2.setMasterCredits(22);

        // 16-22. Set attributes for the third student
        student3.setPersonId("111111-3334");
        student3.setBachelorCredits(215);
        student3.setMasterCredits(120);
        student3.setTitleOfMastersThesis("\"Christmas - The most wonderful time of the year\"");
        student3.setTitleOfBachelorsThesis("\"Dreaming of a white Christmas\"");
        student3.setStartYear(2018);
        student3.setGraduationYear(2022);

        // 23-25. Print student details
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

        // 26-29. Test invalid person ID inputs
        System.out.println(student1.setPersonId("This is a string"));
        System.out.println(student1.setPersonId("320187-1234"));
        System.out.println(student1.setPersonId("11111111-3334"));
        System.out.println(student1.setPersonId("121298-830A"));
    }
}
