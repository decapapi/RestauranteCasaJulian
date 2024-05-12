package restaurant.restaurantecasajulian.data;

import restaurant.restaurantecasajulian.utils.InputValidator;

import java.util.List;

public record ReservationData(String username, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders, int id) {
    private static int idCounter = 0;

    public ReservationData(String userId, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders) {
        this(userId, tableId, timeSlot, comments, preOrders, ++idCounter);
    }

    public String getUsername() {
        return username;
    }

    public int getTableId() {
        return tableId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getDate() {
        return timeSlot.startDate().format(InputValidator.DATE_FORMATTER);
    }

    public String getTime() {
        return timeSlot.startTime().format(InputValidator.TIME_FORMATTER);
    }

    public String getComments() {
        return comments;
    }

    public List<DishData> getPreOrders() {
        return preOrders;
    }
}
