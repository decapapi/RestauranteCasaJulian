module restaurant.restaurantecasajulian {
    requires javafx.controls;
    requires javafx.fxml;

    opens restaurant.restaurantecasajulian to javafx.fxml;
    opens restaurant.restaurantecasajulian.RestaurantManager to javafx.fxml;
    opens restaurant.restaurantecasajulian.data to javafx.base;
    opens restaurant.restaurantecasajulian.data.types to javafx.base;
    //opens restaurant.restaurantecasajulian.model to javafx.base;
    opens restaurant.restaurantecasajulian.model.users to javafx.base;
    opens restaurant.restaurantecasajulian.utils to javafx.base;

    exports restaurant.restaurantecasajulian;
}