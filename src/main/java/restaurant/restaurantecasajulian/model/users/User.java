package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.data.types.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

/**
 * Represents a user of the system.
 * This class is abstract and should be extended by concrete user types.
 */

public abstract  class User {
    private final String username;
    private final String password;
    private final String email;
    private final UserType userType;
    private TimeSlot blockedTime = null;

    /**
     * Creates a new user.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param email the email of the user.
     * @param userType the type of the user.
     */
    public User(String username, String password, String email, UserType userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    /**
     * Gets the username of the user.
     * @return the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email of the user.
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Checks if the user is blocked.
     * @return true if the user is blocked, false otherwise.
     */
    public boolean isBlocked() {
        if (this.blockedTime == null)
            return false;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime blockEnd = LocalDateTime.of(this.blockedTime.startDate(),
                this.blockedTime.startTime()).plusMinutes(this.blockedTime.durationInMinutes());
        return now.isBefore(blockEnd);
    }

    /**
     * Gets the block of the user.
     * @return the block of the user, null if the user is not blocked.
     */
    public TimeSlot getUserBlock() {
        if (!isBlocked())
            return null;
        return this.blockedTime;
    }

    /**
     * Blocks the user for a certain amount of time.
     * @param durationInMinutes duration of the block in minutes.
     */
    public void block(int durationInMinutes) {
        this.blockedTime = new TimeSlot(LocalDate.now(), LocalTime.now(), durationInMinutes);
    }

    /**
     * Unblocks the user.
     */
    public void unblock() {
        this.blockedTime = null;
    }

    /**
     * Gets the type of the user.
     * @return the type of the user.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Checks if the user is an admin.
     * @return true if the user is an admin, false otherwise.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Converts the user to a CSV string.
     * @return the CSV string.
     */
    public String toCSV() {
        return userType.name() + CSV_SEPARATOR + username + CSV_SEPARATOR + password + CSV_SEPARATOR + email;
    }
}
