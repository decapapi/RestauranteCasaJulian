package restaurant.restaurantecasajulian.utils;

import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.users.*;

public class UserFactory {
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