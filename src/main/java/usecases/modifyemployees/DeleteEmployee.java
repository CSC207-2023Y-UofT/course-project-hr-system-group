package usecases.modifyemployees;

public interface DeleteEmployee {

    /**
     * deleteEmployee
     * Deletes an employee in the HRSystem .
     * @param id, String of the employee to be removed's ID.
     */
    boolean deleteEmployee(String id);
}
