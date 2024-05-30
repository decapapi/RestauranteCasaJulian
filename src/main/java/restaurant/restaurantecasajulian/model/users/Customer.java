package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.types.UserType;

/**
 * Represents a customer user.
 */
public class Customer extends User {
    /**
     * Creates a new customer with the given username, password and email.
     * @param username the username of the customer
     * @param password the password of the customer
     * @param email the email of the customer
     */
    public Customer(String username, String password, String email) {
        super(username, password, email, UserType.CUSTOMER);
    }
}
