package entities;

import java.util.ArrayList;

public class HRSystem {

    private ArrayList<Employee> employees;

    public HRSystem(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(String id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                return;
            }
        }
    }

}
