package ar.edu.itba.ss;

import static ar.edu.itba.ss.SystemConfiguration.*;

public class App {
    public static void main(String[] args) {
        // [simulationTime] = s
        double simulationTime = 20;

        for (int i = 1; i <= 80; i++) {
            for (int j = 0; j < 3; j++) {
                Engine.runSimulation(i * 5, MIN_R, MAX_R, L, W, simulationTime);
            }
        }

//        Engine.runWithSystemParameters();
    }
}
