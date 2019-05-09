package com.github.martinfrank.mazelib.algorithm;

import com.github.martinfrank.mazelib.map.MazeMap;
import com.github.martinfrank.mazelib.map.MazeMapEdge;
import com.github.martinfrank.mazelib.map.MazeMapField;
import com.github.martinfrank.mazelib.map.MazeMapNode;
import com.github.martinfrank.mazelib.mapdata.MazeMapEdgeData;
import com.github.martinfrank.mazelib.mapdata.MazeMapFieldData;

import java.util.*;

public class RecursiveBacktrackerWithPassages<M extends MazeMap<?, F, E, N, ?>,
        F extends MazeMapField<? extends MazeMapFieldData, F, E, N>,
        E extends MazeMapEdge<? extends MazeMapEdgeData, F, E, N>,
        N extends MazeMapNode<?, F, E, N>> extends AbstractAlgorithm<M, F, E, N> {

    public RecursiveBacktrackerWithPassages(M map) {
        super(map);
    }

    @Override
    public void createPerfectMaze() {
        withRecursiveBackTracker();
        updateFields();
    }

    private void updateFields() {
        for (F field : getMap().getFields()) {
            long amount = 0;
            for (E edge : field.getEdges()) {
                if (edge.getData().getPassage().isOpen()) {
                    amount = amount + 1;
                }
            }
            field.getData().setReachable(amount == 0);
            field.getData().setDeadEnd(amount == 1);
        }
    }

    private void withRecursiveBackTracker() {
//        Stack<F> stack = new Stack<>();
        Deque<F> stack = new ArrayDeque<F>();
        F current = getMapAccessor().getRandomStart();
        Set<F> closed = new HashSet<>();
        closed.add(current);
        int counter = 0;
        current.getData().setCounter(counter);
        counter++;

        do {
            List<F> nbgs = getCarver().getNeighborsForPassageCarving(current, closed);
            if (nbgs.isEmpty()) {
                current = stack.pop();
            } else {
                F next = nbgs.get(0);
                getCarver().carvePassage(current, next);
                stack.push(current);
                current = next;

                current.getData().setCounter(counter);
                counter++;

                closed.add(current);
            }
        } while (!stack.isEmpty());
    }

}
