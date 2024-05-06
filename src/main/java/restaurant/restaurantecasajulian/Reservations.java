package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class Reservations {
    @FXML
    private ComboBox<String> comboReservationType;
    @FXML
    private ComboBox<String> comboSeats;
    @FXML
    private ComboBox<String> comboTime;

    private RestaurantManager rm = RestaurantManager.getInstance();

    String[] lunchTimes = {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30"};
    String[] mealTimes = {"13:00", "13:30", "14:00", "14:30", "15:00"};

    @FXML
    private void initialize() {
        comboReservationType.getItems().addAll("Lunch reservation", "Meal reservation");
        comboReservationType.setValue("Meal reservation");
        comboSeats.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        if (comboReservationType.getValue().equals("Lunch reservation")) {
            comboTime.getItems().addAll(lunchTimes);
        } else {
            comboTime.getItems().addAll(mealTimes);
        }
    }

    @FXML
    private void updateAvailableTables(ActionEvent actionEvent) {
        
    }

    @FXML
    private void makeReservation(ActionEvent actionEvent) {
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        rm.logout();
        SceneManager.showAlert("Logout", "You have been logged out successfully.", Alert.AlertType.INFORMATION);
        SceneManager.loadScreen("login.fxml", actionEvent);
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {
        SceneManager.loadScreen("customerMenu.fxml", actionEvent);
    }

}