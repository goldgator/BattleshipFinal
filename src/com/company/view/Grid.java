package com.company.view;

import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private int btnSide = 4;
    private int gap = 4;
    private Color black = Color.BLACK;
    private Dimension btnPreferSize;
    private JButton[][] buttons = new JButton[16][16];

    public Grid(int pSize) {
        btnPreferSize = new Dimension(pSize,pSize);
        setBackground(black);
        setLayout(new GridLayout(btnSide, btnSide, gap, gap));
        setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        JPanel[][] panels = new JPanel[btnSide][btnSide];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel(new GridLayout(btnSide, btnSide));
                add(panels[i][j]);
            }
        }
        for (int i = 0; i < buttons.length; i++) {
            int iPanel = i / btnSide;
            for (int j = 0; j< buttons[i].length ; j++) {
                int jPanel = j / btnSide;
                String coordinate = String.format("(%d, %d)", j, i);
                buttons[i][j] = new JButton(coordinate);
                buttons[i][j].setPreferredSize(btnPreferSize);
                buttons[i][j].setMinimumSize(btnPreferSize);
                buttons[i][j].setMaximumSize(btnPreferSize);
                panels[iPanel][jPanel].add(buttons[i][j]);
            }
        }
    }
}


