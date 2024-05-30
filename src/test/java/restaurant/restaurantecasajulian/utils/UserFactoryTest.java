package restaurant.restaurantecasajulian.utils;

import org.junit.jupiter.api.Test;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.users.User;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void createUser() {
        User user = UserFactory.createUser(UserType.CUSTOMER, "username", "password", "email");
        assertNotNull(user);
    }
}