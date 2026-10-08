package dev.m3s.programming2.homework3;

import java.time.Year;

public abstract class Employee extends Person implements Payment{

    private String empId; // format “xxxx_nnnn”, randomize
    private int startYear = Year.now().getValue(); // 2000 < startYear <= current year
    private Payment payment; // either of type HourBasedPayment or MonthlyPayment.

    // Constructors
    public Employee(String lname, String fname) {
        // sets the last name and the first name
        super(lname, fname);

        // generates the empId string
        int idString = getRandomId(ConstantValues.MIN_EMP_ID, ConstantValues.MAX_EMP_ID);
        String empString = getEmployeeIdString();
        this.empId = empString + idString;

    }

    // Methods
    public String getIdString() {
        return this.empId;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(final int startYear) {
        if (2001 <= startYear && startYear <= Year.now().getValue()) {
            this.startYear = startYear;
        }
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        if (payment != null) {
            this.payment = payment;
        }
    }

    public double calculatePayment() {
        if (payment != null) {
            return payment.calculatePayment();
        } else {
            return 0.0; // payment is null, return 0.0
        }
    }

    protected abstract String getEmployeeIdString();
}
