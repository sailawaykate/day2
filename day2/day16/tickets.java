package day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class tickets {

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day16/tickets.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String[]> input = new ArrayList<>();
        HashMap<String, String[]> rules = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("rules:")) {
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    if (!line.isBlank() && !line.equals("your ticket:")) {
                        String[] stuff = line.split(":\s");
                        String key = stuff[0];
                        String[] numbers = stuff[1].split("-|\sor\s");
                        rules.put(key, numbers);
                    } else {
                        break;
                    }
                }
            }
            if (line.equals("nearby tickets:")) {
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    input.add(line.split(","));
                }
            }
        }

        int invalidNums = 0;

        for (String[] ticket : input) {
            boolean isValid = true;
            for (String s : ticket) {
                // check each ticket val for possible validity
                int evaluate = Integer.parseInt(s);
                ArrayList<Boolean> foundValidNum = new ArrayList<>();
                for (String[] ruleList : rules.values()) {
                    int min1 = Integer.parseInt(ruleList[0]);
                    int max1 = Integer.parseInt(ruleList[1]);
                    int min2 = Integer.parseInt(ruleList[2]);
                    int max2 = Integer.parseInt(ruleList[3]);
                    if ((evaluate >= min1 && evaluate <= max1) || (evaluate >= min2 && evaluate <= max2)) {
                        foundValidNum.add(true);
                    }
                }
                if (!foundValidNum.contains(true)) {
                    isValid = false;
                    invalidNums += evaluate;
                    System.out.println("Invalid ticket found! " + evaluate + " does not meet any criteria.");
                }
            }
            if (!isValid) {
                System.out.println("Current sum of invalid numbers: " + invalidNums);
            }
        }
        System.out.println(input);
    }
}


