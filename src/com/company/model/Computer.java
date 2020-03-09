package com.company.model;

import com.company.controller.BattleshipController;
import com.company.model.difficulty.Difficulty;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player {
    private Difficulty diff;


    public Computer(String newName, BattleshipController cont, Difficulty newDiff) {
        super(newName, cont);
        setDiff(newDiff);
    }

    public void setDiff(Difficulty newDiff) {
        diff = newDiff;
    }

    public void placeShips() {
        Random rng = new Random();
        int[] shipLengths = new int[]{4, 3, 3, 2};

        for (int length : shipLengths) {
            int randRow;
            int randCol;
            boolean isHorizontal;
            int direction;
            Ship placedShip;
            do {
                randRow = rng.nextInt(8);
                randCol = rng.nextInt(8);
                isHorizontal = rng.nextBoolean();
                direction = (rng.nextInt(1) == 0) ? -1 : 1;

                placedShip = ownBoard.placeShip(randRow,randCol,isHorizontal,direction,length);
            } while (placedShip == null);
        }
        System.out.println("Computer changed all ship tiles");
    }



    public int[] chooseGridSpace() {
        return diff.chooseSquare(guessBoard,activeHits);
    }


}
