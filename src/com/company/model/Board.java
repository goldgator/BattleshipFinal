package com.company.model;

public abstract class Board {
    protected BoardSquare[][] board = new BoardSquare[8][8];

    //Copy uses: ◯,⦻,⛝,⬜



    public abstract void targetSquare(int row, int col);


    public BoardState getBoardSquare(int row, int col) {
        return board[row][col].getState();
    }

    /**
     * Resets the board, putting everything back to Empty
     */
    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i > 1) {
                    //Fills every square as empty
                    board[i][j] = new BoardSquare(i, j);
                } else {
                    BoardSquare square = new BoardSquare(i, j);
                    square.setState(BoardState.SHIP);
                    board[i][j] = square;
                }
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
