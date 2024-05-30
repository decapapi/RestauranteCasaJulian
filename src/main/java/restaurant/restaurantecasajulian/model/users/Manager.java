package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

/**
 * Represents a manager user in the system.
 */
public class Manager extends Employee {
    /**
     * Creates a new manager user with the given username, password and email.
     * @param username
     * @param password
     * @param email
     */
    public Manager(String username, String password, String email) {
        super(username, password, email, UserType.MANAGER);
    }
}
