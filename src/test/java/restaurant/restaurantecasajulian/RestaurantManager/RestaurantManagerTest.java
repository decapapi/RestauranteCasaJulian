package restaurant.restaurantecasajulian.RestaurantManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.model.users.Customer;
import restaurant.restaurantecasajulian.model.users.Manager;
import restaurant.restaurantecasajulian.model.users.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
class RestaurantManagerTest {
    private RestaurantManager restaurantManager;
    private User testUser;
    private Table testTable;
    private ReservationData testReservation;

    @BeforeEach
    void setUp() {
        restaurantManager = RestaurantManager.getInstance();
        testUser = new Manager("testUser", "testPassword", "testEmail");
        testTable = new Table(1, 4, true, new HashMap<>());
        testReservation = new ReservationData("testUser", 1, new TimeSlot(LocalDate.now(), LocalTime.now(), 50), "testComments", new ArrayList<>());
    }

    @Test
    void login() {
        restaurantManager.register(testUser);
        assertTrue(restaurantManager.login("testUser", "testPassword"));
        assertFalse(restaurantManager.login("wrongUser", "testPassword"));
    }

    @Test
    void logout() {
        restaurantManager.register(testUser);
        restaurantManager.login("testUser", "testPassword");
        restaurantManager.logout();
        assertNull(restaurantManager.getCurrentUser());
    }

    @Test
    void register() {
        assertFalse(restaurantManager.register(testUser));
        assertTrue(restaurantManager.register(new Customer("testUser2", "testPassword", "testEmail")));
    }

    @Test
    void addUser() {
        assertTrue(restaurantManager.addUser(testUser));
        assertFalse(restaurantManager.addUser(testUser));
    }

    @Test
    void removeUser() {
        restaurantManager.register(testUser);
        assertTrue(restaurantManager.removeUser("testUser"));
        assertFalse(restaurantManager.removeUser("testUser"));
    }

    @Test
    void addTable() {
        restaurantManager.addTable(testTable);
        assertTrue(restaurantManager.getTables().contains(testTable));
    }

    @Test
    void editTable() {
        restaurantManager.addTable(testTable);
        assertTrue(restaurantManager.editTable(1, 6, false));
        assertFalse(restaurantManager.editTable(2, 6, false));
    }

    @Test
    void makeReservation() {
        restaurantManager.addTable(testTable);
        assertTrue(restaurantManager.makeReservation("testUser", 4, new TimeSlot(LocalDate.now(), LocalTime.now(), 50), "testComments", new ArrayList<>()));
        assertFalse(restaurantManager.makeReservation("testUser", 5, new TimeSlot(LocalDate.now(), LocalTime.now(), 50), "testComments", new ArrayList<>()));
    }

    @Test
    void isOpen() {
        restaurantManager.setOpen(true);
        assertTrue(restaurantManager.isOpen());
        restaurantManager.setOpen(false);
        assertFalse(restaurantManager.isOpen());
    }
}