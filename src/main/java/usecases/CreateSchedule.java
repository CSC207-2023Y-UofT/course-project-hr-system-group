package usecases;

import entities.Employee;
import entities.Schedule;
import entities.Shift;

import java.util.ArrayList;

public interface CreateSchedule {

    default Schedule createSchedule(String[][] data) {
        Schedule schedule = new Schedule();
        for (String[] lst : data) {
            ArrayList<String> employees = new ArrayList();

            for (int i = 1; i < lst.length; i++) {
                employees.add(lst[i]);
            }

            Shift shift = new Shift(lst[0], employees);
            schedule.addShift(shift);
        }
        return schedule;
    }
}
