package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class EmployeeMenu {
    @FXML
    private Label txtWelcome;
    
    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    public void initialize() {
        txtWelcome.setText("Welcome, " + rm.getCurrentUser().getUsername());
    }

    public void showReservationsMenu(ActionEvent actionEvent) {
        SceneManager.loadScreen("reservations.fxml", actionEvent);
    }

    public void confirmAttendance(ActionEvent actionEvent) {
        SceneManager.loadScreen("confirmAttendance.fxml", actionEvent);
    }

    public void logout(ActionEvent actionEvent) {
        rm.logout();
        SceneManager.showAlert("Logout", "You have been logged out successfully.", Alert.AlertType.INFORMATION);
        SceneManager.loadScreen("login.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) {
        SceneManager.loadScreen("customerMenu.fxml", actionEvent);
    }
}