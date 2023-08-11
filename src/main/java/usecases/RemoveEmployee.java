package usecases;

import entities.Schedule;

/**
 * RemoveEmployee.java
 * Interface for use case RemoveEmployee.
 */
public interface RemoveEmployee {

    /**
     * removeEmployee
     * Retrieves a shift and removes an employee accordingly to the given parameters.
     * @param schedule, overall schedule.
     * @param dayIndex, int for index employee is to be removed from.
     * @param shiftIndex, int for index of shift to be modified.
     * @param employee, String for employee to be removed.
     */
    void removeEmployee(Schedule schedule, int dayIndex, int shiftIndex, String employee);

}
