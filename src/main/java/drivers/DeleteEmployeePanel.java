package drivers;

import interfaceadapters.ScheduleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * DeleteEmployeePanel.java
 * Class for the JPanel DeleteEmployeePanel, prompts the user for the ID of an Employee to delete from the HRSystem.
 */
public class DeleteEmployeePanel extends JPanel implements ActionListener {

    private ScheduleController scheduleController;
    private JButton confirmButton;
    private JComboBox<String> employeeListBox;

    public DeleteEmployeePanel(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;

        //Title
        JLabel title = new JLabel("DELETE EMPLOYEE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Combo Box and Button
        ArrayList<String> allEmployeesID = this.scheduleController.getAllEmployeesID();
        String[] arrayAllEmployeesID = new String[allEmployeesID.size()];
        for (int i = 0; i < allEmployeesID.size(); i++) {
            arrayAllEmployeesID[i] = allEmployeesID.get(i);
        }

        confirmButton = new JButton("Delete Employee");
        employeeListBox = new JComboBox<>(arrayAllEmployeesID);

        //Button
        confirmButton = new JButton("Delete");
        JPanel buttons = new JPanel();
        buttons.add(confirmButton);
        confirmButton.addActionListener(this);

        //Add components to the panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(employeeListBox);
        this.add(buttons);
        this.setVisible(true);
    }

    /**
     * actionPerformed
     * Takes the currently selected String employee from employeeListBox and attempts to delete said employee with
     * deleteEmployee from ScheduleController. If deleteEmployee fails and returns false, create a JOptionPane that
     * informs the employee is scheduled in one or more shifts. Closes the frame once an employee is successfully
     * deleted.
     * @param e, ActionEvent from pressing JButton confirmButton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String employee = (String) employeeListBox.getSelectedItem();
        if (!scheduleController.deleteEmployee(employee)) {
            JOptionPane.showMessageDialog(this,
                    "Employee is currently scheduled. Please remove them from the schedule before deleting.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Employee deleted.");
        SchedulePanel.deleteNewFrame();
    }

}