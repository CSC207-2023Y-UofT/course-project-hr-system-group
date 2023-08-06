package drivers;

import entities.Employee;
import entities.HRSystem;
import interfaceadapters.ScheduleController;
import usecases.ScheduleFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            String fileName = "ScheduleData.csv";
            String currentWorkingDirectory = System.getProperty("user.dir");
            String filePath = currentWorkingDirectory + "\\src\\main\\java\\resources\\" + fileName;
            List<String[]> data = ScheduleFileReader.readCSV(filePath);

            // Sample HRSystem
            ArrayList<Employee> employees = new ArrayList<>();
            employees.add(new Employee("001", "name"));
            employees.add(new Employee("002", "name"));
            employees.add(new Employee("003", "name"));
            employees.add(new Employee("004", "name"));
            employees.add(new Employee("005", "name"));
            HRSystem hrSystem = new HRSystem(employees);

            ScheduleUI scheduleUI = new ScheduleUI();

            ScheduleController scheduleController = new ScheduleController(hrSystem, scheduleUI, data);
            scheduleController.openUI();
        } catch (IOException e) {
            System.out.println("Failed to open and read the schedule .csv file.");
        }

    }

}