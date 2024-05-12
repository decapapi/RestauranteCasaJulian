package restaurant.restaurantecasajulian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.DishData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.data.TimeSlot;
import restaurant.restaurantecasajulian.model.Table;
import restaurant.restaurantecasajulian.utils.InputValidator;
import restaurant.restaurantecasajulian.utils.SceneManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Reservations {
    @FXML
    private ComboBox<String> comboReservationType;
    @FXML
    private ComboBox<String> comboSeats;
    @FXML
    private ComboBox<String> comboTime;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextArea txtComments;
    @FXML
    private ComboBox<String> comboDishName;
    @FXML
    private ComboBox<String> comboRations;
    @FXML
    private TableView<DishData> tvPreOrder;
    @FXML
    private TableColumn<DishData, String> colDishName;
    @FXML
    private TableColumn<DishData, Integer> colRations;

    private RestaurantManager rm = RestaurantManager.getInstance();

    String[] lunchTimes = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30"};

    @FXML
    private void initialize() {
        comboReservationType.getItems().addAll("Lunch", "Meal");
        comboSeats.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        comboDishName.getItems().addAll("Paella valenciana", "Paella mixta", "Arroz de marisco");
        comboRations.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        comboReservationType.setValue("Meal");
        comboSeats.setValue("1");
        comboDishName.setValue("Paella valenciana");
        comboRations.setValue("1");
        dpDate.setValue(LocalDate.now());

        updateAvailableTables(null);

        colDishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRations.setCellValueFactory(new PropertyValueFactory<>("rations"));
    }

    @FXML
    private void updateAvailableTables(ActionEvent actionEvent) {
        if (comboReservationType.getValue().equals("Lunch")) {
            comboTime.getItems().clear();
            List<Table> availableTables = rm.getTablesWithSeats(Integer.parseInt(comboSeats.getValue()));
            for (String slot : lunchTimes) {
                LocalTime slotTime = LocalTime.parse(slot, InputValidator.TIME_FORMATTER);
                TimeSlot potentialSlot = new TimeSlot(dpDate.getValue(), slotTime, 60);
                boolean available = false;
                for (int i = 0; i < availableTables.size() && !available; i++) {
                    if (availableTables.get(i).isAvailable(potentialSlot)) {
                        comboTime.getItems().add(slot);
                        available = true;
                    }
                }
            }
        } else {
            comboTime.getItems().clear();
            List<Table> availableTables = rm.getTablesWithSeats(Integer.parseInt(comboSeats.getValue()));
            LocalTime slotTime = LocalTime.parse("13:00", InputValidator.TIME_FORMATTER);
            TimeSlot potentialSlot = new TimeSlot(dpDate.getValue(), slotTime, 60);
            boolean available = false;
            for (int i = 0; i < availableTables.size() && !available; i++) {
                if (availableTables.get(i).isAvailable(potentialSlot)) {
                    comboTime.getItems().add("13:00");
                    available = true;
                }
            }
        }
    }

    @FXML
    private void makeReservation(ActionEvent actionEvent) {
        if (comboReservationType.getValue() == null || comboSeats.getValue() == null || comboTime.getValue() == null || dpDate.getValue() == null) {
            SceneManager.showAlert("Error", "Please fill in all the fields.", Alert.AlertType.ERROR);
        } else {
            List<Table> availableTables = rm.getTablesWithSeats(Integer.parseInt(comboSeats.getValue()));
            LocalTime slotTime = LocalTime.parse(comboTime.getValue(), InputValidator.TIME_FORMATTER);
            TimeSlot reservationSlot = new TimeSlot(dpDate.getValue(), slotTime, 60);
            boolean reservationMade = false;
            for (int i = 0; i < availableTables.size() && !reservationMade; i++) {
                if (availableTables.get(i).isAvailable(reservationSlot)) {
                    List<DishData> preOrders = new ArrayList<>(tvPreOrder.getItems());
                    if (availableTables.get(i).addReservation(new ReservationData(rm.getCurrentUser().getUsername(),
                            availableTables.get(i).getId(), reservationSlot, txtComments.getText(), preOrders))) {
                        reservationMade = true;
                    }
                }
            }
            if (!reservationMade) {
                SceneManager.showAlert("Error", "A reservation could not be made with that details.", Alert.AlertType.ERROR);
            } else {
                SceneManager.showAlert("Reservation", "Reservation made successfully!", Alert.AlertType.CONFIRMATION);
                goBack(actionEvent);
            }
        }
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        rm.logout();
        SceneManager.showAlert("Logout", "You have been logged out successfully.", Alert.AlertType.INFORMATION);
        goBack(actionEvent);
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {
        SceneManager.loadScreen("customerMenu.fxml", actionEvent);
    }

    public void addPreOrder(ActionEvent actionEvent) {
        if (comboDishName.getValue() == null || comboRations.getValue() == null) {
            SceneManager.showAlert("Error", "Please select a dish and the number of rations.", Alert.AlertType.ERROR);
        } else {
            DishData dishData = new DishData(comboDishName.getValue(), Integer.parseInt(comboRations.getValue()));
            tvPreOrder.getItems().add(dishData);
        }
    }

    public void removePreOrder(ActionEvent actionEvent) {
        DishData selectedDish = tvPreOrder.getSelectionModel().getSelectedItem();
        if (selectedDish == null) {
            SceneManager.showAlert("Error", "Please select a dish to remove.", Alert.AlertType.ERROR);
        } else {
            tvPreOrder.getItems().remove(selectedDish);
        }
    }
}