package interfaceadapters;

import drivers.ScheduleUI;
import entities.Employee;
import entities.HRSystem;
import entities.Schedule;
import usecases.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleController implements CreateSchedule, RemoveEmployee, AddEmployee {

    static Schedule schedule;
    ScheduleUI scheduleUI;
    static HRSystem hrSystem;
    String[][] parsedData;

    public ScheduleController(HRSystem hrSystem, ScheduleUI scheduleUI, List<String[]> data) {
        this.hrSystem = hrSystem;

        ScheduleDataParser parser = new ScheduleDataParser(data);
        this.parsedData = parser.getParsedDataArray();

        schedule = this.createSchedule(this.parsedData);

        this.scheduleUI = scheduleUI;
    }

    public void openUI() {
        this.scheduleUI.createUI(this.parsedData);
    }

    public static void modifyShift(String str, int dayIndex, int shiftIndex, String employee) {
        switch(str) {
            case "Remove Employee":
                RemoveEmployee.removeEmployee(schedule, dayIndex, shiftIndex, employee);
                return;
            case "Add Employee":
                AddEmployee.addEmployee(schedule, dayIndex, shiftIndex, employee);
                return;
            default:
        }
    }

    public static ArrayList<String> getAllEmployeesID() {
        ArrayList<Employee> allEmployees = hrSystem.getEmployees();
        ArrayList<String> allEmployeesID = new ArrayList<>();
        for (Employee employee : allEmployees) {
            allEmployeesID.add(employee.getId());
        }
        return allEmployeesID;
    }

    public static void callFileWriter(String[][] data) {
        try {
            String fileName = "ScheduleData.csv";
            String currentWorkingDirectory = System.getProperty("user.dir");
            String filePath = currentWorkingDirectory + "\\src\\main\\java\\resources\\" + fileName;
            ScheduleFileWriter.writeCSV(data, filePath);
        } catch (IOException e) {
            System.out.println("Failed to open and write to the schedule .csv file.");
        }
    }

}
