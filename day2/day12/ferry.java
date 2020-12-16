package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.abs;

public class ferry {

   /*
    Action N means to move north by the given value.
    Action S means to` move south by the given value.
    Action E means to move east by the given value.
    Action W means to move west by the given value.
    Action L means to turn left the given number of degrees.
    Action R means to turn right the given number of degrees.
    Action F means to` move forward by the given value in the direction the ship is currently facing.

    The ship starts by facing east. Only the L and R actions change the direction the ship is facing.

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
    public static String turn(String start, int turns) {
        HashMap<Integer, String> rotations = new HashMap<>();
        rotations.put(0, "N");
        rotations.put(1, "E");
        rotations.put(2, "S");
        rotations.put(3, "W");
        HashMap<String, Integer> directions = new HashMap<>();
        directions.put("N", 0);
        directions.put("E", 1);
        directions.put("S", 2);
        directions.put("W", 3);

        int newOrientation = directions.get(start) + turns;
        while (newOrientation > 3) {
            newOrientation = newOrientation - 4;
        }
        while (newOrientation < 0) {
            newOrientation = newOrientation + 4;
        }

        return rotations.get(newOrientation);
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day12/ferry.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> input = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            input.add(line);
        }

        int EW = 10;
        int NS = -1;
        String currentlyFacing = "E";

        for (String direction: input) {
            String action = String.valueOf(direction.charAt(0));
            int number = Integer.parseInt(direction.replace(action, ""));
            if (action.equals("F")) {
                action = currentlyFacing;
            }
            switch (action) {
                //Action N means to move the waypoint north by the given value.
                case "N" -> NS = NS - number;
                case "S" -> NS = NS + number;
                case "E" -> EW = EW + number;
                case "W" -> EW = EW - number;
                case "L" -> {
                    int turns = abs(number / 90);
                    while (turns > 3) {
                        turns = turns - 4;
                    }
                    currentlyFacing = turn(currentlyFacing, turns * -1);
                }
                case "R" -> {
                    int turns = abs(number / 90);
                    while (turns > 3) {
                        turns = turns - 4;
                    }
                    currentlyFacing = turn(currentlyFacing, turns);
                }
            }
        }
        System.out.println(input);
        System.out.println("East/West: " + EW);
        System.out.println("North/South: " + NS);
        long sum = abs(EW) + abs(NS);
        System.out.println("Answer: " + sum);
    }
}


