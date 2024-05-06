package restaurant.restaurantecasajulian.data;

public record DishData(String name, int rations) {
    public String getName() {
        return name;
    }

    public int getRations() {
        return rations;
    }
}
