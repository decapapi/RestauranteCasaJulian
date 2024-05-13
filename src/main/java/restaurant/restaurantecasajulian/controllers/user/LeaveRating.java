package restaurant.restaurantecasajulian.controllers.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.data.RatingData;
import restaurant.restaurantecasajulian.data.ReservationData;
import restaurant.restaurantecasajulian.utils.SceneManager;

public class LeaveRating {
    @FXML
    private Label txtDate;
    @FXML
    private Label txtTime;
    @FXML
    private Button star1;
    @FXML
    private Button star2;
    @FXML
    private Button star3;
    @FXML
    private Button star4;
    @FXML
    private Button star5;
    @FXML
    private TextArea ratingComment;

    private final RestaurantManager rm = RestaurantManager.getInstance();

    @FXML
    public void initialize() {
        if (PastReservations.getSelectedReservation() != null) {
            ReservationData reservation = PastReservations.getSelectedReservation();
            txtDate.setText(reservation.getDate());
            txtTime.setText(reservation.getTime());

            RatingData rating = rm.getRating(reservation.username(), reservation.getTimeSlot());

            if (rating != null) {
                ratingComment.setText(rating.message());
                Button[] stars = {star1, star2, star3, star4, star5};
                for (int i = 0; i < rating.rating(); i++) {
                    stars[i].getStyleClass().add("selected");
                }
            }
        }
    }

    @FXML
    private void updateRating(ActionEvent event) {
        if (event.getSource() instanceof Button button) {
            boolean isSelected = button.getStyleClass().contains("selected");
            Button[] stars = {star1, star2, star3, star4, star5};
            boolean foundSelected = false;
            for (Button star : stars) {
                if (star.equals(button)) {
                    foundSelected = true;
                    if (isSelected) {
                        star.getStyleClass().remove("selected");
                    } else {
                        star.getStyleClass().add("selected");
                    }
                } else {
                    if (foundSelected) {
                        star.getStyleClass().remove("selected");
                    } else if (!isSelected) {
                        star.getStyleClass().add("selected");
                    }
                }
            }
        }
    }


    private int getRating() {
        Button[] stars = {star1, star2, star3, star4, star5};
        return (int) java.util.Arrays.stream(stars).filter(star -> star.getStyleClass().contains("selected")).count();
    }

    @FXML
    private void saveRating(ActionEvent event) {
        RatingData rating = new RatingData(rm.getCurrentUser().getUsername(), PastReservations.getSelectedReservation().getTimeSlot(), ratingComment.getText(), getRating());
        rm.updateRating(rating);
        SceneManager.showAlert("Rating saved", "Rating saved successfully", Alert.AlertType.INFORMATION);
        SceneManager.closeWindow(event);

    }
}