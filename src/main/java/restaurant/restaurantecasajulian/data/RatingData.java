package restaurant.restaurantecasajulian.data;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

/**
 * Class that represents a rating for a reservation
 * @param tableId
 * @param userId
 * @param date
 * @param message
 * @param rating
 */

public record RatingData(int tableId, String userId, TimeSlot date, String message, float rating) {
    public RatingKey getKey() {
        return new RatingKey(tableId, userId, date);
    }

    /**
     * Method to convert the rating to a CSV format
     * @return String with the rating in CSV format
     */

    public String toCSV() {
        return tableId + CSV_SEPARATOR + userId + CSV_SEPARATOR + date.toCSV() + CSV_SEPARATOR + message + CSV_SEPARATOR + rating;
    }
}
