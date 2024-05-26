package restaurant.restaurantecasajulian.data;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

public record RatingData(int tableId, String userId, TimeSlot date, String message, float rating) {
    public RatingKey getKey() {
        return new RatingKey(tableId, userId, date);
    }

    public String toCSV() {
        return tableId + CSV_SEPARATOR + userId + CSV_SEPARATOR + date.toCSV() + CSV_SEPARATOR + message + CSV_SEPARATOR + rating;
    }
}
