package restaurant.restaurantecasajulian.utils;

import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.users.*;

/**
 * Factory class for creating User objects.
 */
public class UserFactory {
    /**
     * Creates a new User object based on the given user type.
     * @param userType The type of user to create.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email of the user.
     * @return A new User object.
     */
    public static User createUser(UserType userType, String username, String password, String email) {
        switch (userType) {
            case CUSTOMER:
                return new Customer(username, password, email);
            case EMPLOYEE:
                return new Employee(username, password, email);
            case MANAGER:
                return new Manager(username, password, email);
            case ADMINISTRATOR:
                return new Administrator(username, password, email);
            default:
                throw new IllegalArgumentException("Invalid user type: " + userType);
        }
    }
}