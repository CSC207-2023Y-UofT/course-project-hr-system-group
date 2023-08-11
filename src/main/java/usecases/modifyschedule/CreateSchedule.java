package usecases.modifyschedule;

import entities.Schedule;

/**
 * CreateSchedule.java
 * Interface for use case CreateSchedule.
 */
public interface CreateSchedule {

    /**
     * createSchedule
     * Creates a new Schedule according to the given String[][] data.
     * @param data, String[][] representation of the Schedule data.
     */
    Schedule createSchedule(String[][] data);
}
