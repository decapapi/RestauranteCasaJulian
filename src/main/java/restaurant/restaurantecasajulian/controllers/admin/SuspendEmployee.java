package restaurant.restaurantecasajulian.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.model.users.User;
import restaurant.restaurantecasajulian.utils.SceneManager;

/**
 * Controller for the SuspendEmployee view
 * This view is used to suspend an employee
 * @see restaurant.restaurantecasajulian.model.users.Employee
 * @see restaurant.restaurantecasajulian.model.users.User
 */

public class SuspendEmployee {
    @FXML
    private TextField txtUsername;
    @FXML
    private ComboBox<String> comboDuration;

    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        comboDuration.getItems().addAll("1 day", "1 week", "1 month", "1 year", "2 years", "5 years");

        if (ManageEmployees.getSelectedEmployee() != null) {
            txtUsername.setText(ManageEmployees.getSelectedEmployee().getUsername());
        }
    }

    @FXML
    private void suspend(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        if (username.isEmpty()) {
            SceneManager.showAlert("Username is required", "Please enter the username", Alert.AlertType.WARNING);
        } else {
            User user = rm.getUserByUsername(username);
            if (user == null) {
                SceneManager.showAlert("User not found", "The user does not exist", Alert.AlertType.WARNING);
            } else {
                if (comboDuration.getValue() != null) {
                    user.block(getDuration());
                    SceneManager.showAlert("User suspended", "The user has been suspended", Alert.AlertType.INFORMATION);
                    SceneManager.closeWindow(actionEvent);
                } else {
                    SceneManager.showAlert("Duration is required", "Please select the duration", Alert.AlertType.WARNING);;
                }
            }
        }
    }

    private int getDuration() {
        return switch (comboDuration.getValue()) {
            case "1 day" -> 60 * 24;
            case "1 week" -> 60 * 24 * 7;
            case "1 month" -> 60 * 24 * 30;
            case "1 year" -> 60 * 24 * 365;
            case "2 years" -> 60 * 24 * 365 * 2;
            case "5 years" -> 60 * 24 * 365 * 5;
            default -> 0;
        };
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.closeWindow(event);
    }
}