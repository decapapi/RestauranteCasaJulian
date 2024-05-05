package restaurant.restaurantecasajulian.model.users;

import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.data.types.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract  class User {
    private final String username;
    private final String password;
    private final String email;
    private final UserType userType;
    private TimeSlot blockedTime = null;

    private static final String CSV_SEPARATOR = ";";

    public User(String username, String password, String email, UserType userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

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

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void toCSV() {
        System.out.println(username + CSV_SEPARATOR + password + CSV_SEPARATOR + email);
    }
}
