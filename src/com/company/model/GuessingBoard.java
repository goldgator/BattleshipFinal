package com.company.model;

public class GuessingBoard extends Board {
    BoardSquare lastHitSquare;

    public GuessingBoard() {
        resetBoard();
    }

    public void targetSquare(int row, int col) {
        switch (board[row][col].getState()) {
            case EMPTY:
                board[row][col].setState(BoardState.MISSED);
                break;
            case SHIP:
                board[row][col].setState(BoardState.HIT);
                break;
        }
    }



}
