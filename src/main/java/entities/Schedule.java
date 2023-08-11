package entities;

import java.util.ArrayList;

/**
 * Schedule.java
 * Class for a Schedule.
 */
public class Schedule {

    private ArrayList<Shift> shifts = new ArrayList();

    public Schedule() {
    }

    /**
     * shiftsContainsEmployee
     * Returns whether this Schedule has a Shift that contains employee with String id.
     * @param id, String for id of employee to be compared.
     */
    public boolean shiftsContainsEmployee(String id) {
        for (Shift shift : shifts) {
            if (shift.containsEmployee(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getShift
     * Getter for a shift in ArrayList<Shift> shifts with a given index.
     * @param index, int for index of shift to be retrieved.
     * @return Shift, the shift at the given index.
     */
    public Shift getShift(int index) {
        return this.shifts.get(index);
    }

    /**
     * addShift
     * Adds a shift to this Schedule's ArrayList<Shift> shifts.
     * @param shift, Shift to be added to ArrayList<Shift> shifts.
     */
    public void addShift(Shift shift) {
        this.shifts.add(shift);
    }

}
