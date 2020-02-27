package com.company.model;

public abstract class Board {
    private BoardState[][] board = new BoardState[8][8];


    public void setBoardSquare(int row, int col, BoardState newState) {
        board[row][col] = newState;
    }

    public BoardState getBoardSquare(int row, int col) {
        return board[row][col];
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //Fills every square as empty
                board[i][j] = BoardState.EMPTY;
            }
        }
    }




}
