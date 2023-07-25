package interfaceadapters;

import javax.swing.*;
import java.awt.*;

public class ShiftChangeScreen extends JPanel {
    ShiftChangePanel addEmployeePanel;
    ShiftChangePanel removeEmployeePanel;

    public ShiftChangeScreen() {
        JLabel title = new JLabel("MODIFY SHIFT");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTabbedPane tabbedPane = new JTabbedPane();

        addEmployeePanel = new ShiftChangePanel(true);
        removeEmployeePanel = new ShiftChangePanel(false);

        tabbedPane.addTab("Add", addEmployeePanel);
        tabbedPane.addTab("Remove", removeEmployeePanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(tabbedPane);
        System.out.println("ShiftChangeScreen running.");
    }

}
