package com.company.model;

public class User extends Player {


    public User(String newName) {
        super(newName);
    }

    public int[] chooseGridSpace() {
        //TODO Make actual user interaction to get row and column
        int row = 0;
        int col = 0;

        guessBoard.targetSquare(row,col);
        return new int[]{row, col};
    }
}
