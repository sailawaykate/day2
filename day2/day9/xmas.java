package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class xmas {

    public static boolean checkForMatch(ArrayList<Long> list, long sum) {
        HashSet<Long> potentialMatches = new HashSet<>();
        boolean hasMatch = false;
        for (long l : list) {
            long temp = sum - l;

            // checking for condition
            if (potentialMatches.contains(temp)) {
                hasMatch = true;
                break;
            }
            potentialMatches.add(l);
        }
        return hasMatch;
    }

    public static long findMinMaxSum(ArrayList<Long> nums, int start, int end) {
        long min = 555555555;
        long max = 0;
        for (int i = start; i < end; i++) {
            long number = nums.get(i);
            if (number <= min) {
                min = number;
            }
            if (number >= max) {
                max = number;
            }
        }
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        return min + max;
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day9/xmas.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Long> nums = new ArrayList<>();
        int leadIn = 25;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            nums.add(Long.parseLong(line));
        }

        long sum = 0;
        for (int i = leadIn; i < nums.size(); i++) {
            ArrayList<Long> checklist = new ArrayList<>();
            sum = nums.get(i);
            for (int build = i - leadIn; build < i; build++) {
                checklist.add(nums.get(build));
            }
            if (!checkForMatch(checklist, sum)) {
                System.out.println(sum + " has no matches");
                break;
            }
        }

        ArrayList<Long> contiguousNumbers = new ArrayList<>();
        int movingTarget = 0;

        for (int k = 0; k < nums.size(); k++) {
            contiguousNumbers.add(nums.get(k));
            long runningTotal = 0;
            for (int i = 0; i < contiguousNumbers.size(); i++) {
                long values = contiguousNumbers.get(i);
                runningTotal += values;
                if (runningTotal == sum) {
                    System.out.println("Start of Array: " + contiguousNumbers.get(0) +
                            " found at position " + movingTarget);
                    int endPosition = movingTarget + i;
                    System.out.println("End of Array: " + values + " found at position " + endPosition);
                    System.out.println("Sum of min & max: " + findMinMaxSum(nums, movingTarget, endPosition));
                    break;
                }
            }
            if (runningTotal > sum) {
                // remove first value from contiguousNumbers and break
                contiguousNumbers.remove(0);
                movingTarget++;
            }
            if (runningTotal == sum) {
                break;
            }
        }
    }
}


