package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

public class Administrator extends User {
    public Administrator(String username, String password, String email) {
        super(username, password, email, UserType.ADMINISTRATOR);
    }
}
