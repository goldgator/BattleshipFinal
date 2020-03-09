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
    private BattleshipController cont;
    private JFrame initFrame;
    private JMenuBar menuBar;
    private JMenu TopLevelMenu;
    private JMenuItem menuItem;
    private JButton button;

    private Grid userGrid;
    private Grid guessGrid;
    private JTextPane textArea;

    private String name1;
    private String name2;
    private JTextField textField1;
    private JTextField textField2;


    public GameView() {
        initFrame = new JFrame("Battleship");
        initFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuBarSetup();

        makeDifficultyOptions(initFrame.getContentPane());

        initFrame.setLayout(new BoxLayout(initFrame.getContentPane(),BoxLayout.Y_AXIS));
        initFrame.getContentPane().setBackground(Color.black);
        initFrame.pack();
        initFrame.setLocationRelativeTo(null);

//        initFrame.setSize(1000,1000);

        initFrame.setVisible(true);
    }

    public void attachController(BattleshipController newCont) {
        cont = newCont;
    }

    public void makeDifficultyOptions(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        addTitle(container);
        makeUserInput();
        addButton("Easy", container);
        addButton("Medium", container);
        addButton("Hard", container);

    }

    public void addTitle(Container container) {
        JTextPane titleArea = new JTextPane();
        titleArea.setText("Battleship");
        StyledDocument doc = titleArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        titleArea.setStyledDocument(doc);

        titleArea.setFont(new Font("Stencil", Font.BOLD, 56));


        titleArea.setMaximumSize(new Dimension(400,60));
        titleArea.setMinimumSize(new Dimension(400,60));
        titleArea.setPreferredSize(new Dimension(400,60));

        titleArea.setBackground(Color.BLACK);
        titleArea.setOpaque(true);
        titleArea.setForeground(new Color(0,102,153));

        JPanel box = new JPanel();
        box.add(titleArea);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        addEmptySpace(30);
        container.add(box);
    }

    public void addButton(String text, Container container) {
        button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setFont(new Font("Monospaced", Font.PLAIN, 20));

        button.setMaximumSize(new Dimension(250, 70));
        button.setMinimumSize(new Dimension(250, 70));
        button.setPreferredSize(new Dimension(250, 70));

        container.add(button);
        addEmptySpace(15);
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

    public void addEmptySpace(int space) {
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        empty.setMaximumSize(new Dimension(200, space));
        empty.setMinimumSize(new Dimension(200, space));
        empty.setPreferredSize(new Dimension(200, space));
        initFrame.getContentPane().add(empty);

    }

    public void makeUserInput() {
        JLabel setNames = new JLabel("Name yourself and the computer then choose a difficulty!");
        setNames.setAlignmentX(Component.CENTER_ALIGNMENT);
        setNames.setForeground(Color.WHITE);
        setNames.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
        textField1 = new JTextField();
        textField1.setMinimumSize(new Dimension(200,20));
        textField1.setMaximumSize(new Dimension(200,20));
        textField1.setText("Player name");

        textField2 = new JTextField();
        textField2.setMinimumSize(new Dimension(200,20));
        textField2.setMaximumSize(new Dimension(200,20));
        textField2.setText("Computer name");

        addEmptySpace(20);
        initFrame.getContentPane().add(setNames);
        addEmptySpace(10);
        initFrame.getContentPane().add(textField1);
        addEmptySpace(10);
        initFrame.getContentPane().add(textField2);
        addEmptySpace(60);
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
        textArea = new JTextPane();
        textArea.setText("Place your ships!");
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        textArea.setStyledDocument(doc);
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