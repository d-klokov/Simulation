package ru.klokov;

import java.util.Set;

public class ConsoleRenderer {
    private final Simulation simulation;

    public ConsoleRenderer(Simulation simulation) {
        this.simulation = simulation;
    }

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public void render(Map map) {
        Set<Position> positions = map.getEntitiesPositions();

        System.out.print("\033\143");
        System.out.println(getTopAndBottomBorders());
        for (int j = 0; j < map.getHeight(); j++) {
            StringBuilder line = new StringBuilder();
            line.append(ANSI_BLACK_BACKGROUND).append("  ").append(ANSI_RESET);
            for (int i = 0; i < map.getWidth(); i++) {
                Position position = new Position(i, j);
                if (positions.contains(position)) {
                    line.append(getEntityView(map.getEntityByPosition(position).getColor()));
                } else {
                    line.append(ANSI_WHITE_BACKGROUND + "   ");
                }
            }
            line.append(ANSI_BLACK_BACKGROUND).append("  ").append(ANSI_RESET);
            line.append(ANSI_RESET).append(printInfo(j)).append(ANSI_RESET);
            System.out.println(line);
        }
        System.out.println(getTopAndBottomBorders());
        System.out.println("\n  Enter command:");
    }

    private String printInfo(int index) {
        StringBuilder info = new StringBuilder();
        switch (index) {
            case 0:
                info.append(ANSI_GREEN).append("   Simulation status: ").append(ANSI_RED).append(printStatus(simulation.getStatus()));
                break;
            case 1:
                info.append(ANSI_GREEN).append("   Turns counter: ").append(ANSI_RED).append(simulation.getTurnCounter());
                break;
            case 2:
                info.append(ANSI_GREEN).append("   Grass quantity: ").append(ANSI_RED).append(simulation.getMap().getGrassQuantity());
                break;
            case 3:
                info.append(ANSI_GREEN).append("   Herbivores quantity: ").append(ANSI_RED).append(simulation.getMap().getHerbivoresQuantity());
                break;
            case 5:
                info.append(ANSI_GREEN).append("   Commands:");
                break;
            case 6:
                info.append(ANSI_GREEN).append("   P - pause/resume");
                break;
            case 7:
                info.append(ANSI_GREEN).append("   S - stop");
                break;
            case 8:
                info.append(ANSI_GREEN).append("   Change speed - 1/2/3");
                break;
        }

        return info.toString();
    }

    private String printStatus(Status status) {
        StringBuilder statusText = new StringBuilder();
        switch (status) {
            case STOPPED:
                statusText.append("STOP");
                break;
            case PAUSED:
                statusText.append("PAUSE");
                break;
            case RUNNING:
                statusText.append("RUN");
                break;
        }
        return statusText.toString();
    }

    private String getEntityView(Color color) {
        StringBuilder view = new StringBuilder();
        switch (color) {
            case GREEN:
                view.append(ANSI_GREEN_BACKGROUND).append(" G ");
                break;
            case BLACK:
                view.append(ANSI_BLACK_BACKGROUND).append(" R ");
                break;
            case CYAN:
                view.append(ANSI_CYAN_BACKGROUND).append(" T ");
                break;
            case PURPLE:
                view.append(ANSI_PURPLE_BACKGROUND).append(" H ");
                break;
            case RED:
                view.append(ANSI_RED_BACKGROUND).append(" P ");
                break;
        }

        return view.toString();
    }

    private String getTopAndBottomBorders() {
        return ANSI_BLACK_BACKGROUND + " ".repeat(simulation.getMap().getWidth() * 3 + 4) + ANSI_RESET;
    }
}
