package interfaceadapters;

import drivers.ScheduleUI;
import entities.Employee;
import entities.HRSystem;
import entities.Schedule;
import usecases.AddEmployee;
import usecases.CreateSchedule;
import usecases.RemoveEmployee;
import usecases.ScheduleDataParser;

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
        System.out.println("Opened Schedule UI.");
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

}
