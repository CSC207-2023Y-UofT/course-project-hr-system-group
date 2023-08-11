package drivers;

import entities.Employee;
import entities.HRSystem;
import interfaceadapters.ScheduleController;

import java.util.ArrayList;
import java.util.List;

/**
 * Main.java
 * Temporary Main class for Schedule feature.
 */
public class Main {

    public static void main(String[] args) {
        // Sample HRSystem
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("001", "name"));
        employees.add(new Employee("002", "name"));
        employees.add(new Employee("003", "name"));
        employees.add(new Employee("004", "name"));
        employees.add(new Employee("005", "name"));
        HRSystem hrSystem = new HRSystem(employees);

        ScheduleUI scheduleUI = new ScheduleUI();
        List<String[]> data = ScheduleController.callFileReader();
        ScheduleController scheduleController = new ScheduleController(hrSystem, scheduleUI, data);
        scheduleController.openUI();
    }

}