package de.elite.games.mazelib.mapdata;

public class MazeMapFieldData {

    private boolean isDeadEnd;
    private boolean isReachable;
    private boolean isBlocked;
    private int counter;

    /**
     * @return true if there is only one passage leading into that field
     */
    public boolean isDeadEnd() {
        return isDeadEnd;
    }

    public void setDeadEnd(boolean isDeadEnd) {
        this.isDeadEnd = isDeadEnd;
    }

    /**
     * @return true if you can enter the field through a passage
     */
    public boolean isReachable() {
        return isReachable;
    }

    public void setReachable(boolean isReachable) {
        this.isReachable = isReachable;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
