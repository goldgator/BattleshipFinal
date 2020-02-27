package com.company.model;

public abstract class Player {
    private String name = "";
    private UserBoard ownBoard = new UserBoard();
    private GuessingBoard guessBoard = new GuessingBoard();

    Player(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void chooseGridSpace();
}
