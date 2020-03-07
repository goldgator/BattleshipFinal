package com.company.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameView {
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
                        gridSetup();
                    }
                });
                break;
            case "medium":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //start game in medium
                        System.out.println("starting in medium");
                        gridSetup();
                    }
                });
                break;
            case "hard":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //start game in hard
                        System.out.println("starting in hard");
                        gridSetup();
                    }
                });
                break;
        }
        initFrame.setVisible(false);

    }

    public void gridSetup() {
        Grid grid = new Grid(30);
        Grid grid2 = new Grid(20);

        initFrame.dispose();
        initFrame = new JFrame("Set Up Your Board!");

        initFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initFrame.getContentPane().add(grid);
        initFrame.getContentPane().add(grid2);
        initFrame.setLayout(new BoxLayout(initFrame.getContentPane(),BoxLayout.Y_AXIS));

        initFrame.pack();
        initFrame.setLocationRelativeTo(null);
        initFrame.setVisible(true);
        for (Component c : initFrame.getContentPane().getComponents()) {
            System.out.println(c.toString());
        }
    }
}