package restaurant.restaurantecasajulian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import restaurant.restaurantecasajulian.RestaurantManager.RestaurantManager;

import java.io.IOException;

public class RestauranteCasaJulian extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RestauranteCasaJulian.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Restaurante Casa Julian");
        stage.setScene(scene);
        stage.resizableProperty().setValue(Boolean.FALSE);
        RestaurantManager.loadData();
        stage.show();
        stage.setOnHiding(e -> RestaurantManager.saveData());
    }

    public static void main(String[] args) {
        launch();
    }
}