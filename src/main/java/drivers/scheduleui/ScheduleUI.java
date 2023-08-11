package drivers.scheduleui;

import interfaceadapters.ScheduleController;

import javax.swing.*;
import java.awt.*;

/**
 * ScheduleUI.java
 * Class for the ScheduleUI.
 */
public class ScheduleUI {

    /**
     * createUI
     * Creates and sets the JFrames and JPanels for the Schedule UI.
     * @param data, String[][] representation of the Schedule data.
     */
    public void createUI(String[][] data, ScheduleController scheduleController) {
        JFrame application = new JFrame("HR System");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        SchedulePanel schedulePanel = new SchedulePanel(data, scheduleController);
        schedulePanel.setOpaque(true);
        application.setContentPane(schedulePanel);

        application.pack();
        application.setVisible(true);
    }

}
