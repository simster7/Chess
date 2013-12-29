package com.game;

public class Test {

    public static void main(String[] args) {
        Bishop b = new Bishop(new Location('C', 4), false);

        System.out.println(b.canMove(new Location('A', 2)));//True
        System.out.println(b.canMove(new Location('D', 5)));//True
        System.out.println(b.canMove(new Location('F', 2)));//False

        Location l = new Location('C', 4);

        System.out.println(l.getY() + " " + l.getX());

        /*
         * int i = 1; char c = (char) (i + 64);
         *
         * System.out.println(c);
         *
         */

    }
}
