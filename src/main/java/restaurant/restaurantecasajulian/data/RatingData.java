package restaurant.restaurantecasajulian.data;

public record RatingData(String userId, TimeSlot date, String message, float rating) {
    public RatingKey getKey() {
        return new RatingKey(userId, date);
    }
}
