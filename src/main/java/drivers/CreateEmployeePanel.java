package drivers;

import interfaceadapters.ScheduleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ShiftChangeScreen.java
 * Class for the JPanel ShiftChangeScreen, contains a TabbedPane for each panel to add or remove an employee.
 */
public class CreateEmployeePanel extends JPanel implements ActionListener {

    private ScheduleController scheduleController;
    private JTextField userInputFieldID, userInputFieldName;
    private JButton confirmButton;

    public CreateEmployeePanel(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;

        //Title
        JLabel title = new JLabel("CREATE NEW EMPLOYEE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Labels
        JLabel labelID = new JLabel("Employee ID:");
        JLabel labelName = new JLabel("Employee Name:");

        //Text Fields
        userInputFieldID = new JTextField(20);
        userInputFieldName = new JTextField(20);

        //Button
        confirmButton = new JButton("Add");
        JPanel buttons = new JPanel();
        buttons.add(confirmButton);
        confirmButton.addActionListener(this);

        //Add components to the panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(labelID);
        this.add(userInputFieldID);
        this.add(labelName);
        this.add(userInputFieldName);
        this.add(buttons);
        this.setVisible(true);
    }

    /**
     * actionPerformed
     * Takes the current text from userInputFieldID and userInputFieldName. If the inputs are not alphanumerical or an
     * ID that already exists is inputted, the corresponding JOptionPane is created with the appropriate warning
     * dialogue. If inputs are valid, create the new employee and close the current frame.
     * @param e, ActionEvent from pressing JButton confirmButton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = userInputFieldID.getText();
        String name = userInputFieldName.getText();
        if (!(validInput(id) && validInput(name))) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input. ID and name must contain only alphanumerical characters.");
            return;
        }
        if (!scheduleController.createEmployee(id, name)) {
            JOptionPane.showMessageDialog(this,
                    "Invalid input. Employee with ID already exists.");
        }
        JOptionPane.showMessageDialog(this, "New employee created.");
        SchedulePanel.deleteNewFrame();
    }

    private boolean validInput(String input) {
        if (input.matches("[A-Za-z0-9]+")) {
            return true;
        }
        return false;
    }

}
