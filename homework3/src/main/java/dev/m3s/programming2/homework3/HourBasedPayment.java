package dev.m3s.programming2.homework3;

public class HourBasedPayment implements Payment {

    private double eurosPerHour;
    private double hours;

    // Methods
    public double getEurosPerHour() {
        return this.eurosPerHour;
    }

    public void setEurosPerHour(double eurosPerHour) {
        if (eurosPerHour > 0.0) {
            this.eurosPerHour = eurosPerHour;
        }
    }

    public double getHours() {
        return this.hours;
    }

    public void setHours(double hours) {
        if (hours > 0.0) {
            this.hours = hours;
        }
    }

    @Override
    public double calculatePayment() {
        double salary = hours * eurosPerHour;
        return salary;
    }
}
