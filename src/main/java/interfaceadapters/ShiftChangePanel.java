package interfaceadapters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ShiftChangePanel extends JPanel implements ActionListener {

    JComboBox<String> employeeListBox;
    int row;
    int column;
    JButton confirmButton;
    String[] employees;
    boolean add;
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
        if (add == true) {
            employees = new String[]{"name1", "name2"};
            confirmButton = new JButton("Add Employee");
        } else {
            employees = data[row][column].split(", ");
            confirmButton = new JButton("Remove Employee");
        }
        employeeListBox = new JComboBox<String>(employees);

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
            System.out.println(employeeListBox.getSelectedItem());

            String employee = (String) employeeListBox.getSelectedItem();
            String command = confirmButton.getText();
            int dayIndex = this.column - 1;
            int shiftIndex = this.row;
            String shift = this.data[shiftIndex][this.column];

            ScheduleController.modifyShift(command, dayIndex, shiftIndex, employee);

            this.data[shiftIndex][this.column] = updateData(employee, shift);

            SchedulePanel.deleteNewFrame();
        }
    }

    public String updateData(String employee, String shift) {
        String deletionTarget = employee;
        if (shift.contains(employee + ", ")) {
            deletionTarget = employee + ", ";
        } else if (shift.contains(", " + employee)) {
            deletionTarget = ", " + employee;
        }

        shift = shift.replace(deletionTarget, "");
        return shift;
    }

}