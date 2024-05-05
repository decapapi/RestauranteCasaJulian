package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

public class Manager extends Employee {
    public Manager(String username, String password, String email) {
        super(username, password, email, UserType.MANAGER);
    }
}
