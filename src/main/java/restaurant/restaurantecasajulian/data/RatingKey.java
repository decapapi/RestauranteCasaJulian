package restaurant.restaurantecasajulian.data;

/**
 * Class that represents the composite key of the Rating
 * @param tableId
 * @param userId
 * @param date
 */

public record RatingKey(int tableId, String userId, TimeSlot date) {
    /**
     * Method that compares the key with another object
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RatingKey key = (RatingKey) obj;
        return tableId == key.tableId && userId.equals(key.userId) && date.compareTo(key.date) == 0;
    }
}
