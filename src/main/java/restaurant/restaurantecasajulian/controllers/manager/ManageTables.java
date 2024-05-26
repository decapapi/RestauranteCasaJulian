package restaurant.restaurantecasajulian.controllers.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class ManageTables {

    @FXML
    private TableView<Table> tvTables;
    @FXML
    private TableColumn<Table, Integer> colTableId;
    @FXML
    private TableColumn<Table, Integer> colSeats;
    @FXML
    private TableColumn<Table, Boolean> colBookable;

    private static Table selectedTable;
    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        tvTables.getItems().addAll(rm.getTables());

        colTableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        colBookable.setCellValueFactory(new PropertyValueFactory<>("bookable"));
    }

    @FXML
    private void setSelectedTable() {
        selectedTable = tvTables.getSelectionModel().getSelectedItem();
    }

    @FXML
    private  void removeTable(ActionEvent event) {
        if (selectedTable != null) {
            rm.removeTable(selectedTable.getId());
            tvTables.getItems().remove(selectedTable);
        }
    }

    @FXML
    private  void addTable(ActionEvent event) {
        selectedTable = null;
        SceneManager.loadScreen("addEditTable.fxml", event);
    }

    @FXML
    private  void editTable(ActionEvent event) {
        if (selectedTable != null) {
            SceneManager.loadScreen("addEditTable.fxml", event);
        } else {
            SceneManager.showAlert("Error", "No table selected.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.loadScreen("managerMenu.fxml", event);
    }

    public static Table getSelectedTable() {
        return selectedTable;
    }

}