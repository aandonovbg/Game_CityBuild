import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void validateChoice(String choice,List<Building> buildings, List<Builder> builders, List<Harvester> harvesters,String[][]resources){
        try {
            if (Integer.parseInt(choice) < 0 || Integer.parseInt(choice) > 4) {
                System.out.println();
                System.out.println("Invalid choice! Enter Digit from 0 to 4");
                System.out.println();
                startMenu(buildings, builders, harvesters,resources);
            }
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Invalid choice! Enter Digit from 0 to 4");
            System.out.println();
            startMenu(buildings, builders, harvesters,resources);
        }
    }
    public static void startMenu(List<Building> buildings, List<Builder> builders, List<Harvester> harvesters,String[][]resources) {
        System.out.println("        MAIN MENU");
        System.out.println("Please choose what to do");
        System.out.println("1 - > Build;");
        System.out.println("2 - > Attack;");
        System.out.println("3 - > List of Buildings;");
        System.out.println("4 - > Show Resources;");
        System.out.println("0 - > EXIT;");
        System.out.print("Your choice - > ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        validateChoice(choice,buildings, builders,harvesters,resources);

        switch (Integer.parseInt(choice)) {
            case 1 -> buildMenu(buildings, builders,harvesters,resources);
            //case 2 -> attackMenu();
            case 3 -> listBuildings(buildings, builders,harvesters,resources);
            case 4 -> showResourcesMainMenu(buildings, builders,harvesters,resources);
        }
    }

    public static void showResourcesMainMenu(List<Building> buildings, List<Builder> builders, List<Harvester> harvesters,String[][] resources){
        System.out.println();
        System.out.println("Your current resource's status: ");
        System.out.println(Arrays.deepToString(resources).replace("[[","").replace("], ","\n")
                .replace("[","").replace(",","").replace("]]",""));
        System.out.println();
        startMenu(buildings,builders,harvesters,resources);
    }

    public static void showResources(List<Building> buildings, List<Builder> builders, List<Harvester> harvesters,String[][] resources){
        String[][] myResources=new String[2][4];
        myResources[0][0]=" Gold";
        myResources[0][1]="Food";
        myResources[0][2]="Wood";
        myResources[0][3]="Stone";

        myResources[1][0]=resources[0][1];
        myResources[1][1]=" "+resources[1][1];
        myResources[1][2]=" "+resources[2][1];
        myResources[1][3]=" "+resources[3][1];

        System.out.println();
        System.out.println("Your current resource's status: ");
        System.out.println(Arrays.deepToString(myResources).replace("[[","").replace("],","\n")
                .replace("[","").replace(",","").replace("]]",""));
        System.out.println();

    }

    public static void listBuildings(List<Building> buildings, List<Builder> builders,List<Harvester> harvesters,String[][]resources) {
        if (buildings.size() == 0) {
            System.out.println("List is still empty.\nBuild something!!!");
            buildMenu(buildings, builders,harvesters,resources);
        } else {
            int barracksCount = 0;
            for (int i = 0; i < buildings.size(); i++) {
                if (buildings.get(i).name.equals("Barracks")) {
                    barracksCount++;
                }
            }
            if (barracksCount > 1){
                for (int i = 0; i <buildings.size() ; i++) {
                    if (!buildings.get(i).name.equals("Barracks")) {
                        System.out.println(buildings.get(i).name);
                    }
                }
                System.out.println(barracksCount + " Barracks");
            }else {
                for (int i = 0; i < buildings.size(); i++) {
                    System.out.println(buildings.get(i).name);
                }
            }
        }
        startMenu(buildings, builders, harvesters,resources);
    }


    public static void buildMenu(List<Building> buildings, List<Builder> builders,List<Harvester> harvesters,String[][] resources) {
        System.out.println();
        System.out.println("                        BUILD MENU");
        buildMenuStyle();

        showResources(buildings, builders,harvesters,resources);
        System.out.print("Your choice - > ");
        Scanner sc = new Scanner(System.in);
        String choice1 = sc.next();
        validateChoice(choice1,buildings, builders,harvesters,resources);

        switch (Integer.parseInt(choice1)) {
            case 1 -> {
                Builder.build("Town Hall", 1000, 1000, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 2 -> {
                Builder.build("Barracks", 500, 500, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 3 -> {
                Builder.build("Hospital", 700, 700, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 4 -> {
                Builder.build("Gold Mine", 200, 300, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 5 -> {
                Builder.build("Grain Field", 100, 150, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 6 -> {
                Builder.build("Lumber Mill", 100, 150, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 7 -> {
                Builder.build("Stone Mine", 100, 150, buildings, builders,resources);
                buildMenu(buildings, builders,harvesters,resources);
            }
            case 0 -> startMenu(buildings, builders, harvesters,resources);
        }
        showResources(buildings, builders,harvesters,resources);
    }

    public static void buildMenuStyle(){
        String [][] buildMenu= new String[9][3];
        buildMenu[0][0]=" N: ";
        buildMenu[1][0]="1 - >";
        buildMenu[2][0]="2 - >";
        buildMenu[3][0]="3 - >";
        buildMenu[4][0]="4 - >";
        buildMenu[5][0]="5 - >";
        buildMenu[6][0]="6 - >";
        buildMenu[7][0]="7 - >";
        buildMenu[8][0]="0 - >";
        buildMenu[0][1]="          Building                   ";
        buildMenu[1][1]="Town Hall - Produce Builders.";
        buildMenu[2][1]="Barracks - Produce/ Upgrade Soldiers.";
        buildMenu[3][1]="Hospital - Heal Soldiers.";
        buildMenu[4][1]="Gold Mine - Harvest Gold.+1 per/sec";
        buildMenu[5][1]="Grain Field - Harvest Food.+1 per/sec";
        buildMenu[6][1]="Lumber Mill - Harvest Wood.+1 per/sec";
        buildMenu[7][1]="Stone Mine - Harvest Stone.+1 per/sec";
        buildMenu[8][1]="Back.";
        buildMenu[0][2]="       Price G/F/W/S ";
        buildMenu[1][2]="            250/100/200/200";
        buildMenu[2][2]="    100/50/100/120";
        buildMenu[3][2]="                200/100/200/200";
        buildMenu[4][2]="      0/100/50/50";
        buildMenu[5][2]="    50/0/50/50";
        buildMenu[6][2]="    50/50/0/70";
        buildMenu[7][2]="    50/50/50/0";
        buildMenu[8][2]="";

        System.out.println(Arrays.deepToString(buildMenu).replace("[[","").replace("],","\n")
                .replace("[","").replace(",","").replace("]]",""));
    }

}
