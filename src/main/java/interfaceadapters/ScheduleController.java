package interfaceadapters;

import usecases.ScheduleDataParser;

import java.util.List;

public class ScheduleController {

    ScheduleUI scheduleUI;
    List<String[]> data;

    public ScheduleController(ScheduleUI scheduleUI, List<String[]> data) {
        this.scheduleUI = scheduleUI;
        this.data = data;
    }

    public void run() {
        ScheduleDataParser parser = new ScheduleDataParser(data);

        this.scheduleUI.createUI(parser.getParsedDataArray());
        System.out.println("Opened Schedule UI.");
    }
}
