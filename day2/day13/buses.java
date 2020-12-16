package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class buses {

    public static long earliestTime(long min, long busTime) {
        long addedTime = busTime;
        while (addedTime < min) {
            addedTime += busTime;
        }
        return addedTime;
    }

    public static void harmonicConvergence() {
        // given first number n, this number will always be leaving at increment n
        // get second number y, and find all instances where y leaves at n+1
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day13/buses.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> input = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            input.add(line);
        }

        int myTime = Integer.parseInt(input.get(0));
        String[] buses = input.get(1).split(",");

        System.out.println(myTime);
        System.out.println(Arrays.toString(buses));
        long minDiff = 999999999L;
        long busTime = 0L;

        for (String bus : buses) {
            if (!bus.equals("x")) {
                long time = earliestTime(myTime, Long.parseLong(bus));
                long diff = time - myTime;
                if (diff < minDiff) {
                    minDiff = diff;
                    busTime = Integer.parseInt(bus);
                }
                System.out.println("Bus # " + bus + " earliest time: " + time);
            }
        }
        System.out.println("Min diff: " + minDiff);
        System.out.println("Bus time: " + busTime);
        long answer = busTime * minDiff;
        System.out.println("Answer: " + answer);

    }
}


