package interfaceadapters;

import entities.Schedule;
import usecases.CreateSchedule;
import usecases.RemoveEmployee;
import usecases.ScheduleDataParser;

import java.util.List;

public class ScheduleController implements CreateSchedule, RemoveEmployee {

    static Schedule schedule;
    ScheduleUI scheduleUI;
//    List<String[]> data;
    String[][] parsedData;

    public ScheduleController(ScheduleUI scheduleUI, List<String[]> data) {
        ScheduleDataParser parser = new ScheduleDataParser(data);
        this.parsedData = parser.getParsedDataArray();

        schedule = this.createSchedule(this.parsedData);

        this.scheduleUI = scheduleUI;
//        this.data = data;
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
                return;
            default:
                return;
        }
    }

}
