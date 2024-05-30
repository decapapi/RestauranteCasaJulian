package restaurant.restaurantecasajulian.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {

    @Test
    void compareTo() {
        TimeSlot timeSlot1 = new TimeSlot(LocalDate.now(), LocalTime.now(), 50);
        TimeSlot timeSlot2 = new TimeSlot(LocalDate.now(), LocalTime.now().plusHours(1), 30); // add 1 hour to the current time
        TimeSlot timeSlot3 = new TimeSlot(LocalDate.now(), LocalTime.now(), 50);

        assertTrue(timeSlot1.compareTo(timeSlot2) < 0); // now timeSlot1 should be less than timeSlot2
        assertEquals(0, timeSlot1.compareTo(timeSlot3));
    }

    @Test
    void parse() {
        TimeSlot timeSlot = new TimeSlot(LocalDate.now(), LocalTime.now(), 50);
        String line = timeSlot.toCSV();
        TimeSlot parsedTimeSlot = TimeSlot.parse(line);

        assertEquals(timeSlot.toString(), parsedTimeSlot.toString());
    }
}