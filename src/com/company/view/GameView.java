package com.company.view;

import com.company.controller.BattleshipController;
import com.company.model.Board;
import com.company.model.BoardState;
import com.company.model.difficulty.Easy;
import com.company.model.difficulty.Hard;
import com.company.model.difficulty.Medium;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameView {
    BattleshipController cont;
    JFrame initFrame;
    JMenuBar menuBar;
    JMenu TopLevelMenu;
    JMenuItem menuItem;
    JButton button;

    Grid userGrid;
    Grid guessGrid;
    JLabel textArea;

    String name1;
    String name2;
    JTextField textField1;
    JTextField textField2;


    public GameView() {
        initFrame = new JFrame("Battleship");
        initFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuBarSetup();
        makeUserInput();
        makeDifficultyOptions(initFrame.getContentPane());

        initFrame.setBackground(Color.black);
        initFrame.pack();
        initFrame.setLocationRelativeTo(null);

        initFrame.setSize(1000,1000);

        initFrame.setVisible(true);
    }

    public void attachController(BattleshipController newCont) {
        cont = newCont;
    }

    public void makeDifficultyOptions(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        addTitle(container);
        addButton("Easy", container);
        addButton("Medium", container);
        addButton("Hard", container);

    }

    public void addTitle(Container container) {
        JLabel titleArea = new JLabel("Place your ships!");
        titleArea.setHorizontalAlignment(SwingConstants.CENTER);
        titleArea.setVerticalAlignment(SwingConstants.CENTER);
        titleArea.setText("BattleShip!");
        titleArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        titleArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        titleArea.setMaximumSize(new Dimension(400,120));
        titleArea.setMinimumSize(new Dimension(400,120));
        titleArea.setPreferredSize(new Dimension(400,120));
        titleArea.setBackground(Color.BLACK);
        titleArea.setOpaque(true);
        titleArea.setForeground(Color.WHITE);

        container.add(titleArea);
    }

    public void addButton(String text, Container container) {
        button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);


        button.setFont(new Font("Monospaced", Font.PLAIN, 20));

        button.setMaximumSize(new Dimension(250, 70));
        button.setMinimumSize(new Dimension(250, 70));
        button.setPreferredSize(new Dimension(250, 70));

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

    public void makeUserInput() {
        JLabel setNames = new JLabel("What would you like the player names to be? (choose a difficulty to enter and start)");
        setNames.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField1 = new JTextField();
        textField1.setMinimumSize(new Dimension(200,20));
        textField1.setMaximumSize(new Dimension(200,20));
        textField2 = new JTextField();
        textField2.setMinimumSize(new Dimension(200,20));
        textField2.setMaximumSize(new Dimension(200,20));
        initFrame.getContentPane().add(setNames);
        initFrame.getContentPane().add(textField1);
        initFrame.getContentPane().add(textField2);
    }

    public void setPlayerNames() {
        name1 = textField1.getText();
        name2 = textField2.getText();
        cont.setupPlayers(name1, name2);
    }

    public void chooseDifficulty(String text, JButton button) {
        switch (text) {
            case "Easy":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("starting in easy");
                        cont.setDiff(new Easy());
                        setPlayerNames();
                        gridSetup();
                    }
                });
                break;
            case "Medium":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("starting in medium");
                        cont.setDiff(new Medium());
                        setPlayerNames();
                        gridSetup();
                    }
                });
                break;
            case "Hard":
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("starting in hard");
                        cont.setDiff(new Hard());
                        setPlayerNames();
                        gridSetup();
                    }
                });
                break;
        }
        //initFrame.setVisible(false);

    }

    public void setupTextArea() {
        textArea = new JLabel("Place your ships!");
        textArea.setHorizontalAlignment(SwingConstants.CENTER);
        textArea.setVerticalAlignment(SwingConstants.CENTER);
        textArea.setText("Place your ships!");
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));

        textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        textArea.setMaximumSize(new Dimension(400,120));
        textArea.setMinimumSize(new Dimension(400,120));
        textArea.setPreferredSize(new Dimension(400,120));

        textArea.setBackground(Color.BLACK);
        textArea.setOpaque(true);
        textArea.setForeground(Color.WHITE);
    }

    public void gridSetup() {
        setupTextArea();

        guessGrid = new Grid(65,this);
        userGrid = new Grid(40,this);
        initFrame.dispose();
        initFrame = new JFrame("BattleShip!");
        initFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel topGrid = new JPanel();
        JPanel centerText = new JPanel();
        JPanel botGrid = new JPanel();;
        topGrid.add(guessGrid);
        centerText.add(textArea);
        botGrid.add(userGrid);
        topGrid.setLayout(new BoxLayout(topGrid,BoxLayout.Y_AXIS));
        centerText.setLayout(new BoxLayout(centerText,BoxLayout.Y_AXIS));
        botGrid.setLayout(new BoxLayout(botGrid,BoxLayout.Y_AXIS));

        initFrame.getContentPane().add(topGrid);
        initFrame.getContentPane().add(centerText);
        initFrame.getContentPane().add(botGrid);
        initFrame.setLayout(new BoxLayout(initFrame.getContentPane(),BoxLayout.Y_AXIS));

        initFrame.getContentPane().setBackground(Color.black);
        initFrame.pack();
        initFrame.setLocationRelativeTo(null);
        initFrame.setVisible(true);
    }

    public void addNewTextMessage(String message) {
        String lastMessage = textArea.getText().split("\n")[0];
        String concat;
        if (lastMessage.isEmpty()) {
            concat = "";
        } else {
            concat = lastMessage;
        }
        concat = message + "\n" + concat;
        textArea.setText(concat);
    }

    public void tellContCoords(int[] coords) {
        cont.setUserCoords(coords);
    }

    public void updateSquareIcon(BoardState newState, int row, int col, boolean isUserTurn) {
        Grid chosenGrid = (isUserTurn) ? guessGrid : userGrid;
        chosenGrid.changeIcon(newState,row,col);
    }

    public void updateGuessGrid(Board clonedBoard) {
        userGrid.updateEntireGrid(clonedBoard);
    }

}