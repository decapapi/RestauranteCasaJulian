package restaurant.restaurantecasajulian.RestaurantManager;

import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantManager {
    private static RestaurantManager rmInstance;

    private final Map<String, User> users;
    private final List<Table> tables;
    private User currentUser;

    public RestaurantManager(){
        this.users = new HashMap<>();
        this.tables = new ArrayList<>();
        addTestData();
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

    public void logout() {
        currentUser = null;
    }

    public boolean register(User user) {
        return users.put(user.getUsername(), user) == null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserType getCurrentUserType() {
        return currentUser.getUserType();
    }

    public List<Table> getTables() {
        return tables;
    }

    public Table getTableById(int id) {
        for (Table table : tables) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }

    public List<Table> getTablesWithSeats(int seats) {
        List<Table> tablesWithSeats = new ArrayList<>();
        for (Table t : tables) {
            if (t.getSeats() >= seats) {
                tablesWithSeats.add(t);
            }
        }
        return tablesWithSeats;
    }

    private void addTestData() {
        this.tables.add(new Table(2));
        this.tables.add(new Table(4));
        this.tables.add(new Table(6));

        this.users.put("admin", new Administrator("admin", "admin", "test"));
        this.users.put("manager", new Manager("manager", "manager", "test"));
        Employee emp = new Employee("employee", "employee", "test");
        this.users.put("employee", emp);
        emp.block(999999999);
        this.users.put("user", new Customer("user", "user", "test"));
    }
}
