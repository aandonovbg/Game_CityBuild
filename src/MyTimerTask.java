import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    protected int buildersUsed;

    public MyTimerTask(int buildersUsed) {
        this.buildersUsed = buildersUsed;
    }

    @Override
    public void run() {

    }
}
