package de.elite.games.mazelib.algorithm;

import de.elite.games.geolib.GeoPoint;

public class Direction {

    public static final Direction NW = new Direction(-1, -1, "NW");
    public static final Direction N = new Direction(0, -1, "N");
    public static final Direction NE = new Direction(1, -1, "NE");
    public static final Direction W = new Direction(-1, 0, "W");
    public static final Direction NONE = new Direction(0, 0, "C");
    public static final Direction E = new Direction(1, 0, "E");
    public static final Direction SW = new Direction(-1, 1, "SW");
    public static final Direction S = new Direction(0, 1, "S");
    public static final Direction SE = new Direction(1, 1, "SE");

    public static final Direction[] DIRECTIONS = new Direction[]{NW, N, NE, W, NONE, E, SW, S, SE};

    private final int dx;
    private final int dy;
    private final String literal;

    private Direction(int dx, int dy, String literal) {
        this.dx = dx;
        this.dy = dy;
        this.literal = literal;
    }

    public static Direction get(GeoPoint from, GeoPoint to) {
        int dx = getDxInternal(from, to);
        int dy = getDyInternal(from, to);

        for (Direction dir : DIRECTIONS) {
            if (dir.dx == dx && dir.dy == dy) {
                return dir;
            }
        }

        return Direction.NONE;
    }

    private static int getDxInternal(GeoPoint from, GeoPoint to) {
        return to.getX() - from.getX();
    }

    private static int getDyInternal(GeoPoint from, GeoPoint to) {
        return to.getY() - from.getY();
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    @Override
    public String toString() {
        return literal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        if (dx != direction.dx) return false;
        return dy == direction.dy;
    }

    @Override
    public int hashCode() {
        int result = dx;
        result = 31 * result + dy;
        return result;
    }
}
