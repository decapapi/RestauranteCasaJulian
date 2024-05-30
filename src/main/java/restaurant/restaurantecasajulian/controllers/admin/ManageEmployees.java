package restaurant.restaurantecasajulian.controllers.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.model.users.Employee;
import restaurant.restaurantecasajulian.model.users.Manager;
import restaurant.restaurantecasajulian.model.users.User;
import restaurant.restaurantecasajulian.utils.InputValidator;
import restaurant.restaurantecasajulian.utils.SceneManager;

/**
 * Controller for the manage employees screen
 * Allows to manage employees, add, remove, suspend, unsuspend
 */

public class ManageEmployees {

    @FXML
    private TextField txtUsername;
    @FXML
    private ComboBox<String> comboType;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtPasswConfirm;
    @FXML
    private TableView<Employee> tvEmployees;
    @FXML
    private TableColumn<Employee, String> colUsername;
    @FXML
    private TableColumn<Employee, String> colEmail;
    @FXML
    private TableColumn<Employee, Boolean> colBlocked;

    private static Employee selectedEmployee;
    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        tvEmployees.getItems().addAll(rm.getEmployees());

        comboType.getItems().addAll("Employee", "Manager");

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colBlocked.setCellValueFactory(new PropertyValueFactory<>("blocked"));
    }

    @FXML
    private void addEmployee(ActionEvent event) {
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String passwConfirm = txtPasswConfirm.getText();
        String type = comboType.getValue();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwConfirm.isEmpty() || type == null) {
            SceneManager.showAlert("Error", "All fields must be filled", Alert.AlertType.ERROR);
        } else {
            if (!password.equals(passwConfirm)) {
                SceneManager.showAlert("Error", "Passwords do not match", Alert.AlertType.ERROR);
            } else {
                if (!InputValidator.isValidEmail(email)) {
                    SceneManager.showAlert("Error", "Invalid email", Alert.AlertType.ERROR);
                } else {
                    if (rm.getUserByUsername(username) != null) {
                        SceneManager.showAlert("Error", "Username already exists", Alert.AlertType.ERROR);
                    } else {
                        if (type.equals("Manager")) {
                            rm.addUser(new Manager(username, password, email));
                        } else {
                            rm.addUser(new Employee(username, password, email));
                        }

                        tvEmployees.getItems().add((Employee)rm.getUserByUsername(username));
                        txtUsername.clear();
                        txtEmail.clear();
                        txtPassword.clear();
                        txtPasswConfirm.clear();
                        comboType.getSelectionModel().clearSelection();
                        tvEmployees.refresh();
                        SceneManager.showAlert("Success", "Employee added", Alert.AlertType.INFORMATION);
                    }
                }
            }
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        rm.logout();
        SceneManager.showAlert("Success", "Logged out", Alert.AlertType.INFORMATION);
        SceneManager.loadScreen("login.fxml", event);
    }

    @FXML
    private void terminateEmployee(ActionEvent event) {
        if (getSelectedEmployee() != null) {
            rm.removeUser(getSelectedEmployee().getUsername());
            tvEmployees.getItems().remove(getSelectedEmployee());
            tvEmployees.refresh();
            SceneManager.showAlert("Success", "Employee terminated", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void suspendEmployee(ActionEvent event) {
        if (getSelectedEmployee() != null) {
            Stage s = SceneManager.loadModal("suspendEmployee.fxml", event);
            s.setOnHidden(e -> tvEmployees.refresh());
        }
    }

    @FXML
    private void unsuspendEmployee(ActionEvent event) {
        if (getSelectedEmployee() != null) {
            if (getSelectedEmployee().isBlocked()) {
                getSelectedEmployee().unblock();
                tvEmployees.refresh();
                SceneManager.showAlert("Success", "Employee unsuspended", Alert.AlertType.INFORMATION);
            } else {
                SceneManager.showAlert("Error", "Employee is not suspended", Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Get the selected employee
     * Allows to get the selected employee from the table view
     * @return the selected employee
     */
    public static Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    @FXML
    private void setSelectedEmployee() {
        selectedEmployee = tvEmployees.getSelectionModel().getSelectedItem();
    }
}