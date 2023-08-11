package usecases.modifyhrsystem;

public interface CreateEmployee {

    /**
     * createEmployee
     * Creates an employee in the HRSystem given an ID  and name.
     * @param id, String for new employee's ID.
     * @param name, String for new employee's name.
     */
    boolean createEmployee(String id, String name);
}
