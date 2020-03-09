package com.company.model;



import java.util.ArrayList;

public class Ship {
    private UserBoard parentBoard;
    private ArrayList<BoardSquare> shipParts;

    Ship(ArrayList<BoardSquare> newParts, UserBoard newBoard) {
        shipParts = newParts;
        parentBoard = newBoard;
    }

    public boolean removePart(BoardSquare shotSquare) {
        return shipParts.remove(shotSquare);
    }

    public boolean isEmpty() {
        return shipParts.isEmpty();
    }

}
