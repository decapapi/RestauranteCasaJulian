package restaurant.restaurantecasajulian.data;

import static restaurant.restaurantecasajulian.utils.CSVDumper.CSV_SEPARATOR;

/**
 * Class that represents the data of a dish
 * @param name
 * @param rations
 */

public record DishData(String name, int rations) {
    /**
     * Method that returns the name of the dish
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns the name of the dish
     * @return String
     */
    public int getRations() {
        return rations;
    }

    /**
     * Method that returns the data of a dish in CSV format
     * @return String
     */
    public String toCSV() {
        return name + CSV_SEPARATOR + rations;
    }
}
