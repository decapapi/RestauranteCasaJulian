package restaurant.restaurantecasajulian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;
import restaurant.restaurantecasajulian.utils.CSVDumper;
import restaurant.restaurantecasajulian.utils.CSVParser;

import java.io.IOException;

public class RestauranteCasaJulian extends Application {
    /**
     * Starts the application
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteCasaJulian.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Restaurante Casa Julian");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        CSVParser.loadData();
        stage.show();
        stage.setOnHiding(e -> CSVDumper.dumpData());
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        launch();
    }
}