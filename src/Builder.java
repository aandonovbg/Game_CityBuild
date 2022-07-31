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

    public static void build(String buildingName, int buildingEndurance, int buildingConstrTime, List<Building> buildings, List<Builder> builders,String[][] resources) {
        if (!isResourcesEnough(resources, buildingName)){
            System.out.println("Not enough Resources!");
        }else {
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

    public static boolean isResourcesEnough(String[][] resources, String buildingName) {
        boolean isResourcesEnough = false;
        switch (buildingName) {
            case "Town Hall" -> {
                if (Integer.parseInt(resources[0][1]) - 250 >= 0 && Integer.parseInt(resources[1][1]) - 100 >= 0 && Integer.parseInt(resources[2][1]) - 200 >= 0 && Integer.parseInt(resources[3][1]) - 200 >= 0) {
                     isResourcesEnough=true;
                }
            }
            case "Barracks" -> {
                if (Integer.parseInt(resources[0][1]) - 100 >= 0 && Integer.parseInt(resources[1][1]) - 50 >= 0 && Integer.parseInt(resources[2][1]) - 100 >= 0 && Integer.parseInt(resources[3][1]) - 120 >= 0) {
                    isResourcesEnough=true;
                }
            }
            case "Hospital" -> {
                if (Integer.parseInt(resources[0][1]) - 200 >= 0 && Integer.parseInt(resources[1][1]) - 100 >= 0 && Integer.parseInt(resources[2][1]) - 200 >= 0 && Integer.parseInt(resources[3][1]) - 200 >= 0) {
                    isResourcesEnough=true;
                }
            }
            case "Gold Mine" -> {
                if (Integer.parseInt(resources[0][1]) >= 0 && Integer.parseInt(resources[1][1]) - 100 >= 0 && Integer.parseInt(resources[2][1]) - 50 >= 0 && Integer.parseInt(resources[3][1]) - 50 >= 0) {
                    isResourcesEnough=true;
                }
            }
            case "Grain Field" -> {
                if (Integer.parseInt(resources[0][1]) >= 50 && Integer.parseInt(resources[1][1]) >= 0 && Integer.parseInt(resources[2][1]) - 50 >= 0 && Integer.parseInt(resources[3][1]) - 50 >= 0) {
                    isResourcesEnough=true;
                }
            }
            case "Lumber Mill" -> {
                if (Integer.parseInt(resources[0][1]) >= 50 && Integer.parseInt(resources[1][1]) - 50 >= 0 && Integer.parseInt(resources[2][1]) >= 0 && Integer.parseInt(resources[3][1]) - 70 >= 0) {
                    isResourcesEnough=true;
                }
            }
            case "Stone Mine" -> {
                if (Integer.parseInt(resources[0][1]) >= 50 && Integer.parseInt(resources[1][1]) - 50 >= 0 && Integer.parseInt(resources[2][1]) >= 50 && Integer.parseInt(resources[3][1]) >= 0) {
                    isResourcesEnough=true;
                }
            }
        }
        return isResourcesEnough;
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
