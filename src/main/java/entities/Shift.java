package entities;

import java.util.ArrayList;

public class Shift<Employee> {

    private String duration;
    private ArrayList<String> employees;

    public Shift(String duration, ArrayList<String> employees) {
        this.duration = duration;
        this.employees = employees;

        System.out.println(employees);
    }

    public String getDuration() {
        return this.duration;
    }

    public ArrayList<String> getEmployees() {
        return this.employees;
    }

    public void addEmployee(int dayIndex, String employee) {
        String day = this.employees.get(dayIndex);
        day += ", " + employee;
        employees.set(dayIndex, day);
    }

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
