package de.elite.games.mazelib.data;

import de.elite.games.mazelib.mapdata.MazeMapFieldData;

public class TestFieldData extends MazeMapFieldData {

    private boolean isMarkedAsPath;

    public void markAsPath(boolean isMarkedAsPath) {
        this.isMarkedAsPath = isMarkedAsPath;
    }

    public boolean isMarkedAsPath() {
        return isMarkedAsPath;
    }
}
