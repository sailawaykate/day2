package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class boardingPasses {

    public static int seatRow(final String pass) {
        //FBFBBFFRLR
        int minRow = 0;
        int maxRow = 127;
        int minCol = 0;
        int maxCol = 7;
        int seatRow = 0;
        int seatCol = 0;
        int seatNum = 0;
        final String row = pass.substring(0, pass.length() - 3);
        final String col = pass.substring(pass.length() - 3);

        for (int i = 0; i < row.length(); i++) {
            if (String.valueOf(row.charAt(i)).equals("F")) {
                maxRow = (minRow + maxRow) / 2;
            } else if (String.valueOf(row.charAt(i)).equals("B")) {
                minRow = (minRow + maxRow) / 2 + 1;
            } else { break; }
        }

        for (int i = 0; i < col.length(); i++) {
            if (String.valueOf(col.charAt(i)).equals("L")) {
                maxCol = (minCol + maxCol) / 2;
            } else if (String.valueOf(col.charAt(i)).equals("R")) {
                minCol = (minCol + maxCol) / 2 + 1;
            } else { break; }
        }

        if (minRow == maxRow && minCol == maxCol) {
            seatRow = minRow;
            seatCol = minCol;
            seatNum = (seatRow * 8) + seatCol;
        }
        return seatNum;
    }

    public static ArrayList<String> fileReader() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("day5/boardingPasses.txt"));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();

        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }

    public static void main(String[] args) throws IOException {
        final List<String> boardingPasses = fileReader();
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for (String boardingPass : boardingPasses) {
            int passNum = seatRow(boardingPass);
            list.add(passNum);
            if (passNum >= max) {
                max = passNum;
            }
        }
        System.out.println(max);
        Collections.sort(list);
        System.out.println(list);

        int num = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            int low = list.get(i);
            int high = list.get(i + 1);
            if (low != high - 1) {
                System.out.println(high);
                break;
            }
        }
        System.out.println(num);
    }
}
