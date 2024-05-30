package restaurant.restaurantecasajulian.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.utils.SceneManager;

/**
 * CustomerMenu screen controller
 * Allows the user to navigate through the customer menu
 */

public class CustomerMenu {
    @FXML
    private Label txtWelcome;
    
    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        txtWelcome.setText("Welcome, " + rm.getCurrentUser().getUsername());
    }

    @FXML
    private void showReservationsMenu(ActionEvent actionEvent) {
        SceneManager.loadScreen("reservations.fxml", actionEvent);
    }

    @FXML
    private void viewPastReservations(ActionEvent actionEvent) {
        SceneManager.loadScreen("pastReservations.fxml", actionEvent);
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        rm.logout();
        SceneManager.showAlert("Logout", "You have been logged out successfully.", Alert.AlertType.INFORMATION);
        SceneManager.loadScreen("login.fxml", actionEvent);
    }
}