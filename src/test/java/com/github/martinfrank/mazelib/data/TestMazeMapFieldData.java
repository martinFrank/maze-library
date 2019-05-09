package com.github.martinfrank.mazelib.data;

import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

public class TestMazeMapFieldData extends MazeMapFieldData {

    private boolean isMarkedAsPath;

    public void markAsPath(boolean isMarkedAsPath) {
        this.isMarkedAsPath = isMarkedAsPath;
    }

    public boolean isMarkedAsPath() {
        return isMarkedAsPath;
    }
}
