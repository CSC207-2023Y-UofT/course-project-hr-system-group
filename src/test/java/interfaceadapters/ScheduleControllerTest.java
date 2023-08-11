package interfaceadapters;

import drivers.scheduleui.ScheduleUI;
import entities.HRSystem;
import entities.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleControllerTest {

    HRSystemController hrSystemController = new HRSystemController();
    HRSystem hrSystem = hrSystemController.createHRSystem();

    ScheduleUI scheduleUI = new ScheduleUI();
    List<String[]> data = ScheduleController.callFileReader();
    ScheduleController scheduleController = new ScheduleController(hrSystem, scheduleUI, data);
    Schedule schedule = scheduleController.getSchedule();

    @Test
    public void GetAllEmployeesID() {
        ArrayList<String> lst = new ArrayList<>();
        lst.add("001");
        lst.add("002");
        lst.add("003");
        scheduleController.createEmployee("001", "name");
        scheduleController.createEmployee("002", "name");
        scheduleController.createEmployee("003", "name");
        Assertions.assertEquals(lst, scheduleController.getAllEmployeesID());
    }

    @Test
    public void AddEmployeeFirstDay() {
        scheduleController.addEmployee(schedule, 0, 0, "001");
        Assertions.assertEquals(true, schedule.getShift(0).containsEmployee("001", 0));
    }

    @Test
    public void AddEmployeeMiddleDay() {
        scheduleController.addEmployee(schedule, 3, 0, "002");
        Assertions.assertEquals(true, schedule.getShift(0).containsEmployee("002", 3));
    }

    @Test
    public void AddEmployeeLastDay() {
        scheduleController.addEmployee(schedule, 6, 0, "002");
        Assertions.assertEquals(true, schedule.getShift(0).containsEmployee("002", 6));
    }

    @Test
    public void RemoveEmployeeStartOfShiftList() {
        scheduleController.removeEmployee(schedule, 0, 0, "004");
        Assertions.assertEquals(false, schedule.getShift(0).containsEmployee("004", 0));
    }

    @Test
    public void RemoveEmployeeMiddleOfShiftList() {
        scheduleController.addEmployee(schedule, 0, 0, "005");
        scheduleController.addEmployee(schedule, 0, 0, "006");
        scheduleController.removeEmployee(schedule, 0, 0, "005");
        Assertions.assertEquals(false, schedule.getShift(0).containsEmployee("005", 0));
    }

    @Test
    public void RemoveEmployeeEndOfShiftList() {
        scheduleController.addEmployee(schedule, 0, 0, "005");
        scheduleController.addEmployee(schedule, 0, 0, "006");
        scheduleController.removeEmployee(schedule, 0, 0, "006");
        Assertions.assertEquals(false, schedule.getShift(0).containsEmployee("006", 0));
    }

    @Test
    public void CreateEmployee() {
        scheduleController.createEmployee("001", "name");
        Assertions.assertEquals(true, scheduleController.getAllEmployeesID().contains("001"));
    }

    @Test
    public void DeleteEmployee() {
        scheduleController.createEmployee("006", "name");
        scheduleController.deleteEmployee("006");
        Assertions.assertEquals(false, scheduleController.getAllEmployeesID().contains("006"));
    }
}