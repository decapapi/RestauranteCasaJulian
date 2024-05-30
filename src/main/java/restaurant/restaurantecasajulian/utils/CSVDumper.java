package restaurant.restaurantecasajulian.utils;

import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.RatingData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.model.users.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to dump the data of the restaurant to CSV files
 * The data is stored in the following files:
 * - config.csv: the configuration of the restaurant
 * - reservations.csv: the reservations of the restaurant
 * - ratings.csv: the ratings of the restaurant
 * - users.csv: the users of the restaurant
 * - tables.csv: the tables of the restaurant
 */

public class CSVDumper {
    public static final String dataPath = "RestaurantData/";
    public static final String configFile = dataPath + "config.csv";
    public static final String reservationsFile = dataPath + "reservations.csv";
    public static final String ratingsFile = dataPath + "ratings.csv";
    public static final String usersFile = dataPath + "users.csv";
    public static final String tablesFile = dataPath + "tables.csv";
    public static final String CSV_SEPARATOR = ";";

    private static RestaurantManager rm = RestaurantManager.getInstance();

    /**
     * Dumps the data of the restaurant to CSV files
     */
    public static void dumpData() {
        dumpConfig();
        dumpReservations();
        dumpRatings();
        dumpUsers();
        dumpTables();
    }

    /**
     * Dumps the configuration of the restaurant to a CSV file
     */
    public static void dumpConfig() {
        FileManager.write(configFile, String.valueOf(rm.isOpen()));
    }

    /**
     * Dumps the reservations of the restaurant to a CSV file
     */
    public static void dumpReservations() {
        List<String> data = new ArrayList<>();
        for (ReservationData reservation : rm.getReservations()) {
            data.add(reservation.toCSV() + CSV_SEPARATOR + rm.isReservationConfirmed(reservation));
        }
        FileManager.write(reservationsFile, data);
    }

    /**
     * Dumps the ratings of the restaurant to a CSV file
     */
    public static void dumpRatings() {
        List<String> data = new ArrayList<>();
        for (RatingData rating : rm.getRatings()) {
            data.add(rating.toCSV());
        }
        FileManager.write(ratingsFile, data);
    }

    /**
     * Dumps the users of the restaurant to a CSV file
     */
    public static void dumpUsers() {
        List<String> data = new ArrayList<>();
        for (User user : rm.getUsers()) {
            data.add(user.toCSV());
        }
        FileManager.write(usersFile, data);
    }

    /**
     * Dumps the tables of the restaurant to a CSV file
     */
    public static void dumpTables() {
        List<String> data = new ArrayList<>();
        for (Table table : rm.getTables()) {
            data.add(table.toCSV());
        }
        FileManager.write(tablesFile, data);
    }
}
