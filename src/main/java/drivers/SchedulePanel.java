package drivers;

import interfaceadapters.ScheduleController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SchedulePanel.java
 * Class for the JPanel SchedulePanel, the main UI Panel of the schedule feature.
 */
public class SchedulePanel extends JPanel implements ActionListener {

    private final String[] COLUMNS =
            {"SHIFT", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private String[][] data;
    private static JTable table;
    private static JFrame newFrame = new JFrame();

    public SchedulePanel(String[][] data, ScheduleController scheduleController) {

        //Title
        JLabel title = new JLabel("SCHEDULE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Buttons
        JButton saveButton = new JButton("Save Schedule to File");

        JPanel buttons = new JPanel();
        buttons.add(saveButton);

        //Table
        this.data = data;
        table = new JTable(data, COLUMNS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(1000, 150));
        table.setFillsViewportHeight(true);


        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add components to this panel.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
        this.add(buttons);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();

                    if (row >= 0 && column > 0) {
                        ShiftChangeScreen shiftChangeScreen = new ShiftChangeScreen(row, column, data,
                                scheduleController);
                        shiftChangeScreen.setOpaque(true);
                        newFrame.setContentPane(shiftChangeScreen);

                        shiftChangeScreen.setPreferredSize(new Dimension(500, 200));
                        newFrame.pack();
                        newFrame.setVisible(true);
                    }
                }
            }
        });
        saveButton.addActionListener(this);
    }


    /**
     * deleteNewFrame
     * Deletes newFrame and updates the SchedulePanel UI.
     */
    public static void deleteNewFrame(){
        newFrame.dispose();
        table.updateUI();
    }


    /**
     * actionPerformed
     * Calls callFileWriter() from ScheduleController with SchedulePanel's current data.
     * @param e, ActionEvent from pressing JButton saveButton.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ScheduleController.callFileWriter(data);
    }


}
