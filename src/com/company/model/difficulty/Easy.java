package com.company.model.difficulty;

import com.company.model.Board;
import com.company.model.BoardSquare;
import com.company.model.BoardState;

import java.util.ArrayList;
import java.util.Random;

public class Easy implements Difficulty {

    public int[] chooseSquare(Board testBoard, ArrayList<BoardSquare> activeHits) {
        int row = 0;
        int col = 0;
        Random rng = new Random();

        do {
            row = rng.nextInt(testBoard.getLength());
            col = rng.nextInt(testBoard.getLength());
        } while (testBoard.getBoardSquare(row,col).getState() != BoardState.EMPTY);
        return new int[]{row,col};
    }
}
