package interfaceadapters;

import drivers.ScheduleUI;
import entities.Employee;
import entities.HRSystem;
import entities.Schedule;
import entities.Shift;
import usecases.modifyemployees.CreateEmployee;
import usecases.modifyemployees.DeleteEmployee;
import usecases.modifyfiles.ScheduleDataParser;
import usecases.modifyfiles.ScheduleFileReader;
import usecases.modifyfiles.ScheduleFileWriter;
import usecases.modifyschedule.AddEmployee;
import usecases.modifyschedule.CreateSchedule;
import usecases.modifyschedule.RemoveEmployee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ScheduleController.java
 * Class for the ScheduleController.
 */
public class ScheduleController implements CreateSchedule, RemoveEmployee, AddEmployee, CreateEmployee, DeleteEmployee {

    private static Schedule schedule;
    private ScheduleUI scheduleUI;
    private static HRSystem hrSystem;
    private String[][] parsedData;

    public ScheduleController(HRSystem hrSystem, ScheduleUI scheduleUI, List<String[]> data) {
        this.hrSystem = hrSystem;

        ScheduleDataParser parser = new ScheduleDataParser(data);
        this.parsedData = parser.getParsedDataArray();

        schedule = createSchedule(this.parsedData);

        this.scheduleUI = scheduleUI;
    }

    /**
     * openUI
     * Calls createUI from the scheduleUI with the String[][] parsedData.
     */
    public void openUI() {
        this.scheduleUI.createUI(this.parsedData, this);
    }

    public void modifyShift(String str, int dayIndex, int shiftIndex, String employee) {
        switch(str) {
            case "Remove Employee":
                removeEmployee(schedule, dayIndex, shiftIndex, employee);
                return;
            case "Add Employee":
                addEmployee(schedule, dayIndex, shiftIndex, employee);
                return;
            default:
        }
    }

    /**
     * getAllEmployeesID
     * Gets an ArrayList<String> of all the employee IDs from HRSystem hrSystem.
     * @return ArrayList<String>, ArrayList of all the employee IDs.
     */
    public ArrayList<String> getAllEmployeesID() {
        ArrayList<Employee> allEmployees = hrSystem.getEmployees();
        ArrayList<String> allEmployeesID = new ArrayList<>();
        for (Employee employee : allEmployees) {
            allEmployeesID.add(employee.getId());
        }
        return allEmployeesID;
    }

    /**
     * callFileWriter
     * Calls readCSV from the ScheduleFileReader.
     */
    public static List<String[]> callFileReader() {
        try {
            String fileName = "ScheduleData.csv";
            String currentWorkingDirectory = System.getProperty("user.dir");
            String filePath = currentWorkingDirectory + "\\src\\main\\java\\resources\\" + fileName;
            return ScheduleFileReader.readCSV(filePath);
        } catch (IOException e) {
            System.out.println("Failed to open and read the schedule .csv file.");
        }
        return null;
    }

    /**
     * callFileWriter
     * Calls writeCSV from the ScheduleFileWriter.
     * @param data, String[][] representation of the Schedule data.
     */
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

    /**
     * addEmployee
     * Retrieves a shift and adds an employee accordingly to the given parameters.
     * @param schedule, overall schedule.
     * @param dayIndex, int for index employee is to be added to.
     * @param shiftIndex, int for index of shift to be modified.
     * @param employee, String for employee to be added.
     */
    @Override
    public void addEmployee(Schedule schedule, int dayIndex, int shiftIndex, String employee) {
        Shift shift = schedule.getShift(shiftIndex);
        shift.addEmployee(dayIndex, employee);
    }

    /**
     * removeEmployee
     * Retrieves a shift and removes an employee accordingly to the given parameters.
     * @param schedule, overall schedule.
     * @param dayIndex, int for index employee is to be removed from.
     * @param shiftIndex, int for index of shift to be modified.
     * @param employee, String for employee to be removed.
     */
    @Override
    public void removeEmployee(Schedule schedule, int dayIndex, int shiftIndex, String employee) {
        Shift shift = schedule.getShift(shiftIndex);
        shift.removeEmployee(dayIndex, employee);
    }

    /**
     * createSchedule
     * Creates a new Schedule according to the given String[][] data.
     * @param data, String[][] representation of the Schedule data.
     */
    @Override
    public Schedule createSchedule(String[][] data) {
        Schedule schedule = new Schedule();
        for (String[] lst : data) {
            ArrayList<String> employees = new ArrayList();

            for (int i = 1; i < lst.length; i++) {
                employees.add(lst[i]);
            }

            Shift shift = new Shift(lst[0], employees);
            schedule.addShift(shift);
        }
        return schedule;
    }

    /**
     * createEmployee
     * Attempts to create an employee in the HRSystem given an ID  and name.
     * @param newId, String ID of employee to be created.
     * @param name, String name of employee to be created.
     * @return boolean, true if Employee is successfully created, false otherwise.
     */
    @Override
    public boolean createEmployee(String newId, String name) {
        ArrayList<String> employeeIDs = getAllEmployeesID();
        for (String id : employeeIDs) {
            if (newId.equals(id)) {
                return false;
            }
        }
        Employee employee = new Employee(newId, name);
        hrSystem.addEmployee(employee);
        return true;
    }

    /**
     * deleteEmployee
     * Deletes an employee in the HRSystem.
     * @param id, String ID of employee to be deleted.
     * @return boolean, true if Employee is successfully deleted, false otherwise.
     */
    @Override
    public boolean deleteEmployee(String id) {
        if (schedule.shiftsContainsEmployee(id)) {
            return false;
        }
        hrSystem.removeEmployee(id);
        return true;
    }
}
