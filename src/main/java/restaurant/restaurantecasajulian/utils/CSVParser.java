package restaurant.restaurantecasajulian.utils;

import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.DishData;
import restaurant.restaurantecasajulian.data.RatingData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.model.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * CSVParser class.
 * This class is responsible for parsing the CSV files and loading the data into the system.
 */

public class CSVParser {

    /**
     * loadData method.
     * This method loads the data from the CSV files into the system.
     */
    public static void loadData() {
        loadOpen();
        loadUsers();
        loadTables();
        loadReservations();
        loadRatings();
    }

    /**
     * loadOpen method.
     * This method loads the open status of the restaurant from the config file.
     */
    public static void loadOpen()
    {
        List<String> lines = FileManager.readAllLines(CSVDumper.configFile);
        RestaurantManager.getInstance().setOpen(!lines.isEmpty() && lines.get(0).equals("true"));
    }

    /**
     * loadUsers method.
     * This method loads the users from the users CSV file.
     */
    public static void loadUsers() {
        List<String> usersData = FileManager.readAllLines(CSVDumper.usersFile);

        for (String userData : usersData) {
            String[] userFields = userData.split(CSVDumper.CSV_SEPARATOR);
            if (userFields.length < 4) {
                System.err.println("Skipping invalid user data: " + userData);
                continue;
            }
            UserType userType;
            try {
                userType = UserType.valueOf(userFields[0]);
            } catch (IllegalArgumentException e) {
                System.err.println("Skipping user with invalid type: " + userFields[0]);
                continue;
            }
            String username = userFields[1];
            String password = userFields[2];
            String email = userFields[3];
            User user = UserFactory.createUser(userType, username, password, email);
            RestaurantManager.getInstance().addUser(user);
        }
    }

    /**
     * loadTables method.
     * This method loads the tables from the tables CSV file.
     */
    public static void loadTables() {
        List<String> tablesData = FileManager.readAllLines(CSVDumper.tablesFile);
        for (String tableData : tablesData) {
            String[] tableFields = tableData.split(CSVDumper.CSV_SEPARATOR);
            if (tableFields.length < 2) {
                System.err.println("Skipping invalid table data: " + tableData);
                continue;
            }
            int id = Integer.parseInt(tableFields[0]);
            int seats = Integer.parseInt(tableFields[1]);
            boolean bookable = Boolean.parseBoolean(tableFields[2]);
            Table table = new Table(id, seats, bookable);
            RestaurantManager.getInstance().addTable(table);
        }
    }

    /**
     * loadReservations method.
     * This method loads the reservations from the reservations CSV file.
     */
    public static void loadReservations() {
        List<String> reservationsData = FileManager.readAllLines(CSVDumper.reservationsFile);
        for (String reservationData : reservationsData) {
            String[] reservationFields = reservationData.split(CSVDumper.CSV_SEPARATOR);
            if (reservationFields.length < 5) {
                System.err.println("Skipping invalid reservation data: " + reservationData);
                continue;
            }
            String username = reservationFields[1];
            try {
                int tableId = Integer.parseInt(reservationFields[2]);
                TimeSlot timeSlot = TimeSlot.parse(reservationFields[3]);
                String comments = reservationFields[4];
                List<DishData> preOrders = parsePreOrders(reservationFields[5]);
                boolean confirmed = Boolean.parseBoolean(reservationFields[6]);
                ReservationData reservation = new ReservationData(username, tableId, timeSlot, comments, preOrders);
                RestaurantManager.getInstance().addReservation(reservation);
                if (confirmed) {
                    RestaurantManager.getInstance().confirmReservation(reservation.id());
                }
            } catch (NumberFormatException e) {
                System.err.println("Skipping invalid reservation data: " + reservationData);
            }
        }
    }

    /**
     * parsePreOrders method.
     * This method parses the pre-orders data from the CSV file.
     * @param preOrdersData The pre-orders data.
     * @return The list of pre-orders.
     */
    private static List<DishData> parsePreOrders(String preOrdersData) {
        List<DishData> preOrders = new ArrayList<>();
        String[] preOrdersFields = preOrdersData.split(CSVDumper.CSV_SEPARATOR);
        for (String preOrderField : preOrdersFields) {
            String[] preOrderData = preOrderField.split(":");
            String name = preOrderData[0];
            int rations = Integer.parseInt(preOrderData[1]);
            DishData dishData = new DishData(name, rations);
            preOrders.add(dishData);
        }
        return preOrders;
    }

    /**
     * loadRatings method.
     * This method loads the ratings from the ratings CSV file.
     */
    public static void loadRatings() {
        List<String> ratingsData = FileManager.readAllLines(CSVDumper.ratingsFile);
        for (String ratingData : ratingsData) {
            String[] ratingFields = ratingData.split(CSVDumper.CSV_SEPARATOR);
            if (ratingFields.length < 4) {
                System.err.println("Skipping invalid rating data: " + ratingData);
                continue;
            }
            try {
                int tableId = Integer.parseInt(ratingFields[0]);
                String userId = ratingFields[1];
                TimeSlot date = TimeSlot.parse(ratingFields[2]);
                String message = ratingFields[3];
                float rating = Float.parseFloat(ratingFields[4]);
                RatingData newRating = new RatingData(tableId, userId, date, message, rating);
                RestaurantManager.getInstance().addRating(newRating);
            } catch (Exception e) {
                System.err.println("Skipping invalid rating data: " + ratingData);
            }
        }
    }
}
