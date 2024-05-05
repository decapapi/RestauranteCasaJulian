package restaurant.restaurantecasajulian.RestaurantManager;

import restaurant.restaurantecasajulian.model.users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantManager {
    private static RestaurantManager rmInstance;

    private final Map<String, User> users;
    private User currentUser;

    public RestaurantManager(){
        this.users = new HashMap<>();
        this.users.put("admin", new Administrator("admin", "admin", "test"));
        this.users.put("manager", new Manager("manager", "manager", "test"));
        Employee emp = new Employee("employee", "employee", "test");
        this.users.put("employee", emp);
        emp.block(999999999);
        this.users.put("user", new Customer("user", "user", "test"));
    }

    public static RestaurantManager getInstance() {
        if (rmInstance == null)
            rmInstance = new RestaurantManager();
        return rmInstance;
    }

    public boolean login(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean register(User user) {
        return users.put(user.getUsername(), user) == null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
