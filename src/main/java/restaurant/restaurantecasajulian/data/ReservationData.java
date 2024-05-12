package restaurant.restaurantecasajulian.data;

import java.util.List;

public record ReservationData(String userId, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders, int id) {
    private static int idCounter = 0;

    public ReservationData(String userId, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders) {
        this(userId, tableId, timeSlot, comments, preOrders, ++idCounter);
    }

    public String getUserId() {
        return userId;
    }

    public int getTableId() {
        return tableId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getComments() {
        return comments;
    }

    public List<DishData> getPreOrders() {
        return preOrders;
    }
}
