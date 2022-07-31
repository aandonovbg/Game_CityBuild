import java.util.List;
import java.util.Scanner;

public class Harvester extends Unit{

    protected String name;
    int harvestSpeed;
    protected boolean isOccupied;
    protected int occupationTimer;
    private static int harvestersCounter=1;

    public Harvester(){
        super.name="Harvester_"+harvestersCounter;
        this.harvestSpeed=5;
        this.isOccupied=false;
        this.occupationTimer=0;
        harvestersCounter++;
    }

    public int getOccupationTimer() {
        return occupationTimer;
    }

    public void setOccupationTimer(int occupationTimer) {
        this.occupationTimer = occupationTimer;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public static int getHarvestersCounter() {
        return harvestersCounter;
    }

    public static int harvestersUsedMenu(List<Builder> harvesters){

        Scanner sc = new Scanner(System.in);
        String harvestersUsed = sc.nextLine();
        try {
            if (Integer.parseInt(harvestersUsed) < 1 || Integer.parseInt(harvestersUsed) > getHarvestersCounter()) {
                System.out.println("Invalid choice! Enter Digit from 1 to " + getHarvestersCounter());
                harvestersUsedMenu(harvesters);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice! Enter Digit from 1 to " + getHarvestersCounter());
            System.out.println();
            harvestersUsedMenu(harvesters);
        }
        return Integer.parseInt(harvestersUsed);
    }

    public static void harvest(String buildingName, List<Harvester> harvesters, List<Builder> builders) {

    }
}
