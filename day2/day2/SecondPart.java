package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class SecondPart {

    /* Each line gives the password policy and then the password. The password policy indicates the lowest
    and highest number of times a given letter must appear for the password to be valid. For example, 1-3 a
    means that the password must contain a at least 1 time and at most 3 times.
     */
    //8-10 g: gggggggggg

    public static boolean pword(final String stuff) throws Exception {
        final Object[] list = Arrays.stream(stuff.split(" ")).toArray();
        final String[] minMax = String.valueOf(list[0]).split("-");
        final int first = Integer.parseInt(minMax[0]) - 1;
        final int second = Integer.parseInt(minMax[1]) - 1;
        final String letter = String.valueOf(list[1]).replace(":", "");
        final String password = String.valueOf(list[2]);
        int count = 0;
        boolean match = false;

        if (first <= password.length() || second <= password.length()) {
            if (String.valueOf(password.charAt(first)).equals(letter)) {
                count += 1;
            }

            if (String.valueOf(password.charAt(second)).equals(letter)) {
                count += 1;
            }
        }

        if (count == 1) {
            match = true;
        }

        return match;
    }

    public static ArrayList<String> fileReader() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("day2/pwords.txt"));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();

        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }

    public static void main(String[] args) throws Exception {
        int bigCount = 0;
        final ArrayList<String> pwords = fileReader();

        for (int i = 0; i < pwords.toArray().length; i++) {
            if (pword(pwords.get(i))) {
                bigCount += 1;
            }
        }
        System.out.println(bigCount);
    }
}

