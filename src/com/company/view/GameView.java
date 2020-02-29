package com.company.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public GameView() {
        setSize(500, 700);
        setTitle("Battleship");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        JButton a = new JButton("button");
        JLabel b = new JLabel();
        JLabel c = new JLabel();

        Container pane = this.getContentPane();
        //consider using a BorderLayout if things start getting weird?
        pane.setLayout(new GridLayout(8, 8));
        pane.add(a);
        pane.add(b);
        pane.add(c);
        //adskjfa;lskdjfa;

    }

    public void makeView() {
    }


}
