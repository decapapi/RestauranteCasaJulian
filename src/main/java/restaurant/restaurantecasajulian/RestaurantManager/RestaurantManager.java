package restaurant.restaurantecasajulian.RestaurantManager;

import restaurant.restaurantecasajulian.data.*;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.model.users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton class that manages the restaurant data.
 * It stores the users, tables, ratings and reservations.
 * It also stores the current user and the restaurant status.
 */

public class RestaurantManager {
    private static RestaurantManager rmInstance;

    private final Map<String, User> users;
    private final List<Table> tables;
    private final Map<RatingKey, RatingData> ratings;
    private User currentUser;
    private boolean open;

    /**
     * Constructor for the RestaurantManager class.
     */
    public RestaurantManager(){
        this.users = new HashMap<>();
        this.tables = new ArrayList<>();
        this.ratings = new HashMap<>();
    }

    /**
     * Method to get the instance of the RestaurantManager class.
     * @return the instance of the RestaurantManager class.
     */
    public static RestaurantManager getInstance() {
        if (rmInstance == null)
            rmInstance = new RestaurantManager();
        return rmInstance;
    }

    /**
     * Method to login
     * @param username
     * @param password
     * @return true if the user is logged in, false otherwise.
     */
    public boolean login(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    /**
     * Method to logout
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Method to register a new user
     * @param user
     * @return true if the user is registered, false otherwise.
     */
    public boolean register(User user) {
        return users.put(user.getUsername(), user) == null;
    }

    /**
     * Method to add a new user
     * @param user
     * @return true if the user is added, false otherwise.
     */
    public boolean addUser(User user) {
        return users.put(user.getUsername(), user) == null;
    }

    /**
     * Method to remove a user
     * @param username
     * @return true if the user is removed, false otherwise.
     */
    public boolean removeUser(String username) {
        return users.remove(username) != null;
    }

    /**
     * Method to get the current user
     * @return the current user.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Method to get the current user type
     * @return the current user type.
     */
    public UserType getCurrentUserType() {
        return currentUser.getUserType();
    }

    /**
     * Method to get the current user username
     * @return the current user username.
     */
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Method to get the user by username
     * @param username the username of the user
     * @return the user with the given username.
     */
    public User getUserByUsername(String username) {
        return users.get(username);
    }

    /**
     * Method to get the employees
     * @return the list of employees.
     */
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Employee) {
                employees.add((Employee) user);
            }
        }
        return employees;
    }

    /**
     * Method to add a table
     * @param table the table to be added.
     */
    public void addTable(Table table) {
        this.tables.add(table);
    }

    /**
     * Method to remove a table
     * @param id the id of the table to be removed.
     */
    public void removeTable(int id) {
        Table table = getTableById(id);
        if (table != null) {
            tables.remove(table);
        }
    }

    /**
     * Method to edit a table
     * @param id the id of the table to be edited.
     * @param seats the number of seats of the table.
     * @param bookable the bookable status of the table.
     * @return true if the table is edited, false otherwise.
     */
    public boolean editTable(int id, int seats, boolean bookable) {
        Table table = getTableById(id);
        if (table != null) {
            Map<ReservationData, Boolean> reservations = table.getReservationsMap();
            tables.remove(table);
            tables.add(new Table(id, seats, bookable, reservations));
        }

        return table != null;
    }

    /**
     * Method to get the tables
     * @return the list of tables.
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * Method to get the table by id
     * @param id the id of the table
     * @return the table with the given id.
     */
    public Table getTableById(int id) {
        for (Table table : tables) {
            if (table.getId() == id) {
                return table;
            }
        }
        return null;
    }

    /**
     * Method to get the tables with a given number of seats
     * @param seats the number of seats
     * @return the list of tables with the given number of seats.
     */
    public List<Table> getTablesWithSeats(int seats) {
        List<Table> tablesWithSeats = new ArrayList<>();
        for (Table t : tables) {
            if (t.getSeats() >= seats) {
                tablesWithSeats.add(t);
            }
        }
        return tablesWithSeats;
    }

    /**
     * Method to get the reservations
     * @return the list of reservations.
     */
    public List<ReservationData> getReservations() {
        List<ReservationData> reservations = new ArrayList<>();
        for (Table table : tables) {
            reservations.addAll(table.getReservations());
        }
        return reservations;
    }

    /**
     * Method to get the reservations of a user
     * @param username the username of the user
     * @return the list of reservations of the user.
     */
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

    /**
     * Method to confirm a reservation
     * @param reservationId the id of the reservation to be confirmed.
     */
    public void confirmReservation(int reservationId) {
        for (Table table : tables) {
            for (ReservationData reservation : table.getReservations()) {
                if (reservation.id() == reservationId) {
                    table.confirmReservation(reservationId);
                }
            }
        }
    }

    /**
     * Method to check if a reservation is confirmed
     * @param reservation the reservation to be checked.
     * @return true if the reservation is confirmed, false otherwise.
     */
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

    /**
     * Method to get the unattended reservations
     * @return the list of unattended reservations.
     */
    public List<ReservationData> getUnattendedReservations() {
        List<ReservationData> reservations = new ArrayList<>();
        for (Table table : tables) {
            reservations.addAll(table.getUnattendedReservations());
        }
        return reservations;
    }

    /**
     * Method to add a rating
     * @param rating the rating to be added.
     */
    public void addRating(RatingData rating) {
        this.ratings.put(rating.getKey(), rating);
    }

    /**
     * Method to get the rating
     * @param tableId the id of the table
     * @param userId the id of the user
     * @param date the date of the rating
     * @return the rating with the given table id, user id and date.
     */
    public RatingData getRating(int tableId, String userId, TimeSlot date) {
        return ratings.get(new RatingKey(tableId, userId, date));
    }

    /**
     * Method to update a rating
     * @param rating the rating to be updated.
     */
    public void updateRating(RatingData rating) {
        ratings.put(new RatingKey(rating.tableId(), rating.userId(), rating.date()), rating);
    }

    /**
     * Method to get the ratings
     * @return the list of ratings.
     */
    public List<RatingData> getRatings() {
        return new ArrayList<>(ratings.values());
    }

    /**
     * Method to add a reservation
     * @param reservation the reservation to be added.
     */
    public void addReservation(ReservationData reservation) {
        Table table = getTableById(reservation.getTableId());
        if (table != null) {
            table.addReservation(reservation);
        }
    }

    /**
     * Method to make a reservation for a user
     * @param username the username of the user
     * @param seats the number of seats
     * @param reservationSlot the time slot of the reservation
     * @param comments the comments of the reservation
     * @param preOrders the pre-orders of the reservation
     * @return true if the reservation is made, false otherwise.
     */
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

    /**
     * Method to cancel a reservation
     * @param reservationId the id of the reservation to be cancelled.
     */
    public void cancelReservation(int reservationId) {
        for (Table table : tables) {
            table.removeReservation(reservationId);
        }
    }

    /**
     * Method to check if the restaurant is open
     * @return true if the restaurant is open, false otherwise.
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Method to set the restaurant status
     * @param open the status of the restaurant.
     */
    public void setOpen(boolean open) {
        this.open = open;
    }
}
