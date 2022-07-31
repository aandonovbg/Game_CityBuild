import java.util.List;
import java.util.Scanner;

public abstract class Unit {
    private static int unitsCounter = 1;
    protected String name;
    protected int attack;
    protected int defence;
    protected int health ;
    protected int moveSpeed;
    protected boolean isOccupied;
    protected int occupationTimer;
    public Unit() {
        this.name = "Unit_" + unitsCounter;
        unitsCounter++;
        this.attack=2;
        this.defence=4;
        this.health=10;
        this.moveSpeed=5;
        this.isOccupied=false;
        this.occupationTimer=0;
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

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defence=" + defence +
                ", health=" + health +
                ", moveSpeed=" + moveSpeed +
                ", isOccupied=" + isOccupied +
                ", occupationTimer=" + occupationTimer +
                '}';
    }
}
