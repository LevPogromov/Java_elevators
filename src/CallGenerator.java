import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class CallGenerator implements Runnable {
    private int callId;
    private System_of_Elevators sys = null;
    private final Random random = new Random();

    public CallGenerator(System_of_Elevators sys) {
        callId = 0;
        this.sys = sys;
    }

    public void run() {
        while (true) {
            int startFloor = random.nextInt(15);
            int endFloor = random.nextInt(15);
            while (startFloor == endFloor) {
                endFloor = random.nextInt(15);
            }
            Call call = new Call(callId++, startFloor, endFloor);

            Elevator best = sys.choose_best(call);
            best.add_call(call);
            try {
                Thread.sleep(random.nextInt(2000) + 1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
