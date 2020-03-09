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
            boolean directionFailed = true;

            //Find and use direction if can
            if (activeHits.size() >= 2) {
                boolean isHorizontal = (activeHits.get(0).getCoords()[0] == activeHits.get(1).getCoords()[0]);
                if (isHorizontal) {
                    int newCol;

                    for(int i = 1; i > -3; i -= 2) {
                        for (BoardSquare b : activeHits) {
                            newCol = b.getCoords()[1] + i;
                            row = b.getCoords()[0];
                            if (newCol >= testBoard.getLength() || newCol < 0) break;

                            BoardSquare testSquare = testBoard.getBoardSquare(row, newCol);
                            if (testSquare.getState() == BoardState.EMPTY) {
                                adjacents.add(testSquare);
                                directionFailed = false;
                            }
                        }
                    }
                }


                if (adjacents.size() == 0) {
                    directionFailed = true;
                }
            }


            if (directionFailed == true) {
                adjacents.clear();
                //Find all adjacents regardless of direction
                for (BoardSquare b : activeHits) {
                    int[] coords = b.getCoords();
                    int newRow;
                    int newCol;
                    for (float i = 0; i < 2; i += .5) {
                        //Update newCoords
                        int vDif = (int) Math.sin(i * Math.PI);
                        int hDif = (int) Math.cos(i * Math.PI);



                        newRow = (int) (coords[0] + vDif);
                        newCol = (int) (coords[1] + hDif);
                        //Only grab square if new values are valid
                        boolean validRow = (newRow < testBoard.getLength() && newRow >= 0);
                        boolean validCol = (newCol < testBoard.getLength() && newCol >= 0);

                        if (validCol && validRow) {
                            BoardSquare testSquare = testBoard.getBoardSquare(newRow, newCol);
                            if (testSquare.getState() == BoardState.EMPTY) {
                                adjacents.add(testSquare);
                            }
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
