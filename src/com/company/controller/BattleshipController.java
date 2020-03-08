package com.company.controller;

import com.company.model.*;
import com.company.model.difficulty.Difficulty;
import com.company.view.GameView;

import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BattleshipController {
    private GameView userUI = new GameView();
    private Player player1;
    private Player player2;
    private boolean isUserTurn = true;
    private Difficulty diff;
    private int[] userGuess;


    public void setUserGuess(int[] newGuess) {
        userGuess = newGuess;
        if (isUserTurn) {
            userTurn();
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

    public void setupPlayers() {
        player1 = new User("Hank");
        player2 = new Computer("Zorp",diff);
        player1.attachOpponentBoard(player2.getOwnBoard());
        player2.attachOpponentBoard(player1.getOwnBoard());
    }

    private void userTurn() {
        System.out.println(userGuess[0] + ", " + userGuess[1]);
        BoardState result = player1.guessShotBoard(userGuess);
        userUI.updateSquareIcon(result,userGuess[0],userGuess[1],isUserTurn);

        userGuess = null;
        isUserTurn = false;
        opponentTurn();
    }

    private void opponentTurn() {
        int[] opponentGuess = player2.chooseGridSpace();
        System.out.println(opponentGuess[0] + ", " + opponentGuess[1]);
        BoardState result = player2.guessShotBoard(opponentGuess);
        userUI.updateSquareIcon(result,opponentGuess[0],opponentGuess[1],isUserTurn);


        isUserTurn = true;
    }





}
