package com.company.controller;

import com.company.model.Computer;
import com.company.model.Player;
import com.company.model.User;
import com.company.model.UserBoard;
import com.company.view.GameView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BattleshipController {
    private GameView userUI = new GameView();
    private Player player1;


    public void run() {
        String input  = promptForString("[row,col]");
        String[] rowColString = input.split(",");
        int[] rowColInt = {Integer.parseInt(rowColString[0]), Integer.parseInt(rowColString[1])};
        UserBoard newBoard = new UserBoard();
        newBoard.targetSquare(rowColInt[0],rowColInt[1]);
        System.out.println(newBoard);
    }

    public String promptForString(String prompt) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(prompt);
        String userString = "";
        boolean escapeBoolean = false;
        do {
            try {
                //Take input and trim pointless white space
                userString = input.readLine().trim();
                if (userString.isEmpty()) {
                    throw new IllegalArgumentException("Please give a response: ");
                }
                return userString;
            } catch (IOException | IllegalArgumentException exc) {
                System.out.print(exc.getMessage()); //Empty space to make sure it replaces all characters
                System.out.flush();
            }
        } while (0 == 0); //Will only leave loop via return statement
    }



}
