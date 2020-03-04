package com.company.model.difficulty;

import com.company.model.Board;
import com.company.model.BoardSquare;

import java.util.ArrayList;

public interface Difficulty {

    int[] chooseSquare(Board testBoard, ArrayList<BoardSquare> activeHits);

}
