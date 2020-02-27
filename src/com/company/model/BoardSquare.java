package com.company.model;

public class BoardSquare {
    BoardState state;
    int row;
    int col;

    public BoardSquare(int newRow, int newCol) {
        row = newRow;
        col = newCol;
        state = BoardState.EMPTY;
    }

    public BoardState getState() {
        return state;
    }

    public void setState(BoardState state) {
        this.state = state;
    }

    public int[] getCoords() {
        return new int[]{row,col};
    }
}
