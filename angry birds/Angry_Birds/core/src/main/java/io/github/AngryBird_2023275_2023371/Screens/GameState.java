package io.github.AngryBird_2023275_2023371.Screens;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentLevel;
    private int score;
    private List<PigState> pigStates;
    private List<BirdState> birdStates;
    private List<BlockState> blockStates;

    public GameState() {
        pigStates = new ArrayList<>();
        birdStates = new ArrayList<>();
        blockStates = new ArrayList<>();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
    public int getScore() {
        return score;
    }
    public List<PigState> getPigStates() {
        return pigStates;
    }
    public List<BirdState> getBirdStates() {
        return birdStates;
    }
    public List<BlockState> getBlockStates() {
        return blockStates;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setPigStates(List<PigState> pigStates) {
        this.pigStates = pigStates;
    }
    public void setBirdStates(List<BirdState> birdStates) {
        this.birdStates = birdStates;
    }
    public void setBlockStates(List<BlockState> blockStates) {
        this.blockStates = blockStates;
    }

    public static class PigState implements Serializable {
        float x, y;
        float health;
        boolean isDestroyed;
    }

    public static class BirdState implements Serializable {
        float x, y;
        boolean isLaunched;
        String birdType;
    }

    public static class BlockState implements Serializable {
        float x, y;
        float health;
        String blockType;
    }
}
