package com.company.model;

public abstract class Player {
    private String name = "";
    protected UserBoard ownBoard = new UserBoard();
    protected GuessingBoard guessBoard = new GuessingBoard();
    protected int[] lastCoords = null;

    public Player(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int[] chooseGridSpace();

    public void shotResults(BoardState newState) {

    }
}
