package de.elite.games.mazelib;

public class MazeGenerationParams {

    private AlgorithmType type;
    ;

    public MazeGenerationParams(AlgorithmType type) {
        this.type = type;
    }

    public boolean isRecursiveBcktrackerWithPassages() {
        return type == AlgorithmType.RECURSIVE_BACKTRACKER_PASSAGES;
    }

    public boolean isRecursiveBcktrackerWithBlocks() {
        return type == AlgorithmType.RECURSIVE_BACKTRACKER_BLOCKS;
    }

    public enum AlgorithmType {RECURSIVE_BACKTRACKER_PASSAGES, RECURSIVE_BACKTRACKER_BLOCKS}
}
