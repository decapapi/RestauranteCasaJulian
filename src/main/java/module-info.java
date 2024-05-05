module restaurant.restaurantecasajulian {
    requires javafx.controls;
    requires javafx.fxml;

    opens restaurant.restaurantecasajulian to javafx.fxml;
    exports restaurant.restaurantecasajulian;
}