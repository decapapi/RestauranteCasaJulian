package restaurant.restaurantecasajulian.controllers.employee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class ConfirmAttendance {

    @FXML
    private TableView<ReservationData> tvReservations;
    @FXML
    private TableColumn<ReservationData, String> colUsername;
    @FXML
    private TableColumn<ReservationData, Integer> colTableId;
    @FXML
    private TableColumn<ReservationData, String> colDate;
    @FXML
    private TableColumn<ReservationData, String> colTime;
    @FXML
    private TableColumn<ReservationData, String> colComments;

    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    public void initialize() {
        tvReservations.getItems().addAll(rm.getUnattendedReservations());

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colTableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    @FXML
    private void confirmAttendance(ActionEvent event) {
        ReservationData reservation = tvReservations.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            rm.getTableById(reservation.getTableId()).confirmReservation(reservation.id());
            tvReservations.getItems().remove(reservation);
            tvReservations.refresh();
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.loadScreen("employeeMenu.fxml", event);
    }
}