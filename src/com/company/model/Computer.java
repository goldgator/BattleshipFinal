package com.company.model;

public class Computer extends Player {


    public Computer(String newName) {
        super(newName);
    }

    public int[] chooseGridSpace() {
        //TODO Create AI
        int row = 0;
        int col = 0;

        return new int[]{row,col};
    }

}
