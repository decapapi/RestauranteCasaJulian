package restaurant.restaurantecasajulian.controllers.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.utils.SceneManager;

/**
 * ManagerMenu screen controller
 * Allows the manager to navigate through the different manager options
 */

public class ManagerMenu {
    @FXML
    private Label txtWelcome;
    @FXML
    private CheckBox chkOpen;
    
    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        txtWelcome.setText("Welcome, " + rm.getCurrentUser().getUsername());
        chkOpen.setSelected(rm.isOpen());
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
        SceneManager.loadScreen("manageReservations.fxml", event);
    }

    @FXML
    private void toggleOpen() {
        RestaurantManager.getInstance().setOpen(chkOpen.isSelected());
    }
}