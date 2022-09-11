
import java.util.*;

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
        if (Building.uniqueBuilding(buildingName, buildings)) {
            System.out.println("You can build only 1 " + buildingName + "in your Town");
            return;
        } else {
            if (!isResourceEnough(resources, buildingName)) {
                System.out.println("Not enough Resources!");
            } else {

                switch (freeUnits(builders)) {
                    case 0 -> System.out.println("Currently ALL Builders are occupied.");
                    case 1 -> {
                        System.out.println("Currently you have 1 Builder available");
                        buildings.add(new Building(buildingName, buildingEndurance, buildingConstrTime));
                        payResources(buildingName, resources);
                        int constructionDuration = buildings.get(buildings.size() - 1).constructionTime / builders.get(0).buildSpeed;
                        System.out.println(buildingName + " will be build after " + constructionDuration + " seconds");
                        occupyBuilders(builders, 1, constructionDuration);
                        System.out.println(builders);
                    }
                    default -> {
                        System.out.println("Currently you have " + freeUnits(builders) + " Builders available.");
                        System.out.println("How many do you want to use");
                        buildersUsed(builders);
                        buildings.add(new Building(buildingName, buildingEndurance, buildingConstrTime));
                        payResources(buildingName, resources);
                        int buildersUsed = buildersUsed(builders);
                        int constructionDuration;
                        constructionDuration = buildings.get(buildings.size() - 1).constructionTime / (builders.get(0).buildSpeed * buildersUsed);
                        System.out.println(buildingName + " will be build after " + constructionDuration + " seconds");
                        occupyBuilders(builders, buildersUsed, constructionDuration);
                    }

                }
            }
        }
    }

    public static void payResources(String buildingName, String[][] resources) {
        switch (buildingName) {
            case "Town Hall" -> deductResources(resources, 250, 100, 200, 200);
            case "Barracks" -> deductResources(resources, 100, 50, 100, 120);
            case "Hospital" -> deductResources(resources, 200, 100, 200, 200);
            case "Gold Mine" -> deductResources(resources, 0, 100, 50, 50);
            case "Grain Field" -> deductResources(resources, 50, 0, 50, 50);
            case "Lumber Mill" -> deductResources(resources, 50, 50, 0, 70);
            case "Stone Mine" -> deductResources(resources, 50, 50, 50, 0);
        }
    }

    public static void deductResources(String[][] resources, int gold, int food, int wood, int stone) {
        if (Integer.parseInt(resources[0][1]) - gold >= 0 && Integer.parseInt(resources[1][1]) - food >= 0 && Integer.parseInt(resources[2][1]) - wood >= 0 && Integer.parseInt(resources[3][1]) - stone >= 0) {
            resources[0][1] = String.valueOf(Integer.parseInt(resources[0][1]) - gold);
            resources[1][1] = String.valueOf(Integer.parseInt(resources[1][1]) - food);
            resources[2][1] = String.valueOf(Integer.parseInt(resources[2][1]) - wood);
            resources[3][1] = String.valueOf(Integer.parseInt(resources[3][1]) - stone);

        }
    }

    public static void occupyBuilders(List<Builder> builders, int buildersUsed, int constructionDuration) {
        for (int i = 0; i < buildersUsed; i++) {
            if (!builders.get(i).isOccupied) {
                buildersUsed--;
                builders.get(i).setOccupied(true);

                int finalI = i;
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                builders.get(finalI).isOccupied=false;
                            }
                        },
                        constructionDuration*1000 //how many seconds till isOccupied=false
                );
            }
        }

    }
    public static boolean validateResources(String[][] resources, int gold, int food, int wood, int stone) {
        boolean validateResources = false;
        if (Integer.parseInt(resources[0][1]) - gold >= 0 && Integer.parseInt(resources[1][1]) - food >= 0 && Integer.parseInt(resources[2][1]) - wood >= 0 && Integer.parseInt(resources[3][1]) - stone >= 0) {
            validateResources = true;
        }
        return validateResources;
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

    public static void produceBuilders(List<Builder> builders,List<Building> buildings,List<Harvester>harvesters,String[][] resources){
        Scanner sc=new Scanner(System.in);
        boolean hasTownHall=false;
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).name.equals("Town Hall")) {
                hasTownHall = true;
                break;
            }
        }
        if (hasTownHall) {
            System.out.println("How many Builders you want to produce?");
            System.out.print("Your choice - > ");
            String choice = sc.next();
            try {
                if (Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > 1000000) {
                    System.out.println();
                    System.out.println("Invalid choice! Enter Real Digit");
                    System.out.println();
                    produceBuilders(builders, buildings, harvesters, resources);
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid choice! Enter Real Digit");
                System.out.println();
                produceBuilders(builders, buildings, harvesters, resources);
            }
            builders.add(new Builder());
        }
            else {
                System.out.println("You must first build \"Town Hall\" building.");
                Menu.buildMenu(buildings,builders,harvesters,resources);
            }
        }

    @Override
    public String toString() {
        return "Builder{" +
                "name='" + name + '\'' +
                ",buildSpeed=" + buildSpeed +
                ", isOccupied=" + isOccupied +
                //", occupationTimer=" + occupationTimer +
                '}';
    }
}
