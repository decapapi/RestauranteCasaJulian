module restaurant.restaurantecasajulian {
    requires javafx.controls;
    requires javafx.fxml;

    opens restaurant.restaurantecasajulian to javafx.fxml;
    opens restaurant.restaurantecasajulian.RestaurantManager to javafx.fxml;
    opens restaurant.restaurantecasajulian.data to javafx.base;
    opens restaurant.restaurantecasajulian.data.types to javafx.base;
    opens restaurant.restaurantecasajulian.model to javafx.base;
    opens restaurant.restaurantecasajulian.model.users to javafx.base;
    opens restaurant.restaurantecasajulian.utils to javafx.base;

    exports restaurant.restaurantecasajulian;
    exports restaurant.restaurantecasajulian.controllers.user;
    opens restaurant.restaurantecasajulian.controllers.user to javafx.fxml;
    exports restaurant.restaurantecasajulian.controllers.employee;
    opens restaurant.restaurantecasajulian.controllers.employee to javafx.fxml;
    exports restaurant.restaurantecasajulian.controllers.admin;
    opens restaurant.restaurantecasajulian.controllers.admin to javafx.fxml;
}