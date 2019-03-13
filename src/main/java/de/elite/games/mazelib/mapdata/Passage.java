package de.elite.games.mazelib.mapdata;

public class Passage {

    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isClosed() {
        return !open;
    }
}
