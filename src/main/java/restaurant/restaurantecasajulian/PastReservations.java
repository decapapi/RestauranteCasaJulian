package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.RatingData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class PastReservations {

    @FXML
    private TableView<ReservationData> tvReservations;
    @FXML
    private TableColumn<ReservationData, String> colUser;
    @FXML
    private TableColumn<ReservationData, Integer> colTableId;
    @FXML
    private TableColumn<ReservationData, TimeSlot> colTimeslot;
    @FXML
    private TableColumn<ReservationData, String> colComments;

    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    public void initialize() {
        tvReservations.getItems().addAll(rm.getAttendedReservations(rm.getCurrentUser().getUsername()));

        colUser.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colTableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        colTimeslot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        colComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    @FXML
    private void leaveRating(ActionEvent event) {
        ReservationData reservation = tvReservations.getSelectionModel().getSelectedItem();
        if (reservation != null) {
            SceneManager.loadModal("leaveRating.fxml", event);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.loadScreen("customerMenu.fxml", event);
    }

}