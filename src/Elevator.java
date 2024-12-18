import java.util.ArrayList;
import java.util.List;

public class Elevator implements Runnable {
    private final int id;
    private int currentFloor = 0;
    private List<Call> calls = new ArrayList<>();
    private boolean running = false;
    private int direction;

    public Elevator(int id) {
        this.id = id;
        this.direction = 0;
    }
    public int get_currentFloor() {
        return currentFloor;
    }

    public void run() {
        while (true) {
            if (calls.size() > 0) {
                Call call = calls.removeFirst();
                move(call);
            }
            else {
                try {
                    Thread.sleep((int)Math.random() * 1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public synchronized void add_call(Call call) {
        calls.add(call);
    }
    public boolean get_running(){
        return running;
    }
    public int get_direction() {
        return direction;
    }
    private void move(Call call) {
        System.out.println("Лифт " + id + " получил вызов: " + call);
        if (call.startFloor > currentFloor) {
            direction = 1;
        }
        if (call.startFloor < currentFloor) {
            direction = -1;
        }
        running = true;
        if (currentFloor != call.startFloor) {
            while (call.startFloor != currentFloor) {
                currentFloor += direction;
                System.out.println("Лифт " + id + " на этаже " + currentFloor);
                try {
                    Thread.sleep((int)Math.random() * 1000);
                } catch (InterruptedException e) {
                    running = false;
                    break;
                }
            }
        }
        if (currentFloor > call.endFloor) {
            direction = -1;
        }
        if (currentFloor < call.endFloor) {
            direction = 1;
        }
        while (currentFloor != call.endFloor) {
            currentFloor += direction;
            System.out.println("Лифт " + id + " на этаже " + currentFloor);
            try {
                Thread.sleep((int)Math.random() * 1000);
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
        running = false;
    }

    public void stop() {
        running = false;
    }
}
