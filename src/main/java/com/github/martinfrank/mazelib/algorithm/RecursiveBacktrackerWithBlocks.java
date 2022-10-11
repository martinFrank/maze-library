package com.github.martinfrank.mazelib.algorithm;

import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.mazelib.map.MazeMap;
import com.github.martinfrank.mazelib.map.MazeMapEdge;
import com.github.martinfrank.mazelib.map.MazeMapField;
import com.github.martinfrank.mazelib.map.MazeMapNode;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

import java.util.*;

public class RecursiveBacktrackerWithBlocks<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> extends RecursiveBacktracker<M, F, E, N> {

    public RecursiveBacktrackerWithBlocks(M map) {
        super(map);
    }


    @Override
    void updateFields() {
        for (F field : getMap().getFields()) {
            long amount = 0;
            for (E edge : field.getEdges()) {
                if (edge.getData().getPassage().isOpen()) {
                    amount = amount + 1;
                }
            }
            if (!field.getData().isBlocked()) {
                field.getData().setDeadEnd(amount == 1);
            } else {
                field.getData().setReachable(false);
            }
        }
    }

    @Override
    void withRecursiveBackTracker() {
        Deque<F> stack = new ArrayDeque<>();
        Set<F> closed = new HashSet<>();
        fillBorders(closed);

        int counter = 0;
        F current = getMapAccessor().getRandomStartInBounds(2);
        getCarver().carveInto(current);
        current.getData().setCounter(counter);
        counter++;
        do {
            List<F> nbgs = getCarver().getNeighborsForWallCarving(current, closed);
            if (nbgs.isEmpty()) {
                current = stack.pop();
            } else {
                F next = nbgs.get(0);
                getCarver().carveInto(current, next);
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
        return getMap().getStyle() == MapStyle.SQUARE_DIAMOND4 ||
                getMap().getStyle() == MapStyle.SQUARE_ISOMETRIC4 ||
                getMap().getStyle() == MapStyle.TRIANGLE_VERTICAL;
    }

    private boolean fillAdditionalColumns() {
        return getMap().getStyle() == MapStyle.TRIANGLE_HORIZONTAL;
    }

    private void addToClosed(int x, int y, Set<F> closed) {
        F f = getMap().getField(x, y);
        if (f != null) {
            closed.add(f);
        }
    }

    private void setBlocked(int x, int y) {
        F f = getMap().getField(x, y);
        if (f != null) {
            f.getData().setBlocked(true);
        }
    }

}
