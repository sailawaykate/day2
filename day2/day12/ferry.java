package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.abs;

public class ferry {

   /*
    The ship starts by facing east.

    Action N means to move the waypoint north by the given value.
    Action S means to move the waypoint south by the given value.
    Action E means to move the waypoint east by the given value.
    Action W means to move the waypoint west by the given value.
    Action L means to rotate the waypoint around the ship left (counter-clockwise) the given number of degrees.
    Action R means to rotate the waypoint around the ship right (clockwise) the given number of degrees.
    Action F means to move forward to the waypoint a number of times equal to the given value.
    The waypoint starts 10 units east and 1 unit north relative to the ship. The waypoint is relative to the ship;
    that is, if the ship moves, the waypoint moves with it.
    */
    public static int[] turnLeft(int EW, int NS, int turns) {
        // if ew is -10 & turns 90degrees right, ns becomes -10
        // if ew is -10 & turns 90degrees left, ns becomes 10
        int newEW = 0;
        int newNS = 0;

        if (turns == 1) {
            // EW & NS flip places & negatives
            newNS = EW * -1;
            newEW = NS;
        }
        if (turns == 2) {
            // EW & NS flip negatives
            newEW = EW * -1;
            newNS = NS * -1;
        }
        if (turns == 3) {
            // EW & NS flip places
            newEW = NS * -1;
            newNS = EW * -1;
        }
        if (turns == 4 || turns == 0) {
            // no change
            newEW = EW;
            newNS = NS;
        }
        int[] newOrientation = new int[2];
        newOrientation[0] = newEW;
        newOrientation[1] = newNS;

        return newOrientation;
    }

    public static int[] turnRight(int EW, int NS, int turns) {
        // if ew is -10 & turns 90degrees right, ns becomes -10
        int newEW = 0;
        int newNS = 0;

        if (turns == 1) {
            newNS = EW;
            newEW = NS * -1;
        }
        if (turns == 2) {
            newEW = EW * -1;
            newNS = NS * -1;
        }
        if (turns == 3) {
            newEW = NS * -1;
            newNS = EW * -1;
        }
        if (turns == 4 || turns == 0) {
            // no change
            newEW = EW;
            newNS = NS;
        }
        int[] newOrientation = new int[2];
        newOrientation[0] = newEW;
        newOrientation[1] = newNS;

        return newOrientation;
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day12/ferry.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> input = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            input.add(line);
        }

        int waypointEW = 10;
        int waypointNS = -1;
        int shipEW = 0;
        int shipNS = 0;

        for (String direction: input) {
            String action = String.valueOf(direction.charAt(0));
            int number = Integer.parseInt(direction.replace(action, ""));
            switch (action) {
                //Action N/S/E/W means to move the waypoint north by the given value.
                case "N" -> waypointNS = waypointNS - number;
                case "S" -> waypointNS = waypointNS + number;
                case "E" -> waypointEW = waypointEW + number;
                case "W" -> waypointEW = waypointEW - number;
                //Action L/R means to rotate the waypoint around the ship left or right the given number of degrees
                case "L" -> {
                    // get current waypoint EW/NS position
                    int turns = abs(number / 90);
                    while (turns > 3) {
                        turns = turns - 4;
                    }
                    int[] waypoint = turnLeft(waypointEW, waypointNS, turns);
                    waypointEW = waypoint[0];
                    waypointNS = waypoint[1];
                }
                case "R" -> {
                    // get current waypoint EW/NS position
                    int turns = abs(number / 90);
                    while (turns > 3) {
                        turns = turns - 4;
                    }
                    int[] waypoint = turnRight(waypointEW, waypointNS, turns);
                    waypointEW = waypoint[0];
                    waypointNS = waypoint[1];
                }
                //Action F means to move forward to the waypoint a number of times equal to the given value
                case "F" -> {
                    // get diff between ship & waypoint for EW/NS
                    for (int i = 0; i < number; i++) {
                        shipNS = shipNS + waypointNS;
                        shipEW = shipEW + waypointEW;
                    }
                }
            }
        }
        System.out.println(input);
        System.out.println("East/West: " + shipEW);
        System.out.println("North/South: " + shipNS);
        long sum = abs(shipEW) + abs(shipNS);
        System.out.println("Answer: " + sum);
    }
}


