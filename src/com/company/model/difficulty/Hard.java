package com.company.model.difficulty;

import com.company.model.Board;
import com.company.model.BoardSquare;
import com.company.model.BoardState;

import java.util.ArrayList;
import java.util.Random;

public class Hard {

    public int[] chooseSquare(Board testBoard, ArrayList<BoardSquare> activeHits) {
        int row = 0;
        int col = 0;
        Random rng = new Random();

        if (activeHits.isEmpty()) {
            //Choose random empty square in a checkerboard fashion
            do {
                row = rng.nextInt(testBoard.getLength());
                col = rng.nextInt(testBoard.getLength());

                if (row % 2 == 1) {
                    row += Math.abs(testBoard.getLength() - row);
                }
                if (row % 2 == 1) {
                    row += Math.abs(testBoard.getLength() - row);
                }
            } while (testBoard.getBoardSquare(row, col).getState() != BoardState.EMPTY);

            return new int[]{row, col};
        } else {
            ArrayList<BoardSquare> adjacents = new ArrayList<>();

            //Find all adjacents
            for (BoardSquare b : activeHits) {
                int[] coords = b.getCoords();
                int newRow;
                int newCol;
                for (int i = 0; i < 360; i += 90) {
                    //Update newCoords
                    newRow = (int) (coords[0] + Math.sin(i));
                    newCol = (int) (coords[1] + Math.cos(i));
                    //Leave loop if out of bounds
                    if (coords[0] + Math.sin(i) >= testBoard.getLength() || coords[0] + Math.sin(i) < 0) break;
                    if (coords[1] + Math.cos(i) >= testBoard.getLength() || coords[0] + Math.cos(i) < 0) break;

                    BoardSquare testSquare = testBoard.getBoardSquare(newRow,newCol);
                    if (testSquare.getState() == BoardState.EMPTY) {
                        adjacents.add(testSquare);
                    }
                }
            }

            //Choose random adjacent
            int rand = rng.nextInt(testBoard.getLength());
            return adjacents.get(rand).getCoords();
        }
    }
}
