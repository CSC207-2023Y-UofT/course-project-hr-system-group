package interfaceadapters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ShiftChangePanel extends JPanel implements ActionListener {

    JComboBox<String> employeeListBox;
    JButton confirmButton;
    String[] employees;
    boolean add;

    public ShiftChangePanel(boolean add) {

        this.add = add;

        //Title
        JLabel title = new JLabel("Select an employee:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Combo Box and Button
        if (add == true) {
            employees = new String[]{"name1", "name2", "name3"};
            confirmButton = new JButton("Add Employee");
        } else {
            employees = new String[]{"name1", "name2"};
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
        }
    }

}