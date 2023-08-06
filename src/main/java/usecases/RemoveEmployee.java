package usecases;

import entities.Schedule;
import entities.Shift;

public interface RemoveEmployee {
    static void removeEmployee(Schedule schedule, int dayIndex, int shiftIndex, String employee) {

        Shift shift = schedule.getShift(shiftIndex);
        shift.removeEmployee(dayIndex, employee);
    }

}
