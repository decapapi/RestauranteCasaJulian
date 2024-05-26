package restaurant.restaurantecasajulian.controllers.manager;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.DishData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class ManageReservations {

    @FXML
    private TableView<ReservationData> tvReservations;
    @FXML
    private TableView<DishData> tvPreorder;
    @FXML
    private TableColumn<ReservationData, Integer> colReservationId;
    @FXML
    private TableColumn<ReservationData, Integer> colTableId;
    @FXML
    private TableColumn<ReservationData, TimeSlot> colTimeslot;
    @FXML
    private TableColumn<ReservationData, String> colComments;
    @FXML
    private TableColumn<ReservationData, String> colName;
    @FXML
    private TableColumn<ReservationData, Integer> colRations;

    private static ReservationData selectedReservation;
    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        tvReservations.getItems().addAll(rm.getReservations());

        colReservationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        colTimeslot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        colComments.setCellValueFactory(new PropertyValueFactory<>("comments"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRations.setCellValueFactory(new PropertyValueFactory<>("rations"));
    }

    @FXML
    private void makeReservation(ActionEvent event) {
        Stage s = SceneManager.loadModal("reservations.fxml", event);
        s.setOnHidden(e -> {
            tvReservations.getItems().clear();
            tvReservations.getItems().addAll(rm.getReservations());
        });
    }

    @FXML
    private void cancelReservation(ActionEvent event) {
        if (selectedReservation != null) {
            rm.cancelReservation(selectedReservation.id());
            tvReservations.getItems().remove(selectedReservation);
            tvPreorder.getItems().clear();
        } else {
            SceneManager.showAlert("Error", "No reservation selected.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.loadScreen("managerMenu.fxml", event);
    }

    @FXML
    private void setSelectedReservation() {
        selectedReservation = tvReservations.getSelectionModel().getSelectedItem();
        tvPreorder.setItems(FXCollections.observableArrayList(selectedReservation.getPreOrders()));
    }

    public static ReservationData getSelectedReservation() {
        return selectedReservation;
    }
}