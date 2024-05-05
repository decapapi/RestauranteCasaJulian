package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.model.users.Customer;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class Register {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    public void register(ActionEvent actionEvent) {
        if (rm.register(new Customer(txtUsername.getText(), txtPassword.getText(), txtEmail.getText()))) {
            SceneManager.showAlert("Registration successful!", "You have been registered correctly"
                    , Alert.AlertType.CONFIRMATION);
            SceneManager.loadScreen("login.fxml", actionEvent);
        } else {
            SceneManager.showAlert("Invalid credentials",
                    "Could not register with that credentials. Username may be taken.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        SceneManager.loadScreen("login.fxml", actionEvent);
    }
}