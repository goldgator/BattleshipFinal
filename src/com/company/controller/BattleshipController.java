package com.company.controller;

import com.company.model.*;
import com.company.model.difficulty.Difficulty;
import com.company.view.GameView;

public class BattleshipController {
    private GameView userUI = new GameView();
    private Player player1;
    private Player player2;
    private boolean isUserTurn = false;
    private Difficulty diff;
    boolean isPlacingShips = true;
    int placedShips = 0;
    int[] shipLengths = new int[]{4,3,3,2};
    private int[] userCoords;
    private int[] previousCoords;
    private boolean shipDestroyed = false;


    public void shipWasDestroyed() {
        System.out.println("Ship Destroyed!!!!!");
        String message = (isUserTurn) ? (player1.getName() + " destroyed a ship!") : (player2.getName() + " destroyed a ship!");
        userUI.addNewTextMessage(message);
        shipDestroyed = true;
    }



    public void setUserCoords(int[] newGuess) {
        previousCoords = userCoords;
        userCoords = newGuess;
        if (isUserTurn) {
            userTurn();
        }
        if (previousCoords != null && isPlacingShips) {
            setupShips();
            userCoords = null;
            previousCoords = null;
        }
    }

    public void setDiff(Difficulty newDiff) {
        diff = newDiff;
        System.out.println(newDiff.getClass().getSimpleName());
        setupPlayers();
    }

    public BattleshipController() {
        userUI.attachController(this);
    }

    public void run() {
        //Instantiation here
    }

    public void setupShips() {
        if (previousCoords.equals(userCoords)) return;
        boolean isHorizontal = (previousCoords[0] == userCoords[0]);
        boolean isVertical = (previousCoords[1] == userCoords[1]);
        if (!isHorizontal && !isVertical) return;
        int direction = (isHorizontal) ? ((int) Math.signum(userCoords[1] - previousCoords[1])) : ((int) Math.signum(userCoords[0] - previousCoords[0]));

        UserBoard playerBoard = player1.getOwnBoard();
        Ship placedShip = playerBoard.placeShip(previousCoords[0], previousCoords[1],isHorizontal,direction,shipLengths[placedShips]);
        if (placedShip == null) return;
        placedShips++;
        userUI.updateGuessGrid(playerBoard);
        if(placedShips == 4) {
            isPlacingShips = false;
            isUserTurn = true;
        }
    }

    public void setupPlayers() {
        player1 = new User("Hank", this);
        player2 = new Computer("Zorp",this, diff);
        player1.attachOpponentBoard(player2.getOwnBoard());
        player2.attachOpponentBoard(player1.getOwnBoard());
        player2.placeShips();
    }

    private void userTurn() {
        System.out.println(userCoords[0] + ", " + userCoords[1]);
        BoardState result = player1.guessShotBoard(userCoords);
        if (shipDestroyed) {
            player1.clearHits();
            shipDestroyed = false;
        } else {
            sendUserMessage(result);
        }

        userUI.updateSquareIcon(result, userCoords[0], userCoords[1],isUserTurn);


        userCoords = null;
        isUserTurn = false;
        opponentTurn();
    }

    private void opponentTurn() {
        int[] opponentGuess = player2.chooseGridSpace();
        System.out.println(opponentGuess[0] + ", " + opponentGuess[1]);
        BoardState result = player2.guessShotBoard(opponentGuess);
        if (shipDestroyed) {
            player2.clearHits();
            shipDestroyed = false;
        } else {
            sendUserMessage(result);
        }
        userUI.updateSquareIcon(result,opponentGuess[0],opponentGuess[1],isUserTurn);


        isUserTurn = true;
    }

    private void sendUserMessage(BoardState result) {
        String name = (isUserTurn) ? player1.getName() : player2.getName();

        switch (result) {
            case MISSED:
                userUI.addNewTextMessage(name + " missed.");
                break;
            case HIT:
                userUI.addNewTextMessage(name + " hit a ship!");
                break;
        }
    }

    public void gameEnd() {
        if (isUserTurn) {
            userUI.addNewTextMessage(player1.getName() + " won!!!!");
        } else {
            userUI.addNewTextMessage(player2.getName() + " won!!!!!");
        }
    }





}
