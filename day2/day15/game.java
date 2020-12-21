package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class game {
    public static int checkIt(int[] list, int currentPosition, int num) {
        for (int x = currentPosition - 1; x >= 0; x--) {
            if (list[x] == num) {
                return currentPosition - x;
            }
        }
        return 0;
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day15/game.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Integer> input = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            input.add(Integer.parseInt(line));
        }

        int[] nums = new int[2020];
        for (int p = 0; p < input.size(); p++) {
            nums[p] = input.get(p);
        }
        int startingNums = input.size();
        System.out.println("Starting numbers: " + input);
        //System.out.println(Arrays.toString(nums));

        for (int i = startingNums; i < nums.length; i++) {
            // start the game
            int lastSaid = nums[i - 1];
            int saidNow = checkIt(nums, i - 1, lastSaid);
            nums[i] = saidNow;
            //System.out.println("Previously spoken: " + lastSaid);
            //System.out.println("Said this turn: " + saidNow);
        }
        System.out.println("Value at 2020: " + nums[2019]);
    }
}


