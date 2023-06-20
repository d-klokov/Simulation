package ru.klokov;

import java.util.Scanner;

public class ConsoleInputHandler implements Runnable {
    private final Simulation simulation;

    public ConsoleInputHandler(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (!simulation.getStatus().equals(Status.STOPPED)) {
            String command = scanner.next().toLowerCase();
            switch (command) {
                case "s":
                    simulation.stop();
                    break;
                case "p":
                    if (simulation.getStatus().equals(Status.PAUSED)) {
                        simulation.resumeSimulation();
                    } else if (simulation.getStatus().equals(Status.RUNNING)) {
                        simulation.pauseSimulation();
                    }
                    break;
                case "1":
                    simulation.setSpeed100();
                    break;
                case "2":
                    simulation.setSpeed250();
                    break;
                case "3":
                    simulation.setSpeed500();
                    break;
            }
        }
    }
}
