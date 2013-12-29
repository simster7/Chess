package com.game;

/*
 * Location serves as a convinient way to store two different notaions for
 * locations used in this program: (1) the chess board chess notation (e.g. C4)
 * and (2) its corresponding place (e.g. [4][2]) on the two-dimentional matrix
 * (board) that stores all the pieces.
 */
public class Location {

    private int x;  //The x index on the matrix
    private int y;  //The y index on the matrix

    public Location(char let, int x) {
        this.x = letterToNum(let);
        this.y = 8 - x;
    }

    public Location(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /*
     * Converts a char to its corresponding index (e.g. B -> 1).
     */
    public int letterToNum(char let) {
        return let - 65;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getChessY() {
        return 8 - y;
    }

    public char getChessX() {
        return (char) (x + 65);
    }

    public void setX(char let) {
        this.x = letterToNum(let);
    }

    public void setY(int y) {
        this.y = 8 - y;
    }

    public String toString() {
        return "" + Character.toString(getChessX()) + getChessY();
    }
}