package drivers;

import javax.swing.*;
import java.awt.*;

public class ShiftChangeScreen extends JPanel {
    ShiftChangePanel addEmployeePanel;
    ShiftChangePanel removeEmployeePanel;

    public ShiftChangeScreen(int row, int column, String[][] data) {
        JLabel title = new JLabel("MODIFY SHIFT");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTabbedPane tabbedPane = new JTabbedPane();

        addEmployeePanel = new ShiftChangePanel(true, row, column, data);
        removeEmployeePanel = new ShiftChangePanel(false, row, column, data);

        tabbedPane.addTab("Add", addEmployeePanel);
        tabbedPane.addTab("Remove", removeEmployeePanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(tabbedPane);
    }

}
