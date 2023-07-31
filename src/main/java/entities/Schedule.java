package entities;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Shift> shifts = new ArrayList();

    public Schedule() {
    }

    public Shift getShift(int index) {
        return this.shifts.get(index);
    }

    public void addShift(Shift shift) {
        this.shifts.add(shift);
    }

    public void removeShift(Shift shift) {
        this.shifts.remove(shift);
    }

}
