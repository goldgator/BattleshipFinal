package com.company.model;

public class GuessingBoard extends Board {
    BoardSquare lastHitSquare;
    UserBoard opponentBoard;

    public GuessingBoard() {
        resetBoard();
    }

    public void attachOpponentBoard(UserBoard newBoard) {
        opponentBoard = newBoard;
    }

    public BoardState targetSquare(int row, int col) {
        BoardState resultState = opponentBoard.targetSquare(row, col);
        board[row][col].setState(resultState);
        return resultState;
    }



}
