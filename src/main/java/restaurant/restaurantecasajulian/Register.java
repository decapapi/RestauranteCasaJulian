package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.model.users.Customer;
import restaurant.restaurantecasajulian.utils.InputValidator;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class Register {
    public Label txtWelcome;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label txtPasswStrength;

    private RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void register(ActionEvent actionEvent) {
        if (txtUsername.getText().isEmpty()) {
            SceneManager.showAlert("Invalid credentials", "Username cannot be empty.", Alert.AlertType.ERROR);
        } else if (!InputValidator.isValidEmail(txtEmail.getText())) {
            SceneManager.showAlert("Invalid email", "The email you entered is not valid.", Alert.AlertType.ERROR);
        } else if (InputValidator.getPasswordStrength(txtPassword.getText()) < 3) {
            SceneManager.showAlert("Invalid password", "The password you entered is too weak.", Alert.AlertType.ERROR);
        } else if (rm.register(new Customer(txtUsername.getText(), txtPassword.getText(), txtEmail.getText()))) {
            SceneManager.showAlert("Registration successful!", "You have been registered correctly"
                    , Alert.AlertType.CONFIRMATION);
            SceneManager.loadScreen("login.fxml", actionEvent);
        } else {
            SceneManager.showAlert("Invalid credentials",
                    "Could not register with that credentials. Username may be taken.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {
        SceneManager.loadScreen("login.fxml", actionEvent);
    }

    @FXML
    private void testPasswordStrength(KeyEvent keyEvent) {
        int strength = InputValidator.getPasswordStrength(txtPassword.getText());

        switch (strength) {
            case 0:
                txtPasswStrength.setText("Password is very weak.");
                txtPasswStrength.setTextFill(Color.RED);
                break;
            case 1:
                txtPasswStrength.setText("Password is weak.");
                txtPasswStrength.setTextFill(Color.ORANGERED);
                break;
            case 2:
                txtPasswStrength.setText("Password is average");
                txtPasswStrength.setTextFill(Color.ORANGE);
                break;
            case 3:
                txtPasswStrength.setText("Password is strong");
                txtPasswStrength.setTextFill(Color.YELLOWGREEN);
                break;
            case 4:
                txtPasswStrength.setText("Password is very strong");
                txtPasswStrength.setTextFill(Color.GREEN);
                break;
            case 5:
                txtPasswStrength.setText("Password is extremely strong");
                txtPasswStrength.setTextFill(Color.GREEN);
                break;
        }
    }
}