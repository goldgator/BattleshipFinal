package com.company.view;

import com.company.controller.BattleshipController;
import com.company.model.difficulty.Easy;
import com.company.model.difficulty.Hard;
import com.company.model.difficulty.Medium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameView {
    BattleshipController cont;
    JFrame initFrame;
    JFrame playerBoard;
    JFrame playGameFrame;
    JFrame endFrame;
    JMenuBar menuBar;
    JMenu TopLevelMenu;
    JMenuItem menuItem;
    JButton button;

    public GameView() {
        initFrame = new JFrame("Battleship");
        initFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuBarSetup();
        makeDifficultyOptions(initFrame.getContentPane());
        initFrame.pack();
        initFrame.setVisible(true);
//        playerBoard.setVisible(false);

    }

    public void attachController(BattleshipController newCont) {
        cont = newCont;
    }


    public void makeDifficultyOptions(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        addButton("easy", container);
        addButton("medium", container);
        addButton("hard", container);

    }

    public void addButton(String text, Container container) {
        button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        //button.setBounds(100, 100, 100, 100);
        container.add(button);
        chooseDifficulty(text, button);
    }

    public void menuBarSetup() {
        menuBar = new JMenuBar();
        TopLevelMenu = new JMenu("Options");
        TopLevelMenu.setMnemonic(KeyEvent.VK_O);
        TopLevelMenu.getAccessibleContext().setAccessibleDescription("description");
        menuBar.add(TopLevelMenu);

        initFrame.setJMenuBar(menuBar);

        menuItem = new JMenuItem("Toggle Sound");
        menuItem.setMnemonic(KeyEvent.VK_S);
        TopLevelMenu.add(menuItem);
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_E);
        TopLevelMenu.add(menuItem);
    }

    public void chooseDifficulty(String text, JButton button) {
        switch (text) {
            case "easy":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //start game in ea
                        System.out.println("starting in easy");
                        cont.setDiff(new Easy());
                        gridSetup();
                    }
                });
                break;
            case "medium":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //start game in medium
                        System.out.println("starting in medium");
                        cont.setDiff(new Medium());
                        gridSetup();
                    }
                });
                break;
            case "hard":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //start game in hard
                        System.out.println("starting in hard");
                        cont.setDiff(new Hard());
                        gridSetup();
                    }
                });
                break;
        }
        initFrame.setVisible(false);

    }

    public void gridSetup() {
        Grid grid = new Grid(70,this);
        JTextArea textArea = new JTextArea("Stuff goes here");
        textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        textArea.setOpaque(false);
        Grid grid2 = new Grid(40,this);

        initFrame.dispose();
        initFrame = new JFrame("Set Up Your Board!");

        initFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel topGrid = new JPanel();
        JPanel centerText = new JPanel();
        JPanel botGrid = new JPanel();;
        topGrid.add(grid);
        centerText.add(textArea);
        botGrid.add(grid2);
        topGrid.setLayout(new BoxLayout(topGrid,BoxLayout.Y_AXIS));
        centerText.setLayout(new BoxLayout(centerText,BoxLayout.Y_AXIS));
        botGrid.setLayout(new BoxLayout(botGrid,BoxLayout.Y_AXIS));

        initFrame.getContentPane().add(topGrid);
        initFrame.getContentPane().add(centerText);
        initFrame.getContentPane().add(botGrid);
        initFrame.setLayout(new BoxLayout(initFrame.getContentPane(),BoxLayout.Y_AXIS));

        initFrame.pack();
        initFrame.setLocationRelativeTo(null);
        initFrame.setVisible(true);
    }

    public void tellContCoords(int[] coords) {
        cont.setUserGuess(coords);
    }

}