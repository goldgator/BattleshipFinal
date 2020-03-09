package com.company.view;

import com.company.model.Board;
import com.company.model.BoardState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel {
    private int btnSide = 4;
    private int panelSide = 2;
    private int gap = 4;
    private final ImageIcon empty = new ImageIcon("src/com/company/icons/water.png");
    private final ImageIcon ship = new ImageIcon("src/com/company/icons/ship.png");
    private final ImageIcon hit = new ImageIcon("src/com/company/icons/shiphurt.png");
    private final ImageIcon miss = new ImageIcon("src/com/company/icons/miss.png");
    private GameView userUI;
    private Color black = Color.BLACK;
    private Dimension btnPreferSize;
    private JButton[][] buttons = new JButton[8][8];

    public Grid(int pSize, GameView newView) {
        userUI = newView;
        btnPreferSize = new Dimension(pSize,pSize);
        setBackground(black);
        setLayout(new GridLayout(panelSide, panelSide, gap, gap));
        setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));

        setMaximumSize(new Dimension(pSize*8,pSize*8));
        setMinimumSize(new Dimension(pSize*8, pSize*8));

        JPanel[][] panels = new JPanel[panelSide][panelSide];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel(new GridLayout(btnSide, btnSide));
                panels[i][j].setBackground(black);
                panels[i][j].setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
                add(panels[i][j]);
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            int iPanel = i / btnSide;
            for (int j = 0; j< buttons[i].length; j++) {
                int jPanel = j / btnSide;
                buttons[i][j] = new JButton(resizeIcon(empty,pSize,pSize));
                buttons[i][j].setPreferredSize(btnPreferSize);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Send coordinates of button to Controller
                        userUI.tellContCoords(new int[]{finalI, finalJ});
                    }
                });
                panels[iPanel][jPanel].add(buttons[i][j]);
            }
        }
    }

    private ImageIcon resizeIcon(ImageIcon oldIcon, int newWidth, int newHeight) {
        Image image = oldIcon.getImage();
        image = image.getScaledInstance(newWidth,newHeight,Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public void updateEntireGrid(Board clonedBoard) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                BoardState newState = clonedBoard.getBoardSquare(i,j).getState();
                changeIcon(newState,i,j);
            }
        }
    }

    public void changeIcon(BoardState newState, int row, int col) {
        switch (newState) {
            case SHIP:
                buttons[row][col].setIcon(resizeIcon(ship,buttons[row][col].getWidth(),buttons[row][col].getHeight()));
                break;
            case HIT:
                buttons[row][col].setIcon(resizeIcon(hit,buttons[row][col].getWidth(),buttons[row][col].getHeight()));
                break;
            case MISSED:
                buttons[row][col].setIcon(resizeIcon(miss,buttons[row][col].getWidth(),buttons[row][col].getHeight()));
                break;
            case EMPTY:
                buttons[row][col].setIcon(resizeIcon(empty,buttons[row][col].getWidth(),buttons[row][col].getHeight()));
                break;
        }
    }

}