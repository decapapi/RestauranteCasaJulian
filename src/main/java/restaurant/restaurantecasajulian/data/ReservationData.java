package restaurant.restaurantecasajulian.data;

import restaurant.restaurantecasajulian.utils.InputValidator;

import java.util.List;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

/**
 * Class that represents a reservation in the system.
 * It contains the username of the user that made the reservation, the table id, the time slot, the comments and the pre-orders.
 * @param username
 * @param tableId
 * @param timeSlot
 * @param comments
 * @param preOrders
 * @param id
 */

public record ReservationData(String username, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders, int id) {
    private static int idCounter = 0;

    /**
     * Constructor for the ReservationData class.
     * @param userId The username of the user that made the reservation.
     * @param tableId The id of the table that was reserved.
     * @param timeSlot The time slot of the reservation.
     * @param comments The comments of the reservation.
     * @param preOrders The pre-orders of the reservation.
     */
    public ReservationData(String userId, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders) {
        this(userId, tableId, timeSlot, comments, preOrders, ++idCounter);
    }

    /**
     * Getter for the Reservation id.
     * @return The id of the reservation.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the Reservation username.
     * @return The username of the user that made the reservation.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the Reservation table id.
     * @return The id of the table that was reserved.
     */
    public int getTableId() {
        return tableId;
    }

    /**
     * Getter for the Reservation time slot.
     * @return The time slot of the reservation.
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Getter for the reservation date.
     * @return The date of the reservation.
     */
    public String getDate() {
        return timeSlot.startDate().format(InputValidator.DATE_FORMATTER);
    }

    /**
     * Getter for the reservation time.
     * @return The time of the reservation.
     */
    public String getTime() {
        return timeSlot.startTime().format(InputValidator.TIME_FORMATTER);
    }

    /**
     * Getter for the Reservation comments.
     * @return The comments of the reservation.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Getter for the Reservation pre-orders.
     * @return The pre-orders of the reservation.
     */
    public List<DishData> getPreOrders() {
        return preOrders;
    }

    /**
     * Method that returns the reservation data in CSV format.
     * @return The reservation data in CSV format.
     */
    public String toCSV() {
        StringBuilder preOrdersString = new StringBuilder();
        for (DishData dish : preOrders) {
            preOrdersString.append(dish.getName()).append(":").append(dish.getRations()).append(CSV_SEPARATOR);
        }
        if (!preOrdersString.isEmpty()) {
            preOrdersString.setLength(preOrdersString.length() - 1);
        }

        return id + CSV_SEPARATOR + username + CSV_SEPARATOR + tableId + CSV_SEPARATOR + timeSlot.toCSV() + CSV_SEPARATOR + comments + CSV_SEPARATOR + preOrdersString;
    }
}
