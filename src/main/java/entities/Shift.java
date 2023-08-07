package entities;

import java.util.ArrayList;

/**
 * Schedule.java
 * Class for a Shift.
 */
public class Shift<Employee> {

    private String duration;
    private ArrayList<String> employees;

    public Shift(String duration, ArrayList<String> employees) {
        this.duration = duration;
        this.employees = employees;
    }

    /**
     * addEmployee
     * Adds an employee ArrayList<String> employees at the given index.
     * @param dayIndex, int for index employee is to be added to.
     * @param employee, String for employee to be added.
     */
    public void addEmployee(int dayIndex, String employee) {
        String day = this.employees.get(dayIndex);
        day += ", " + employee;
        employees.set(dayIndex, day);
    }

    /**
     * removeEmployee
     * Removes an employee ArrayList<String> employees at the given index.
     * @param dayIndex, int for index employee is to be removed from.
     * @param employee, String for employee to be removed.
     */
    public void removeEmployee(int dayIndex, String employee) {
        System.out.println(this.employees);
        String day = this.employees.get(dayIndex);
        if (day.contains(employee + ", ")) {
            day.replace(employee + ", ", "");
        } else if (day.contains(", " + employee)) {
            day.replace(", " + employee, "");
        } else {
            day.replace(employee, "");
        }
    }
}
