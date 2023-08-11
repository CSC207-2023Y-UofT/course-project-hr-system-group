package drivers;

import drivers.scheduleui.ScheduleUI;
import entities.HRSystem;
import interfaceadapters.HRSystemController;
import interfaceadapters.ScheduleController;

import java.util.List;

/**
 * Main.java
 * Temporary Main class for Schedule feature.
 */
public class Main {

    public static void main(String[] args) {
        HRSystemController hrSystemController = new HRSystemController();
        HRSystem hrSystem = hrSystemController.createHRSystem();

        ScheduleUI scheduleUI = new ScheduleUI();
        List<String[]> data = ScheduleController.callFileReader();
        ScheduleController scheduleController = new ScheduleController(hrSystem, scheduleUI, data);

        // Sample HRSystem
        scheduleController.createEmployee("001", "name");
        scheduleController.createEmployee("002", "name");
        scheduleController.createEmployee("003", "name");
        scheduleController.createEmployee("004", "name");
        scheduleController.createEmployee("005", "name");

        scheduleController.openUI();
    }

}