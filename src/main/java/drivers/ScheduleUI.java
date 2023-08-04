package drivers;

import javax.swing.*;
import java.awt.*;

public class ScheduleUI {

    public void createUI(String[][] data) {
        JFrame application = new JFrame("HR System");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        SchedulePanel schedulePanel = new SchedulePanel(data);
        schedulePanel.setOpaque(true);
        application.setContentPane(schedulePanel);

        application.pack();
        application.setVisible(true);
    }

}
