package entities;

import java.util.ArrayList;

/**
 * Schedule.java
 * Class for a Shift.
 * Employees in the shift are stored in ArrayList<String> employees. Each index of employees represents a day in the
 * week, from Sunday (index = 0) to Saturday (index = 6). Each String employee is separated by ", ".
 */
public class Shift<Employee> {

    private String duration;
    private ArrayList<String> employees;

    public Shift(String duration, ArrayList<String> employees) {
        this.duration = duration;
        this.employees = employees;
    }

    /**
     * containsEmployee
     * Returns whether this Shift contains an Employee with String id.
     * @param id, String for id of employee to be compared.
     */
    public boolean containsEmployee(String id) {
        for (String compareId : employees) {
            String [] compareIds = compareId.split(", ");
            for (String str : compareIds) {
                if (id.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * containsEmployee
     * Returns whether this Shift at the given dayIndex contains an Employee with String id.
     * @param id, String for id of employee to be compared.
     * @param dayIndex, int for specific day to be checked.
     */
    public boolean containsEmployee(String id, int dayIndex) {
        String compareId = employees.get(dayIndex);
        String [] compareIds = compareId.split(", ");
        for (String str : compareIds) {
            if (id.equals(str)) {
                return true;
            }
        }
        return false;
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
        String day = this.employees.get(dayIndex);
        if (day.contains(employee + ", ")) {
            this.employees.set(dayIndex, day.replace(employee + ", ", ""));
        } else if (day.contains(", " + employee)) {
            this.employees.set(dayIndex, day.replace(", " + employee, ""));
        } else {
            this.employees.set(dayIndex, day.replace(employee, ""));
        }
    }
}
