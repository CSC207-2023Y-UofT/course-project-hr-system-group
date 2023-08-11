package drivers.scheduleui;

import interfaceadapters.ScheduleController;

import javax.swing.*;
import java.awt.*;

/**
 * ShiftChangeScreen.java
 * Class for the JPanel ShiftChangeScreen, contains a TabbedPane for each panel to add or remove an employee.
 */
public class ShiftChangeScreen extends JPanel {
    private ShiftChangePanel addEmployeePanel;
    private ShiftChangePanel removeEmployeePanel;

    public ShiftChangeScreen(int row, int column, String[][] data, ScheduleController scheduleController) {
        JLabel title = new JLabel("MODIFY SHIFT");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTabbedPane tabbedPane = new JTabbedPane();

        addEmployeePanel = new ShiftChangePanel(true, row, column, data, scheduleController);
        removeEmployeePanel = new ShiftChangePanel(false, row, column, data, scheduleController);

        tabbedPane.addTab("Add", addEmployeePanel);
        tabbedPane.addTab("Remove", removeEmployeePanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(tabbedPane);
    }

}
