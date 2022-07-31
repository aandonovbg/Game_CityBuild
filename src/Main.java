import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Building> buildings = new ArrayList<>();
    public static List<Builder> builders = new ArrayList<>();
    public static List<Unit> units = new ArrayList<>();
    public static List<Harvester> harvesters = new ArrayList<>();

    public static String[][] Resources() {
        String[][] resources = new String[4][2];
        resources[0][0] = " Gold";
        resources[0][1] = "  500";
        resources[1][0] = "Food";
        resources[1][1] = "  500";
        resources[2][0] = "Lumber";
        resources[2][1] = "500";
        resources[3][0] = "Stone";
        resources[3][1] = " 500";

        return resources;
    }


    public static void main(String[] args) {
        String[][] resources=Resources();
        builders.add(new Builder());
        Menu.startMenu(buildings, builders,harvesters,resources);


    }

}