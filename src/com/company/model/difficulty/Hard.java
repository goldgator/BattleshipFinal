package com.company.model.difficulty;

import com.company.model.Board;
import com.company.model.BoardSquare;
import com.company.model.BoardState;

import java.util.ArrayList;
import java.util.Random;

public class Hard implements Difficulty{

    public int[] chooseSquare(Board testBoard, ArrayList<BoardSquare> activeHits) {
        int row = 0;
        int col = 0;
        Random rng = new Random();

        if (activeHits.isEmpty()) {
            //Choose random empty square in a checkerboard fashion
            do {
                row = rng.nextInt(testBoard.getLength());
                col = rng.nextInt(testBoard.getLength());

                if (row % 2 == 0) {
                    if (col % 2 == 0) {
                        col += Math.signum((testBoard.getLength()/2f) - col);
                    }
                } else {

                    if (col % 2 == 1) {
                        col += Math.signum((testBoard.getLength()/2f) - col);
                    }
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
                for (float i = 0; i < 2; i += .5) {
                    //Update newCoords
                    int vDif = (int) Math.sin(i*Math.PI);
                    int hDif = (int) Math.cos(i*Math.PI);

                    if ((Math.abs(vDif) + Math.abs(hDif) > 1)) {
                        throw new IllegalStateException("i: " + i + "|vDif: " + vDif + "|hDif: " + hDif);
                    }

                    newRow = (int) (coords[0] + vDif);
                    newCol = (int) (coords[1] + hDif);
                    //Only grab square if new values are valid
                    boolean validRow =  (newRow < testBoard.getLength() && newRow >= 0);
                    boolean validCol =  (newCol < testBoard.getLength() && newCol >= 0);

                    if (validCol && validRow) {
                        BoardSquare testSquare = testBoard.getBoardSquare(newRow,newCol);
                        if (testSquare.getState() == BoardState.EMPTY) {
                            adjacents.add(testSquare);
                        }
                    }
                }
            }

            //Choose random adjacent
            if (adjacents.size() == 0) {
                throw new IllegalStateException("Row: " + row + "|Col: " + col);
            }

            int rand = rng.nextInt(adjacents.size());
            return adjacents.get(rand).getCoords();
        }
    }
}
