package restaurant.restaurantecasajulian.controllers.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class AddEditTable {
    @FXML
    private TextField txtTableId;
    @FXML
    private ComboBox<Integer> comboSeats;
    @FXML
    private ComboBox<Boolean> comboBookable;
    @FXML
    private Label txtAction;

    private final RestaurantManager rm = RestaurantManager.getInstance();
    private boolean isEdit = false;

    @FXML
    private void initialize() {
        comboSeats.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        comboBookable.getItems().addAll(true, false);

        Table selectedTable = ManageTables.getSelectedTable();

        if (selectedTable != null) {
            isEdit = true;
            txtAction.setText("Edit table");
            txtTableId.setText(String.valueOf(selectedTable.getId()));
            comboSeats.setValue(selectedTable.getSeats());
            comboBookable.setValue(selectedTable.isBookable());

        } else {
            txtAction.setText("Add table");
        }
    }

    @FXML
    private void confirm(ActionEvent event) {
        if (isEdit) {
            if (rm.editTable(Integer.parseInt(txtTableId.getText()), comboSeats.getValue(), comboBookable.getValue())) {
                SceneManager.showAlert("Success", "Table edited successfully.", Alert.AlertType.CONFIRMATION);
                SceneManager.loadScreen("manageTables.fxml", event);
            } else {
                SceneManager.showAlert("Error", "Could not edit table.", Alert.AlertType.ERROR);
            }
        } else {
            rm.addTable(new Table(comboSeats.getValue(), comboBookable.getValue()));
            SceneManager.showAlert("Success", "Table added successfully.", Alert.AlertType.CONFIRMATION);
            SceneManager.loadScreen("manageTables.fxml", event);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.loadScreen("manageTables.fxml", event);
    }
}