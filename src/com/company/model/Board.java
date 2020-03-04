package com.company.model;

public abstract class Board {
    protected BoardSquare[][] board = new BoardSquare[8][8];

    //Copy uses: ◯,⦻,⛝,⬜



    public abstract void targetSquare(int row, int col);

    public int getLength() {
        return board.length;
    }

    public void setBoardSquare(int row, int col, BoardState newState) {
        board[row][col].setState(newState);
    }

    public BoardSquare getBoardSquare(int row, int col) {
        return board[row][col];
    }

    /**
     * Resets the board, putting everything back to Empty
     */
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //Fills every square as empty
                board[i][j] = new BoardSquare(i, j);
            }
        }
    }


    @Override
    public String toString() {
        String output = "|";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                switch (board[i][j].getState()) {
                    case EMPTY:
                        output += "◯|";
                        break;
                    case SHIP:
                        output += "⬜|";
                        break;
                    case HIT:
                        output += "⛝|";
                        break;
                    case MISSED:
                        output += "⦻|";
                        break;
                }
            }
            if (i != 7) {
                output += "\n|";
            }
        }
        return output;
    }

}
