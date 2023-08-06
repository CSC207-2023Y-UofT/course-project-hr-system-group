package drivers;

import interfaceadapters.ScheduleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class ShiftChangePanel extends JPanel implements ActionListener {

    JComboBox<String> employeeListBox;
    int row, column;
    JButton confirmButton;
    boolean add;
    String[] selectionEmployees;
    String[][] data;

    public ShiftChangePanel(boolean add, int row, int column, String[][] data) {

        this.data = data;
        this.row = row;
        this.column = column;
        this.add = add;

        //Title
        JLabel title = new JLabel("Select an employee:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Combo Box and Button
        if (add) {
            ArrayList<String> allEmployeesID = ScheduleController.getAllEmployeesID();
            String[] overlapEmployees = data[row][column].split(", ");
            for (String employee : overlapEmployees) {
                allEmployeesID.remove(employee);
            }
            selectionEmployees = new String[allEmployeesID.size()];
            for (int i = 0; i < selectionEmployees.length; i++) {
                selectionEmployees[i] = allEmployeesID.get(i);
            }
            confirmButton = new JButton("Add Employee");
        } else {
            selectionEmployees = data[row][column].split(", ");
            confirmButton = new JButton("Remove Employee");
        }
        employeeListBox = new JComboBox<String>(selectionEmployees);

        JPanel buttons = new JPanel();
        buttons.add(confirmButton);


        //Add components to this panel.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(employeeListBox);
        this.add(buttons);
        this.setVisible(true);


        employeeListBox.addActionListener(this);
        confirmButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {

            String employee = (String) employeeListBox.getSelectedItem();
            String command = confirmButton.getText();
            int dayIndex = this.column - 1;
            int shiftIndex = this.row;
            String shift = this.data[shiftIndex][this.column];

            ScheduleController.modifyShift(command, dayIndex, shiftIndex, employee);

            this.data[shiftIndex][this.column] = updateData(command, employee, shift);

            SchedulePanel.deleteNewFrame();
        }
    }

    public String updateData(String command, String employee, String shift) {
        switch(command) {
            case "Remove Employee":
                String deletionTarget = employee;
                if (shift.contains(employee + ", ")) {
                    deletionTarget = employee + ", ";
                } else if (shift.contains(", " + employee)) {
                    deletionTarget = ", " + employee;
                }

                shift = shift.replace(deletionTarget, "");
                return shift;
            case "Add Employee":
                if (shift.equals("")) {
                    return employee;
                } else {
                    return shift + ", " + employee;
                }
            default:
                return "";
        }
    }

}