package restaurant.restaurantecasajulian.data;

import java.util.List;

public record ReservationData(String userId, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders, int id) {
    private static int idCounter = 0;

    public ReservationData(String userId, int tableId, TimeSlot timeSlot, String comments, List<DishData> preOrders) {
        this(userId, tableId, timeSlot, comments, preOrders, ++idCounter);
    }
}
