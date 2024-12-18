import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Elevator elevator1 = new Elevator(1);
        Elevator elevator2 = new Elevator(2);
        Elevator elevator3 = new Elevator(3);
        System_of_Elevators system_of_elevators = new System_of_Elevators(Arrays.asList(elevator1,elevator2,elevator3));
        Thread elevatorThread1 = new Thread(elevator1);
        Thread elevatorThread2 = new Thread(elevator2);
        Thread elevatorThread3 = new Thread(elevator3);
        Thread callGeneratorThread = new Thread(new CallGenerator(system_of_elevators));

        elevatorThread1.start();
        elevatorThread2.start();
        elevatorThread3.start();
        callGeneratorThread.start();
    }
}