package restaurant.restaurantecasajulian.controllers.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class ManagerMenu {
    @FXML
    private Label txtWelcome;
    
    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        txtWelcome.setText("Welcome, " + rm.getCurrentUser().getUsername());
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        rm.logout();
        SceneManager.showAlert("Logout", "You have been logged out successfully.", Alert.AlertType.INFORMATION);
        SceneManager.loadScreen("login.fxml", actionEvent);
    }

    @FXML
    private void showManageTables(ActionEvent event) {
        SceneManager.loadScreen("manageTables.fxml", event);
    }

    @FXML
    private void showManageReservations(ActionEvent event) {
    }

    @FXML
    private void showPremisesControl(ActionEvent event) {
    }
}