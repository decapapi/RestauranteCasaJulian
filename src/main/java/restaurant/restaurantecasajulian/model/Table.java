package restaurant.restaurantecasajulian.model;

import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

public class Table {
    private static int idCounter = 0;

    private final int id;
    private final int seats;
    public boolean bookable;
    private Map<ReservationData, Boolean> reservations;

    public Table(int seats) {
        this.id = ++idCounter;
        this.seats = seats;
        this.bookable = true;
        this.reservations = new HashMap<>();
    }

    public Table(int seats, boolean bookable) {
        this.id = ++idCounter;
        this.seats = seats;
        this.bookable = bookable;
        this.reservations = new HashMap<>();
    }

    public Table(int tableId, int seats, boolean bookable) {
        this.id = tableId;
        idCounter = Math.max(idCounter, tableId);
        this.seats = seats;
        this.bookable = bookable;
        this.reservations = new HashMap<>();
    }

    public Table(int tableId, int seats, boolean bookable, Map<ReservationData, Boolean> reservations) {
        this.id = tableId;
        idCounter = Math.max(idCounter, tableId);
        this.seats = seats;
        this.bookable = bookable;
        this.reservations = reservations;
    }

    public boolean addReservation(ReservationData reservation) {
        return reservations.put(reservation, false) == null;
    }

    public boolean removeReservation(ReservationData reservation) {
        return reservations.remove(reservation) != null;
    }

    public void allowReservations(boolean allow) {
        this.bookable = allow;
    }

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

    public void confirmReservation(int reservationId) {
        for (ReservationData reservation : reservations.keySet()) {
            if (reservation.id() == reservationId) {
                reservations.put(reservation, true);
                break;
            }
        }
    }

    public List<ReservationData> getReservations() {
        return reservations.keySet().stream().toList();
    }

    public Map<ReservationData, Boolean> getReservationsMap() {
        return reservations;
    }

    public List<ReservationData> getAttendedReservations() {
        List<ReservationData> list = new ArrayList<>();
        for (ReservationData reservation : reservations.keySet()) {
            if (reservations.get(reservation)) {
                list.add(reservation);
            }
        }
        return list;
    }

    public List<ReservationData> getUnattendedReservations() {
        List<ReservationData> list = new ArrayList<>();
        for (ReservationData reservation : reservations.keySet()) {
            if (!reservations.get(reservation)) {
                list.add(reservation);
            }
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isBookable() {
        return bookable;
    }

    public String toCSV() {
        return id + CSV_SEPARATOR + seats + CSV_SEPARATOR + bookable;
    }
}
