package restaurant.restaurantecasajulian.data;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

public record DishData(String name, int rations) {
    public String getName() {
        return name;
    }

    public int getRations() {
        return rations;
    }

    public String toCSV() {
        return name + CSV_SEPARATOR + rations;
    }
}
