import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Math.min;

public class System_of_Elevators {
    private List<Elevator> elevators = new ArrayList<>();

    public System_of_Elevators(List<Elevator>elevators) {
        this.elevators.addAll(elevators);
    }

    public Elevator choose_best(Call call) {
        int min_dist = Integer.MAX_VALUE; // начинаем с максимального значения
        Elevator best = null;

        for (int i = 0; i < elevators.size(); i++) {
            Elevator elevator = elevators.get(i);
            int currentFloor = elevator.get_currentFloor();
            int distance = Math.abs(currentFloor - call.startFloor);

            // Условие для работающего лифта
            if (elevator.get_running()) {
                // Если лифт движется в сторону вызова и ближе, обновляем best
                if (Math.abs(currentFloor + elevator.get_direction() - call.startFloor) < distance) {
                    if (distance < min_dist) {
                        min_dist = distance;
                        best = elevator;
                    }
                }
            } else { // Если лифт не работает
                if (distance < min_dist) {
                    min_dist = distance;
                    best = elevator;
                }
            }
        }

        // Если все лифты заняты, выбираем первый лифт по умолчанию
        if (best == null) {
            return elevators.getFirst();
        } else {
            return best;
        }
    }
}