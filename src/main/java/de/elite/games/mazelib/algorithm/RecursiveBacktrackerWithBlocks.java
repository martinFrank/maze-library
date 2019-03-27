package de.elite.games.mazelib.algorithm;

import de.elite.games.maplib.MapStyle;
import de.elite.games.mazelib.map.MazeMap;
import de.elite.games.mazelib.map.MazeMapEdge;
import de.elite.games.mazelib.map.MazeMapField;
import de.elite.games.mazelib.map.MazeMapNode;
import de.elite.games.mazelib.mapdata.MazeMapEdgeData;
import de.elite.games.mazelib.mapdata.MazeMapFieldData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RecursiveBacktrackerWithBlocks<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> extends AbstractAlgorithm<M, F, E, N> {

    public RecursiveBacktrackerWithBlocks(M map) {
        super(map);
    }

    @Override
    public void createPerfectMaze() {
        withRecursiveBackTracker();
        updateFields();
    }

    private void updateFields() {
        for (F field : getMap().getFields()) {
            long amount = field.getEdges().stream().filter(e -> e.getData().getPassage().isOpen()).count();
            field.getData().setReachable(amount == 0);
            if (!field.getData().isBlocked()) {
                field.getData().setDeadEnd(amount == 1);
            }
        }
    }

    private void withRecursiveBackTracker() {
        Stack<F> stack = new Stack<>();
        Set<F> closed = new HashSet<>();
        fillBorders(closed);

        int counter = 0;
        F current = getMapAccessor().getRandomStartInBounds(2);
        getMapAccessor().carveInto(current, current);
        current.getData().setCounter(counter);
        counter++;
        do {
            List<F> nbgs = getMapAccessor().getNeighborsForWallCarving(current, closed);
            if (nbgs.isEmpty()) {
                current = stack.pop();
            } else {
                F next = nbgs.get(0);
                getMapAccessor().carveInto(current, next);
                stack.push(current);
                current = next;

                current.getData().setCounter(counter);
                counter++;

                closed.add(current);
            }
        } while (!stack.isEmpty());
    }

    private void fillBorders(Set<F> closed) {
        for (int dx = 0; dx < getMap().getColumns(); dx++) {
            addToClosed(dx, 0, closed);
            addToClosed(dx, getMap().getRows() - 1, closed);
            if (fillAdditionalRows()) {
                addToClosed(dx, 1, closed);
                addToClosed(dx, getMap().getRows() - 2, closed);
            }
        }
        for (int dy = 0; dy < getMap().getRows(); dy++) {
            addToClosed(0, dy, closed);
            addToClosed(getMap().getColumns() - 1, dy, closed);
            if (fillAdditionalColumns()) {
                addToClosed(1, dy, closed);
                addToClosed(getMap().getColumns() - 2, dy, closed);
            }
        }
        for (int dx = 0; dx < getMap().getColumns(); dx++) {
            for (int dy = 0; dy < getMap().getRows(); dy++) {
                setBlocked(dx, dy);
            }
        }
    }

    private boolean fillAdditionalRows() {
        return getMap().getStyle() == MapStyle.SQUARE_DIAMOND ||
                getMap().getStyle() == MapStyle.SQUARE_ISOMETRIC ||
                getMap().getStyle() == MapStyle.TRIANGLE_VERTICAL;
    }

    private boolean fillAdditionalColumns() {
        return getMap().getStyle() == MapStyle.TRIANGLE_HORIZONTAL;
    }

    private void addToClosed(int x, int y, Set<F> closed) {
        getMap().getField(x, y).ifPresent(closed::add);
    }

    private void setBlocked(int x, int y) {
        getMap().getField(x, y).ifPresent(f -> f.getData().setBlocked(true));
    }

}
