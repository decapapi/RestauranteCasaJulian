package restaurant.restaurantecasajulian.data;

import restaurant.restaurantecasajulian.utils.InputValidator;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a time slot with a start date, start time and duration in minutes
 * @param startDate the start date of the time slot
 * @param startTime the start time of the time slot
 * @param durationInMinutes the duration of the time slot in minutes
 */
public record TimeSlot(LocalDate startDate, LocalTime startTime, int durationInMinutes) implements Comparable<TimeSlot> {
    /**
     * String representation of the TimeSlot
     * @return the string representation of the TimeSlot
     */
    @Override
    public String toString() {
        return "Date: " + startDate.format(InputValidator.DATE_FORMATTER) +
                ", Time: " + startTime.format(InputValidator.TIME_FORMATTER);
    }

    public String toCSV() {
        return startDate.format(InputValidator.DATE_FORMATTER) + "-" +
                startTime.format(InputValidator.TIME_FORMATTER) + "-" +
                durationInMinutes;
    }

    /**
     * Get the timeslot start date
     * @return the timeslot start date
     */
    public int compareTo(TimeSlot timeSlot) {
        int output = this.startDate.compareTo(timeSlot.startDate);
        if (output == 0)
            output = this.startTime.compareTo(timeSlot.startTime);
        return output;
    }

    public static TimeSlot parse(String line) {
        String[] fields = line.split("-");
        String date = fields[0];
        String time = fields[1];
        String duration = fields[2];
        LocalDate startDate = LocalDate.parse(date, InputValidator.DATE_FORMATTER);
        LocalTime startTime = LocalTime.parse(time, InputValidator.TIME_FORMATTER);
        int durationInMinutes = Integer.parseInt(duration);
        return new TimeSlot(startDate, startTime, durationInMinutes);
    }
}