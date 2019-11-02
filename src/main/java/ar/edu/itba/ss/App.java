package ar.edu.itba.ss;

import static ar.edu.itba.ss.SystemConfiguration.*;

public class App {
    public static void main(String[] args) {
        // [simulationTime] = s
        double simulationTime = 10;

        for (int i = 1; i <= 60; i++) {
            for (int j = 0; j < 3; j++) {
                Engine.runSimulation(i * 10, MIN_R, MAX_R, L, W, simulationTime);
            }
        }

//        Engine.runWithSystemParameters();
    }
}
