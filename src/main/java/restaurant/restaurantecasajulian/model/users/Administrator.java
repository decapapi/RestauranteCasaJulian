package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

/**
 * Class to represent an Administrator user
 */
public class Administrator extends User {
    /**
     * Creates a new Administrator with the given username, password and email.
     * @param username the username of the Administrator
     * @param password the password of the Administrator
     * @param email the email of the Administrator
     */
    public Administrator(String username, String password, String email) {
        super(username, password, email, UserType.ADMINISTRATOR);
    }
}
