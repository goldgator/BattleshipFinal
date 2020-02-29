package com.company.model;

import java.util.ArrayList;

public class UserBoard extends Board {
    ArrayList<Ship> allShips = new ArrayList<>();

    public UserBoard() {
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

    public boolean allShipsDestroyed() {
        return false;
    }


}
