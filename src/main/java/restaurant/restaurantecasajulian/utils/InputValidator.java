package restaurant.restaurantecasajulian.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Support class to validate data inputs.
 */

public class InputValidator {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Validate a date.
     * Must be in the format dd/MM/yyyy.
     * @param date the date to validate
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validate a time.
     * Must be in the format HH:mm.
     * @param time the time to validate
     * @return true if the time is valid, false otherwise
     */
    public static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time, TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validate an email.
     * @param email the email to validate
     * @return true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email.length() > 256)
            return false;

        email = email.toLowerCase();

        String[] parts = email.split("@");

        if (parts.length != 2)
            return false;

        String local = parts[0];
        String domain = parts[1];

        if (domain.endsWith("."))
            return false;

        if (local.contains("..") || domain.contains(".."))
            return false;

        if (domain.length() < 4 || domain.length() > 255)
            return false;

        if (local.isEmpty() || local.length() > 64)
            return false;

        if (!local.matches("^[a-z0-9][a-z0-9._-]{0,63}"))
            return false;

        return domain.matches("^[a-z0-9][a-z0-9._-]{0,251}\\.[a-z]{2,4}");
    }

    /**
     * Evaluate the security level of a password.
     * @param password the password to validate
     * @return the security level of the password
     */
    public static int getPasswordStrength(String password) {
        int security = 0;
        if (password.length() >= 6) {
            security++;
            if (password.matches(".*\\d.*"))
                security++;
            if (password.matches(".*[a-z].*"))
                security++;
            if (password.matches(".*[A-Z].*"))
                security++;
            if (password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*"))
                security++;
        }

        return security;
    }
}
