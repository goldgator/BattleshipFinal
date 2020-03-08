package com.company.view;

import com.company.controller.BattleshipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel {
    private int btnSide = 4;
    private int panelSide = 2;
    private int gap = 4;
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
        JPanel[][] panels = new JPanel[panelSide][panelSide];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel(new GridLayout(btnSide, btnSide));
                panels[i][j].setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
                add(panels[i][j]);
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            int iPanel = i / btnSide;
            for (int j = 0; j< buttons[i].length; j++) {
                int jPanel = j / btnSide;
                String coordinate = String.format("(%d, %d)", i, j);
                buttons[i][j] = new JButton(coordinate);
                buttons[i][j].setPreferredSize(btnPreferSize);
                buttons[i][j].setMinimumSize(btnPreferSize);
                buttons[i][j].setMaximumSize(btnPreferSize);

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
}


