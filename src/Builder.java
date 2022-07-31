import java.util.List;
import java.util.Scanner;

public class Builder extends Unit {
    private static int buildersCounter = 1;
    private final int buildSpeed;
    protected boolean isOccupied;
    protected int occupationTimer;

    public Builder() {
        super.name = "Builder_" + buildersCounter;
        this.buildSpeed = super.moveSpeed;
        buildersCounter++;
        this.isOccupied = false;
        this.occupationTimer = 0;
    }

    public static int getBuildersCounter() {
        return buildersCounter;
    }

    public static int freeUnits(List<Builder> builders) {
        int freeUnits = 0;
        for (int i = 0; i < builders.size(); i++) {
            if (!builders.get(i).isOccupied) {
                freeUnits++;
            }
        }
        return freeUnits;
    }

    public static int buildersUsed(List<Builder> builders) {

        Scanner sc = new Scanner(System.in);
        String unitsUsed = sc.nextLine();
        try {
            if (Integer.parseInt(unitsUsed) < 1 || Integer.parseInt(unitsUsed) > freeUnits(builders)) {
                System.out.println("Invalid choice! Enter Digit from 1 to " + freeUnits(builders));
                buildersUsed(builders);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice! Enter Digit from 1 to " + freeUnits(builders));
            System.out.println();
            buildersUsed(builders);
        }
        return Integer.parseInt(unitsUsed);
    }

    public static void build(String buildingName, int buildingEndurance, int buildingConstrTime, List<Building> buildings, List<Builder> builders, String[][] resources) {
        if (!isResourceEnough(resources, buildingName)) {
            System.out.println("Not enough Resources!");
        } else {
            if (Building.uniqueBuilding(buildingName, buildings)) {
                System.out.println("You can build only 1 " + buildingName + "in your Town");
                return;
            } else {
                if (freeUnits(builders) > 1) {
                    System.out.println("Currently you have " + freeUnits(builders) + " Builders available.");
                    System.out.println("How many do you want to use");
                    buildersUsed(builders);
                    buildings.add(new Building(buildingName, buildingEndurance, buildingConstrTime));
                    int buildersUsed = buildersUsed(builders);
                    int constructionDuration;
                    if (buildings.size() == 1) {
                        constructionDuration = buildings.get(0).constructionTime / (builders.get(0).buildSpeed * buildersUsed);
                    } else {
                        constructionDuration = buildings.get(buildings.size() - 1).constructionTime / (builders.get(0).buildSpeed * buildersUsed);
                    }
                    System.out.println(buildingName + " will be build after " + constructionDuration + " seconds");
                } else {
                    System.out.println("Currently you have 1 Builder available");
                    buildings.add(new Building(buildingName, buildingEndurance, buildingConstrTime));
                    int constructionDuration = buildings.get(buildings.size() - 1).constructionTime / builders.get(0).buildSpeed;
                    System.out.println(buildingName + " will be build after " + constructionDuration + " seconds");

                }
            }
        }
    }

    public static boolean validateResources(String[][] resources, int gold, int food, int wood, int stone) {
        boolean isResEnough = false;
        if (Integer.parseInt(resources[0][1]) - gold >= 0 && Integer.parseInt(resources[1][1]) - food >= 0 && Integer.parseInt(resources[2][1]) - wood >= 0 && Integer.parseInt(resources[3][1]) - stone >= 0) {
            resources[0][1] = String.valueOf(Integer.parseInt(resources[0][1]) - gold);
            resources[1][1] = String.valueOf(Integer.parseInt(resources[1][1]) - food);
            resources[2][1] = String.valueOf(Integer.parseInt(resources[2][1]) - wood);
            resources[3][1] = String.valueOf(Integer.parseInt(resources[3][1]) - stone);
            isResEnough = true;
        }
        return isResEnough;
    }

    public static boolean isResourceEnough(String[][] resources, String buildingName) {
        boolean isResourcesEnough = false;
        switch (buildingName) {
            case "Town Hall" -> isResourcesEnough = validateResources(resources, 250, 100, 200, 200);
            case "Barracks" -> isResourcesEnough = validateResources(resources, 100, 50, 100, 120);
            case "Hospital" -> isResourcesEnough = validateResources(resources, 200, 100, 200, 200);
            case "Gold Mine" -> isResourcesEnough = validateResources(resources, 0, 100, 50, 50);
            case "Grain Field" -> isResourcesEnough = validateResources(resources, 50, 0, 50, 50);
            case "Lumber Mill" -> isResourcesEnough = validateResources(resources, 50, 50, 0, 70);
            case "Stone Mine" -> isResourcesEnough = validateResources(resources, 50, 50, 50, 0);
        }
        return isResourcesEnough;
    }

    public int getOccupationTimer() {
        return occupationTimer;
    }

    public void setOccupationTimer(int occupationTimer) {
        this.occupationTimer = occupationTimer;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "name='" + name + '\'' +
                ",buildSpeed=" + buildSpeed +
                ", isOccupied=" + isOccupied +
                ", occupationTimer=" + occupationTimer +
                '}';
    }
}
