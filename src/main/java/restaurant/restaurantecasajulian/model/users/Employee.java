package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

public class Employee extends User {
    
    public Employee(String username, String password, String email) {
        this(username, password, email, UserType.EMPLOYEE);
    }

    public Employee(String username, String password, String email, UserType userType) {
        super(username, password, email, userType);
    }
}
