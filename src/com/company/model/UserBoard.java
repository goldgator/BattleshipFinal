package com.company.model;

import com.company.controller.BattleshipController;

import java.util.ArrayList;

public class UserBoard extends Board {
    BattleshipController cont;
    ArrayList<Ship> allShips = new ArrayList<>();

    public UserBoard(BattleshipController newCont) {
        cont = newCont;
        resetBoard();
    }

    public BoardState targetSquare(int row, int col) {
        switch (board[row][col].getState()) {
            case EMPTY:
                board[row][col].setState(BoardState.MISSED);
                return BoardState.MISSED;
            case SHIP:
                board[row][col].setState(BoardState.HIT);
                removeHitShipPiece(board[row][col]);
                return BoardState.HIT;
            default:
                return board[row][col].getState();
        }
    }

    /**
     * Places a ship using the parameters, returns null if ship was not able to be made
     * @param row row index of starting ship point
     * @param col col index of starting ship point
     * @param isHorizontal boolean saying it is placed horizontally
     * @param direction integer(1/-1) that determines direction of ship
     * @param shipLength length of the ship being placed
     */
    public Ship placeShip(int row, int col, boolean isHorizontal, int direction, int shipLength) throws IllegalArgumentException {
        ArrayList<BoardSquare> modifiedSquares = new ArrayList<>();
        try {
            if (board[row][col].getState() == BoardState.EMPTY) {
                board[row][col].setState(BoardState.SHIP);
                modifiedSquares.add(board[row][col]);
                for (int i = 1; i < shipLength; i++) {
                    if (isHorizontal) {
                        int newCol = col + (direction * i);
                        if (newCol < 0 || newCol >= 8) throw new IllegalArgumentException("Went out of bounds");
                        if (board[row][newCol].getState() == BoardState.EMPTY) {
                            board[row][newCol].setState(BoardState.SHIP);
                            modifiedSquares.add(board[row][newCol]);
                        } else {
                            throw new IllegalArgumentException("Overlapped a ship");
                        }
                    } else {
                        int newRow = row + (direction * i);
                        if (newRow < 0 || newRow >= 8) throw new IllegalArgumentException("Went out of bounds");
                        if (board[newRow][col].getState() == BoardState.EMPTY) {
                            board[newRow][col].setState(BoardState.SHIP);
                            modifiedSquares.add(board[newRow][col]);
                        } else {
                            throw new IllegalArgumentException("Overlapped a ship");
                        }
                    }
                }
                Ship newShip = new Ship(modifiedSquares, this);
                addShip(newShip);
                return newShip;
            } else {
                throw new IllegalArgumentException("Overlapped a ship");
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            for (BoardSquare b : modifiedSquares) {
                b.setState(BoardState.EMPTY);
            }
        }
        return null;
    }

    public void addShip(Ship newShip) {
        allShips.add(newShip);
    }

    public void clearDestroyedShips() {
        for (int i = 0; i < allShips.size(); i++) {
            if (allShips.get(i).isEmpty()) {
                allShips.remove(i);
                cont.shipWasDestroyed();
                break;
            }
        }
        if (allShips.isEmpty()) {
            allShipsDestroyed();
        }
    }

    public void removeHitShipPiece(BoardSquare hitSquare) {
        //Will do nothing for ships not affected, will remove the piece if it is the right ship
        for (Ship s : allShips) {
            if (s.removePart(hitSquare)) break;
        }
        clearDestroyedShips();
    }

    private void allShipsDestroyed() {
        cont.setGameEnd(true);
        cont.gameEnd();
    }


}
