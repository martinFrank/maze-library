package de.elite.games.mazelib.map;

import de.elite.games.maplib.MapFactory;
import de.elite.games.maplib.MapPartFactory;

public class TestMapFactory extends MapFactory<TestMap, TestMapField, TestMapEdge, TestMapPoint, TestMapWalker> {

    public TestMapFactory(MapPartFactory<TestMap, TestMapField, TestMapEdge, TestMapPoint, TestMapWalker> mapPartFactory) {
        super(mapPartFactory);
    }

}
