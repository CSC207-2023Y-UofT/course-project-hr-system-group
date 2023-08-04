package usecases;

import entities.Schedule;
import entities.Shift;

public interface AddEmployee {
    static void addEmployee(Schedule schedule, int dayIndex, int shiftIndex, String employee) {
        System.out.println("Attempting to add employee.");

        Shift shift = schedule.getShift(shiftIndex);
        shift.addEmployee(dayIndex, employee);
    }

}
