package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.types.UserType;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class Login {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private RestaurantManager rm = RestaurantManager.getInstance();

    public void login(ActionEvent actionEvent) {
        if (rm.login(txtUsername.getText(), txtPassword.getText())) {
            if (rm.getCurrentUser().isBlocked()) {
                SceneManager.showAlert("Account blocked",
                        "Your account is currently blocked. You cannot log in.", Alert.AlertType.ERROR);
            } else {
                SceneManager.showAlert("Login successful!", "You have been logged in correctly. Welcome, "
                        + rm.getCurrentUser().getUsername(), Alert.AlertType.CONFIRMATION);
                if (rm.getCurrentUser().getUserType() == UserType.CUSTOMER) {
                    SceneManager.loadScreen("customerMenu.fxml", actionEvent);
                }
            }
        } else {
            SceneManager.showAlert("Invalid credentials",
                    "Could not log in with that credentials. Please try again.", Alert.AlertType.ERROR);
        }
    }

    public void register(ActionEvent actionEvent) {
        SceneManager.loadScreen("register.fxml", actionEvent);
    }
}