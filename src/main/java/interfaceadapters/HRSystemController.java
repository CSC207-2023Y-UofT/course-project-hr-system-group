package interfaceadapters;

import entities.Employee;
import entities.HRSystem;
import usecases.modifyhrsystem.CreateHRSystem;

import java.util.ArrayList;

public class HRSystemController implements CreateHRSystem {

    /**
     * createHRSystem
     * Creates and returns a new HRSystem.
     * @return HRSystem.
     */
    public HRSystem createHRSystem() {
        HRSystem hrSystem = new HRSystem(new ArrayList<Employee>());
        return hrSystem;
    }
}
