package de.elite.games.mazelib.algorithm;

import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapPoint;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RecursiveBacktracker<M extends MazeMap<?, F, E, P, ?>, F extends MazeMapField<? extends MazeMapFieldData, F, E, P>, E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, P>, P extends MazeMapPoint<?, F, E, P>> extends AbstractAlgorithm<M, F, E, P> {

    public RecursiveBacktracker(M map) {
        super(map);
    }

    @Override
    public void createPerfectMaze() {
        withRecursiveBackTracker();
        updateFields();
    }

    private void updateFields() {
        for (F field : getMapAccessor().getMap().getFields()) {
            long amount = field.getEdges().stream().filter(e -> e.getData().getPassage().isOpen()).count();
            field.getData().setReachable(amount == 0);
            field.getData().setDeadEnd(amount == 1);
        }
    }

    private void withRecursiveBackTracker() {
        Stack<F> stack = new Stack<>();
        F current = getMapAccessor().getRandomStart();
        Set<F> closed = new HashSet<>();
        closed.add(current);
        do {
            List<F> nbgs = getMapAccessor().getNeighbors(current, closed);
            if (nbgs.isEmpty()) {
                current = stack.pop();
            } else {
                F next = nbgs.get(0);
                getMapAccessor().carve(current, next);
                stack.push(current);
                current = next;
                closed.add(current);
            }
        } while (!stack.isEmpty());
    }

}
