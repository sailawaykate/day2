package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class seats {
    public static String[][] sitDown(String[][] seats, int rows, int cols) {
        String[][] newSeats;
        newSeats = new String[rows][cols];
        //go through each row in seats
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int occupiedAdjacent = 0;
                String seatHere = seats[i][j];
                // is it the floor here?
                if (seatHere.equals(".")) {
                    newSeats[i][j] = ".";
                }
                // is seat empty/filled?
                if (seatHere.equals("L") || seatHere.equals("#")) {
                    // check row above for occupied seats
                    if (i != 0) { // am I in the top row? no? good.
                        if (j != 0) { // if not in top left corner check NW seat
                            // what's the first seat you can see to the NW?
                            int iCoord = i - 1;
                            int jCoord = j - 1;
                            String seatNW = ".";
                            while (seatNW.equals(".") && iCoord >= 0 && jCoord >= 0) {
                                seatNW = seats[iCoord][jCoord];
                                iCoord--;
                                jCoord--;
                            }
                            if (seatNW.equals("#")) {
                                occupiedAdjacent++;
                            }
                        }
                        if (j < cols - 1) { // if not in top right corner check NE seat
                            // what's the first seat you can see to the NE?
                            int iCoord = i - 1;
                            int jCoord = j + 1;
                            String seatNE = ".";
                            while (seatNE.equals(".") && iCoord >= 0 && jCoord < cols) {
                                seatNE = seats[iCoord][jCoord];
                                iCoord--;
                                jCoord++;
                            }
                            if (seatNE.equals("#")) {
                                occupiedAdjacent++;
                            }
                        }
                        // now check directly above
                        // what's the first seat you can see to the N?
                        int iCoord = i - 1;
                        String seatN = ".";
                        while (seatN.equals(".") && iCoord >= 0) {
                            seatN = seats[iCoord][j];
                            iCoord--;
                        }
                        if (seatN.equals("#")) {
                            occupiedAdjacent++;
                        }
                    }
                    //check same row for occupied seats
                    if (j != 0) { // am I in the first column? no? good.
                        // what's the first seat you can see to the W?
                        int jCoord = j - 1;
                        String seatW = ".";
                        while (seatW.equals(".") && jCoord >= 0) {
                            seatW = seats[i][jCoord];
                            jCoord--;
                        }
                        if (seatW.equals("#")) {
                            occupiedAdjacent++;
                        }
                    }
                    if (j < cols - 1) { // am I in the last column? no? good.
                        // what's the first seat you can see to the E?
                        int jCoord = j + 1;
                        String seatE = ".";
                        while (seatE.equals(".") && jCoord < cols) {
                            seatE = seats[i][jCoord];
                            jCoord++;
                        }
                        if (seatE.equals("#")) {
                            occupiedAdjacent++;
                        }
                    }
                    // check row below for occupied seats
                    if (i < rows - 1) { // am I in the bottom row? no? good.
                        if (j != 0) { // if not in bottom left corner check SW seat
                            // what's the first seat you can see to the SW?
                            int iCoord = i + 1;
                            int jCoord = j - 1;
                            String seatSW = ".";
                            while (seatSW.equals(".") && iCoord < rows && jCoord >= 0) {
                                seatSW = seats[iCoord][jCoord];
                                iCoord++;
                                jCoord--;
                            }
                            if (seatSW.equals("#")) {
                                occupiedAdjacent++;
                            }
                        }
                        if (j < cols - 1) { // if not in bottom right corner check SE seat
                            // what's the first seat you can see to the SE?
                            int iCoord = i + 1;
                            int jCoord = j + 1;
                            String seatSE = ".";
                            while (seatSE.equals(".") && iCoord < rows && jCoord < cols) {
                                seatSE = seats[iCoord][jCoord];
                                iCoord++;
                                jCoord++;
                            }
                            if (seatSE.equals("#")) {
                                occupiedAdjacent++;
                            }
                        }
                        // now check directly below
                        // what's the first seat you can see to the S?
                        int iCoord = i + 1;
                        String seatS = ".";
                        while (seatS.equals(".") && iCoord < rows) {
                            seatS = seats[iCoord][j];
                            iCoord++;
                        }
                        if (seatS.equals("#")) {
                            occupiedAdjacent++;
                        }
                    }
                    // fill the seat/empty the seat
                    if (occupiedAdjacent == 0 && seatHere.equals("L")) {
                        newSeats[i][j] = "#";
                    }
                    if (occupiedAdjacent > 0 && seatHere.equals("L")) {
                        newSeats[i][j] = seatHere;
                    }
                    if (occupiedAdjacent >= 5 && seatHere.equals("#")) {
                        newSeats[i][j] = "L";
                    }
                    if (occupiedAdjacent < 5 && seatHere.equals("#")) {
                        newSeats[i][j] = seatHere;
                    }
                }
            }
        }

        return newSeats;
    }

    public static boolean didAnythingChange(String[][] oldArray, String[][] newArray, int rows, int cols) {
        boolean areThereChanges = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String val1 = oldArray[i][j];
                String val2 = newArray[i][j];
                if (!val1.equals(val2)) {
                    areThereChanges = true;
                    break;
                }
                if (areThereChanges) {
                    break;
                }
            }
        }
        return areThereChanges;
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day11/seats.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> rawInput = new ArrayList<>();
        String[][] seats;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            rawInput.add(line);
        }

        int rows = rawInput.size();
        int cols = rawInput.get(0).length();
        seats = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            String row = rawInput.get(i);
            for (int j = 0; j < row.length(); j++) {
                seats[i][j] = String.valueOf(row.charAt(j));
            }
        }

        String[][] newArrangement;
        newArrangement = sitDown(seats, rows, cols);
        int numberOfChangedSeatingArrangements = 1;

        boolean wereThereChanges = true;

        while (wereThereChanges) {
            String[][] oldArrangement = newArrangement;
            newArrangement = sitDown(oldArrangement, rows, cols);
            wereThereChanges = didAnythingChange(oldArrangement, newArrangement, rows, cols);
        }

        // ok now count occupied seats "#"
        int occupiedSeats = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (newArrangement[i][j].equals("#")) {
                    occupiedSeats++;
                }
            }
        }

        System.out.println(occupiedSeats + " occupied seats");
        System.out.println(numberOfChangedSeatingArrangements);
        for (int i = 0; i < rows; i++) {
            System.out.println(Arrays.toString(newArrangement[i]));
        }
    }
}


