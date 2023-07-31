package interfaceadapters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SchedulePanel extends JPanel implements ActionListener {

    final String[] COLUMNS = {"SHIFT", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String[][] data;
    static JTable table;
    static JFrame newFrame = new JFrame();

    public SchedulePanel(String[][] data) {

        //Title
        JLabel title = new JLabel("SCHEDULE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Buttons
        JButton newShift = new JButton("New Shift");
        JButton removeShift = new JButton("Remove Shift");

        JPanel buttons = new JPanel();
        buttons.add(newShift);
        buttons.add(removeShift);

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
                    System.out.println(row + " and " + column);

                    if (row >= 0 && column > 0) {
                        ShiftChangeScreen shiftChangeScreen = new ShiftChangeScreen(row, column, data);
                        shiftChangeScreen.setOpaque(true);
//                        newFrame = new JFrame();
                        newFrame.setContentPane(shiftChangeScreen);

                        shiftChangeScreen.setPreferredSize(new Dimension(500, 200));
                        newFrame.pack();
                        newFrame.setVisible(true);
                    }
                }
            }
        });
        newShift.addActionListener(this);
        removeShift.addActionListener(this);
    }

    public static void deleteNewFrame(){
        newFrame.dispose();
        table.updateUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }


}
