package de.elite.games.mazelib.maze;

public class Direction {

    public static final Direction NORTH = new Direction(0, -1, "N");

    private final int dx;
    private final int dy;
    private final String literal;

    private Direction(int dx, int dy, String literal) {
        this.dx = dx;
        this.dy = dy;
        this.literal = literal;
    }
}
