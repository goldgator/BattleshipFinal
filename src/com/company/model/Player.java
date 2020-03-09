package com.company.model;

import com.company.controller.BattleshipController;

import java.util.ArrayList;

public abstract class Player {
    private String name = "";
    protected UserBoard ownBoard;
    private BattleshipController cont;
    protected GuessingBoard guessBoard = new GuessingBoard();

    protected ArrayList<BoardSquare> activeHits = new ArrayList<>();
    protected int[] lastCoords = null;

    public Player(String newName, BattleshipController newCont) {
        cont = newCont;
        ownBoard = new UserBoard(cont);
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

    public void addNewActiveHit(BoardSquare hitSquare) {
        activeHits.add(hitSquare);
    }

    public void clearHits() {
        activeHits.clear();
    }

    public abstract void placeShips();

    public abstract int[] chooseGridSpace();

    public BoardState guessShotBoard(int[] coords) {
        BoardState result = guessBoard.targetSquare(coords[0],coords[1]);
        if (result == BoardState.HIT) {
            BoardSquare hitSquare = ownBoard.getBoardSquare(coords[0],coords[1]);
            addNewActiveHit(hitSquare);
            ownBoard.removeHitShipPiece(hitSquare);
        }
        return result;
    }
}
