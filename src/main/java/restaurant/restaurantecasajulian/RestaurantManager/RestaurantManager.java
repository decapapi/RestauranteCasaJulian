package restaurant.restaurantecasajulian.RestaurantManager;

import restaurant.restaurantecasajulian.data.*;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.model.users.*;
import restaurant.restaurantecasajulian.utils.CSVDumper;
import restaurant.restaurantecasajulian.utils.CSVParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantManager {
    private static RestaurantManager rmInstance;

    private final Map<String, User> users;
    private final List<Table> tables;
    private final Map<RatingKey, RatingData> ratings;
    private User currentUser;
    private boolean open;

    public RestaurantManager(){
        this.users = new HashMap<>();
        this.tables = new ArrayList<>();
        this.ratings = new HashMap<>();
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

    public boolean addUser(User user) {
        return users.put(user.getUsername(), user) == null;
    }

    public boolean removeUser(String username) {
        return users.remove(username) != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UserType getCurrentUserType() {
        return currentUser.getUserType();
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUserByUsername(String username) {
        return users.get(username);
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Employee) {
                employees.add((Employee) user);
            }
        }
        return employees;
    }

    public void addTable(Table table) {
        this.tables.add(table);
    }

    public void removeTable(int id) {
        Table table = getTableById(id);
        if (table != null) {
            tables.remove(table);
        }
    }

    public boolean editTable(int id, int seats, boolean bookable) {
        Table table = getTableById(id);
        if (table != null) {
            Map<ReservationData, Boolean> reservations = table.getReservationsMap();
            tables.remove(table);
            tables.add(new Table(id, seats, bookable, reservations));
        }

        return table != null;
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

    public List<ReservationData> getReservations() {
        List<ReservationData> reservations = new ArrayList<>();
        for (Table table : tables) {
            reservations.addAll(table.getReservations());
        }
        return reservations;
    }

    public List<ReservationData> getAttendedReservations(String username) {
        List<ReservationData> reservations = new ArrayList<>();
        for (Table table : tables) {
            for (ReservationData reservation : table.getAttendedReservations()) {
                if (reservation.username().equals(username)) {
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    public void confirmReservation(int reservationId) {
        for (Table table : tables) {
            for (ReservationData reservation : table.getReservations()) {
                if (reservation.id() == reservationId) {
                    table.confirmReservation(reservationId);
                }
            }
        }
    }

    public boolean isReservationConfirmed(ReservationData reservation) {
        for (Table table : tables) {
            for (ReservationData r : table.getAttendedReservations()) {
                if (r.id() == reservation.id()) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<ReservationData> getUnattendedReservations() {
        List<ReservationData> reservations = new ArrayList<>();
        for (Table table : tables) {
            reservations.addAll(table.getUnattendedReservations());
        }
        return reservations;
    }

    public void addRating(RatingData rating) {
        this.ratings.put(rating.getKey(), rating);
    }

    public RatingData getRating(int tableId, String userId, TimeSlot date) {
        return ratings.get(new RatingKey(tableId, userId, date));
    }

    public void updateRating(RatingData rating) {
        ratings.put(new RatingKey(rating.tableId(), rating.userId(), rating.date()), rating);
    }

    public List<RatingData> getRatings() {
        return new ArrayList<>(ratings.values());
    }

    public void addReservation(ReservationData reservation) {
        Table table = getTableById(reservation.getTableId());
        if (table != null) {
            table.addReservation(reservation);
        }
    }

    public boolean makeReservation(String username, int seats, TimeSlot reservationSlot, String comments, List<DishData> preOrders) {
        boolean reservationMade = false;
        List<Table> availableTables = getTablesWithSeats(seats);
        for (int i = 0; i < availableTables.size() && !reservationMade; i++) {
            if (availableTables.get(i).isAvailable(reservationSlot)) {
                if (availableTables.get(i).addReservation(new ReservationData(username,
                        availableTables.get(i).getId(), reservationSlot, comments, preOrders))) {
                    reservationMade = true;
                }
            }
        }
        return reservationMade;
    }

    public void cancelReservation(int reservationId) {
        for (Table table : tables) {
            table.removeReservation(reservationId);
        }
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
