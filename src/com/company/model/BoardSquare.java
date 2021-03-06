package com.company.model;

public class BoardSquare {
    private BoardState state;
    private int row;
    private int col;

    public void setRow(int row) {
        this.row = row;
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
