package restaurant.restaurantecasajulian.model;

import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

/**
 * Represents a table in the restaurant.
 */
public class Table {
    private static int idCounter = 0;

    private final int id;
    private final int seats;
    public boolean bookable;
    private Map<ReservationData, Boolean> reservations;

    /**
     * Creates a new table with the given number of seats.
     * The table is bookable by default.
     * @param seats The number of seats at the table.
     */
    public Table(int seats) {
        this.id = ++idCounter;
        this.seats = seats;
        this.bookable = true;
        this.reservations = new HashMap<>();
    }

    /**
     * Creates a new table with the given number of seats and bookable status.
     * @param seats The number of seats at the table.
     * @param bookable Whether the table is bookable.
     */
    public Table(int seats, boolean bookable) {
        this.id = ++idCounter;
        this.seats = seats;
        this.bookable = bookable;
        this.reservations = new HashMap<>();
    }

    /**
     * Creates a new table with the given ID, number of seats, and bookable status.
     * @param tableId The ID of the table.
     * @param seats The number of seats at the table.
     * @param bookable Whether the table is bookable.
     */
    public Table(int tableId, int seats, boolean bookable) {
        this.id = tableId;
        idCounter = Math.max(idCounter, tableId);
        this.seats = seats;
        this.bookable = bookable;
        this.reservations = new HashMap<>();
    }

    /**
     * Creates a new table with the given ID, number of seats, bookable status, and reservations.
     * @param tableId The ID of the table.
     * @param seats The number of seats at the table.
     * @param bookable Whether the table is bookable.
     * @param reservations The reservations for the table.
     */
    public Table(int tableId, int seats, boolean bookable, Map<ReservationData, Boolean> reservations) {
        this.id = tableId;
        idCounter = Math.max(idCounter, tableId);
        this.seats = seats;
        this.bookable = bookable;
        this.reservations = reservations;
    }

    /**
     * Adds a reservation to the table.
     * @param reservation The reservation to add.
     * @return True if the reservation was added, false if a reservation already exists for the same time slot.
     */
    public boolean addReservation(ReservationData reservation) {
        return reservations.put(reservation, false) == null;
    }

    /**
     * Removes a reservation from the table.
     * @param reservationId The ID of the reservation to remove.
     * @return True if the reservation was removed, false if no reservation with the given ID exists.
     */
    public boolean removeReservation(int reservationId) {
        for (ReservationData reservation : reservations.keySet()) {
            if (reservation.id() == reservationId) {
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    /**
     * Allows or disallows reservations for the table.
     * @param allow Whether to allow reservations.
     */
    public void allowReservations(boolean allow) {
        this.bookable = allow;
    }

    /**
     * Checks if the table is available for the given time slot.
     * @param timeSlot The time slot to check.
     * @return True if the table is available, false if a reservation already exists for the time slot.
     */
    public boolean isAvailable(TimeSlot timeSlot) {
        LocalTime endTime = timeSlot.startTime().plusMinutes(timeSlot.durationInMinutes());
        for (ReservationData r : reservations.keySet()) {
            if (r.timeSlot().startDate().isEqual(timeSlot.startDate())) {
                LocalTime reservationEndTime = r.timeSlot().startTime().plusMinutes(r.timeSlot().durationInMinutes());
                if (r.timeSlot().startTime().isBefore(endTime) && reservationEndTime.isAfter(timeSlot.startTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Confirms a reservation.
     * @param reservationId The ID of the reservation to confirm.
     */
    public void confirmReservation(int reservationId) {
        for (ReservationData reservation : reservations.keySet()) {
            if (reservation.id() == reservationId) {
                reservations.put(reservation, true);
                break;
            }
        }
    }

    /**
     * Gets the reservations for the table.
     * @return The reservations for the table.
     */
    public List<ReservationData> getReservations() {
        return reservations.keySet().stream().toList();
    }

    /**
     * Gets the reservations for the table as a map.
     * The map maps each reservation to a boolean indicating whether the reservation has been attended.
     * @return The reservations for the table as a map.
     */
    public Map<ReservationData, Boolean> getReservationsMap() {
        return reservations;
    }

    /**
     * Gets the reservations that have been attended.
     * @return The reservations that have been attended.
     */
    public List<ReservationData> getAttendedReservations() {
        List<ReservationData> list = new ArrayList<>();
        for (ReservationData reservation : reservations.keySet()) {
            if (reservations.get(reservation)) {
                list.add(reservation);
            }
        }
        return list;
    }

    /**
     * Gets the reservations that have not been attended.
     * @return The reservations that have not been attended.
     */
    public List<ReservationData> getUnattendedReservations() {
        List<ReservationData> list = new ArrayList<>();
        for (ReservationData reservation : reservations.keySet()) {
            if (!reservations.get(reservation)) {
                list.add(reservation);
            }
        }
        return list;
    }

    /**
     * Gets the table's ID.
     * @return The table's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the number of seats at the table.
     * @return The number of seats at the table.
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Gets whether the table is bookable.
     * @return True if the table is bookable, false otherwise.
     */
    public boolean isBookable() {
        return bookable;
    }

    /**
     * Gets the table's information as a CSV string.
     * @return The table's information as a CSV string.
     */
    public String toCSV() {
        return id + CSV_SEPARATOR + seats + CSV_SEPARATOR + bookable;
    }
}
