package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

public class Customer extends User{
    public Customer(String username, String password, String email) {
        super(username, password, email, UserType.CUSTOMER);
    }
}
