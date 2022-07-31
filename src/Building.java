import java.util.List;

public class Building {
    String name;
    int endurance;
    int constructionTime;

    public Building(String name, int endurance, int constructionTime) {
        this.name = name;
        this.endurance = endurance;
        this.constructionTime = constructionTime;
    }

    public static boolean uniqueBuilding(String buildingName, List<Building> buildings) {
        boolean isUnique = false;

        if (buildingName.equalsIgnoreCase("Hospital")||buildingName.equalsIgnoreCase("Town Hall")){
            for (int i = 0; i < buildings.size(); i++) {
                if (buildingName.equalsIgnoreCase(buildings.get(i).name)){
                    isUnique = true;
                }
            }
        }

        return isUnique;
    }
}
