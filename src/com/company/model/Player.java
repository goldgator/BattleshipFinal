package com.company.model;

public abstract class Player {
    private String name = "";
    protected UserBoard ownBoard = new UserBoard();
    protected GuessingBoard guessBoard = new GuessingBoard();
    protected int[] lastCoords = null;

    public Player(String newName) {
        name = newName;
    }

    public UserBoard getOwnBoard() {
        return ownBoard;
    }

    public void attachOpponentBoard(UserBoard newBoard) {
        guessBoard.attachOpponentBoard(newBoard);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int[] chooseGridSpace();

    public BoardState guessShotBoard(int[] coords) {
        return guessBoard.targetSquare(coords[0],coords[1]);
    }
}
