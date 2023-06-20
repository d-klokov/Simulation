package ru.klokov;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        Thread thread = new Thread(new ConsoleInputHandler(simulation));
        thread.start();

        simulation.startSimulation();
    }
}