package usecases;

import entities.Schedule;

/**
 * AddEmployee.java
 * Interface for use case AddEmployee.
 */
public interface AddEmployee {

    /**
     * addEmployee
     * Retrieves a shift and adds an employee accordingly to the given parameters.
     * @param schedule, overall schedule.
     * @param dayIndex, int for index employee is to be added to.
     * @param shiftIndex, int for index of shift to be modified.
     * @param employee, String for employee to be added.
     */
    void addEmployee(Schedule schedule, int dayIndex, int shiftIndex, String employee);

}
