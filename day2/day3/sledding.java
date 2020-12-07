package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class sledding {

    public static ArrayList<String> fileReader() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("day3/slope.txt"));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();

        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }

    public static long trees(final int right, final int down) throws IOException {
        final ArrayList<String> slope = fileReader();
        int x = 0;
        int y = 0;
        long trees = 0;
        final String tree = "#";
        StringBuilder currentLine = new StringBuilder();

        while (y < slope.toArray().length) {
            currentLine = new StringBuilder(slope.get(y));

            while (x >= currentLine.length()) {
                currentLine.append(currentLine);
            }

            if (String.valueOf(currentLine.charAt(x)).equals(tree)) {
                trees += 1;
            }

            x += right;
            y += down;
        }
        return trees;
    }

    public static void main(String[] args) throws Exception {
        /*
        Right 1, down 1.
        Right 3, down 1. (This is the slope you already checked.)
        Right 5, down 1.
        Right 7, down 1.
        Right 1, down 2.
         */
        final long r1d1 = trees(1, 1);
        final long r3d1 = trees(3, 1);
        final long r5d1 = trees(5, 1);
        final long r7d1 = trees(7, 1);
        final long r1d2 = trees(1, 2);
        System.out.println(r1d1);
        System.out.println(r3d1);
        System.out.println(r5d1);
        System.out.println(r7d1);
        System.out.println(r1d2);

        final long total = (r1d1 * r3d1 * r5d1 * r7d1 * r1d2);
        System.out.println(total);
    }
}
