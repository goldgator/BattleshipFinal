package com.company;

import com.company.controller.BattleshipController;
import com.company.view.GameView;

public class Main {

    public static void main(String[] args) {

        GameView view = new GameView();
        new BattleshipController().run();
    }
}