package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapPoint;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RecursiveBacktracker<M extends MazeMap<?, F, E, P, ?>, F extends MazeMapField<?, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>> implements PerfectMazeAlgorithm<M, F, E, P> {

    private final M map;
    private final MapAccessor<M, F, E, P> mapAccessor;

    public RecursiveBacktracker(M map) {
        this.map = map;
        mapAccessor = new MapAccessor<>(map);
    }

    @Override
    public void createPerfectMaze() {
        Stack<F> stack = new Stack<>();
        F current = mapAccessor.getRandomStart(map);
        Set<F> closed = new HashSet<>();
        closed.add(current);
        do {
            List<F> nbgs = mapAccessor.getNeighbors(current, closed);
            if (nbgs.isEmpty()) {
                current = stack.pop();
            } else {
                F next = nbgs.get(0);
                mapAccessor.carve(current, next);
                stack.push(current);
                current = next;
                closed.add(current);
            }
        } while (!stack.isEmpty());

    }

}
