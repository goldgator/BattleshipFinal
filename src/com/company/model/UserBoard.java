package com.company.model;

import java.util.ArrayList;

public class UserBoard extends Board {
    ArrayList<Ship> allShips = new ArrayList<>();

    public UserBoard() {
        resetBoard();
    }

    public BoardState targetSquare(int row, int col) {
        switch (board[row][col].getState()) {
            case EMPTY:
                board[row][col].setState(BoardState.MISSED);
                return BoardState.MISSED;
            case SHIP:
                board[row][col].setState(BoardState.HIT);
                return BoardState.HIT;
            default:
                return board[row][col].getState();
        }
    }

    public boolean allShipsDestroyed() {
        return false;
    }


}
