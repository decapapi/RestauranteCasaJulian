package restaurant.restaurantecasajulian.data;

public record RatingKey(String userId, TimeSlot date) {
    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RatingKey key = (RatingKey) obj;
        return userId.equals(key.userId) && date.compareTo(key.date) == 0;
    }
}
