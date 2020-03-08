package com.company.model;

import com.company.model.difficulty.Difficulty;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player {
    private ArrayList<BoardSquare> activeHits = new ArrayList<>();
    private Difficulty diff;

    public Computer(String newName, Difficulty newDiff) {
        super(newName);
        setDiff(newDiff);
    }

    public void setDiff(Difficulty newDiff) {
        diff = newDiff;
    }

    public int[] chooseGridSpace() {
        return diff.chooseSquare(guessBoard,activeHits);
    }

    public void addActiveHit(BoardSquare newActive) {
        activeHits.add(newActive);
    }
}
