package com.company.model;

public class BoardSquare {
    BoardState state;
    int row;
    int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public BoardSquare(int newRow, int newCol) {
        setRow(newRow);
        setCol(newCol);
        setState(BoardState.EMPTY);
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
