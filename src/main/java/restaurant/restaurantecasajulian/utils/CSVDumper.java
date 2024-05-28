package restaurant.restaurantecasajulian.utils;

import javafx.scene.control.Alert;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.RatingData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.model.users.User;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVDumper {
    public static final String dataPath = "RestaurantData/";
    public static final String configFile = dataPath + "config.csv";
    public static final String reservationsFile = dataPath + "reservations.csv";
    public static final String ratingsFile = dataPath + "ratings.csv";
    public static final String usersFile = dataPath + "users.csv";
    public static final String tablesFile = dataPath + "tables.csv";
    public static final String CSV_SEPARATOR = ";";

    private static RestaurantManager rm = RestaurantManager.getInstance();

    public static void dumpData() {
        dumpConfig();
        dumpReservations();
        dumpRatings();
        dumpUsers();
        dumpTables();
    }

    public static void dumpConfig() {
        FileManager.write(configFile, String.valueOf(rm.isOpen()));
    }

    public static void dumpReservations() {
        List<String> data = new ArrayList<>();
        for (ReservationData reservation : rm.getReservations()) {
            data.add(reservation.toCSV() + CSV_SEPARATOR + rm.isReservationConfirmed(reservation));
        }
        FileManager.write(reservationsFile, data);
    }

    public static void dumpRatings() {
        List<String> data = new ArrayList<>();
        for (RatingData rating : rm.getRatings()) {
            data.add(rating.toCSV());
        }
        FileManager.write(ratingsFile, data);
    }

    public static void dumpUsers() {
        List<String> data = new ArrayList<>();
        for (User user : rm.getUsers()) {
            data.add(user.toCSV());
        }
        FileManager.write(usersFile, data);
    }

    public static void dumpTables() {
        List<String> data = new ArrayList<>();
        for (Table table : rm.getTables()) {
            data.add(table.toCSV());
        }
        FileManager.write(tablesFile, data);
    }
}
