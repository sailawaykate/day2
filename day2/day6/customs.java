package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class customs {

    public static ArrayList<ArrayList<String>> fileReader() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("day6/customs.txt"));
        ArrayList<ArrayList<String>> passportsList = new ArrayList<>();
        String line = bufReader.readLine();
        ArrayList<String> group = new ArrayList<>();

        while (line != null) {
            if (!line.isBlank()) {
                group.add(line);
            } else {
                passportsList.add(group);
                group = new ArrayList<>();
            }
            line = bufReader.readLine();
        }
        passportsList.add(group);
        bufReader.close();
        return passportsList;
    }

    public static int countUniqueCharacters(ArrayList<String> input) {
        boolean[] isItThere = new boolean[Character.MAX_VALUE];

        for (String answer : input) {
            for (int i = 0; i < answer.length(); i++) {
                isItThere[answer.charAt(i)] = true;
                for (String answer2 : input) {
                    if (!answer2.contains(String.valueOf(answer.charAt(i)))) {
                        isItThere[answer.charAt(i)] = false;
                        break;
                    }
                }
            }
        }

        int count = 0;
        for (boolean b : isItThere) {
            if (b) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        final ArrayList<ArrayList<String>> customs = fileReader();
        int count = 0;
        for (ArrayList<String> c : customs) {
            count += countUniqueCharacters(c);
        }

        System.out.println(count);
    }
}
