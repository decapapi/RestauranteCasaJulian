package restaurant.restaurantecasajulian.utils;

import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestauranteCasaJulian;

import java.io.IOException;
import java.util.Arrays;

public class SceneManager {

    public static void loadScreen(String viewPath, Event event){
        loadScreen(viewPath, (Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    public static void loadScreen(String viewPath, Stage stage) {
        try {
            Parent root = FXMLLoader.load(RestauranteCasaJulian.class.getResource(viewPath));
            Scene viewScene = new Scene(root);
            stage.setScene(viewScene);
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error loading screen");
            alert.setContentText("Error occurred loading the screen: " + e.getMessage());
            System.out.println(e.getMessage());
            for(StackTraceElement s: e.getStackTrace()){
                System.out.println(s);
            }
            alert.showAndWait();
        }
    }

    public static Stage loadModal(String viewPath, ActionEvent actionEvent) {
        try {
            Parent view = FXMLLoader.load(RestauranteCasaJulian.class.getResource(viewPath));
            Scene viewScene = new Scene(view);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Stage secondaryStage = new Stage();
            secondaryStage.setScene(viewScene);
            secondaryStage.initModality(Modality.WINDOW_MODAL);
            secondaryStage.initOwner(stage);
            stage.setOpacity(0.9);
            secondaryStage.show();
            secondaryStage.setOnHiding(e -> stage.setOpacity(1.0));

            return secondaryStage;  // return the Stage
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error loading screen");
            alert.setContentText("An error occurred while loading the screen: " + e.getMessage());
            alert.showAndWait();
            return null;
        }
    }

    public static void closeWindow(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
