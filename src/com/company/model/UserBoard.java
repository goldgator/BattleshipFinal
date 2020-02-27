package com.company.model;

import java.util.ArrayList;

public class UserBoard extends Board {
    ArrayList<Ship> allShips = new ArrayList<>();

    public UserBoard() {
        resetBoard();
    }


    public boolean allShipsDestroyed() {
        return false;
    }


}
