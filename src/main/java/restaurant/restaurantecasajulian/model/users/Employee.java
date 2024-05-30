package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

/**
 * Represents an employee of the restaurant.
 */

public class Employee extends User {
    /**
     * Creates a new employee with the given username, password and email.
     * @param username the username of the employee
     * @param password the password of the employee
     * @param email the email of the employee
     */
    public Employee(String username, String password, String email) {
        this(username, password, email, UserType.EMPLOYEE);
    }
    /**
     * Creates a new employee with the given username, password, email and user type.
     * @param username the username of the employee
     * @param password the password of the employee
     * @param email the email of the employee
     * @param userType the user type of the employee
     */
    public Employee(String username, String password, String email, UserType userType) {
        super(username, password, email, userType);
    }
}
