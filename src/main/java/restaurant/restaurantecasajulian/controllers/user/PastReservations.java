package restaurant.restaurantecasajulian.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.utils.SceneManager;

/**
 * Controller for the Past Reservations screen.
 * This screen shows the user's past reservations.
 * Shows the list of past reservations and allows the user to leave a rating for a reservation.
 */

public class PastReservations {

    @FXML
    private TableView<ReservationData> tvReservations;
    @FXML
    private TableColumn<ReservationData, Integer> colTableId;
    @FXML
    private TableColumn<ReservationData, String> colDate;
    @FXML
    private TableColumn<ReservationData, String> colTime;
    @FXML
    private TableColumn<ReservationData, String> colComments;

    private static ReservationData selectedReservation;
    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    private void initialize() {
        tvReservations.getItems().addAll(rm.getAttendedReservations(rm.getCurrentUser().getUsername()));

        colTableId.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
    }

    @FXML
    private void leaveRating(ActionEvent event) {
        if (getSelectedReservation() != null) {
            Stage modal = SceneManager.loadModal("leaveRating.fxml", event);
            modal.setOnHidden(e -> tvReservations.refresh());
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        SceneManager.loadScreen("customerMenu.fxml", event);
    }

    /**
     * Get the selected reservation.
     * Allows the Leave Rating modal to get the selected reservation.
     * @return the selected reservation
     */

    public static ReservationData getSelectedReservation() {
        return selectedReservation;
    }

    @FXML
    private void setSelectedReservation() {
        selectedReservation = tvReservations.getSelectionModel().getSelectedItem();
    }
}