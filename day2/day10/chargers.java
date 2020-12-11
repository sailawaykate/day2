package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class chargers {

    public static void getIncrements(ArrayList<Integer> chargers) {
        int countSingles = 0;
        int countTriples = 0;
        for (int i = 0; i < chargers.size() - 1; i++) {
            int diff = chargers.get(i + 1) - chargers.get(i);
            if (diff == 1) {
                countSingles++;
            }
            if (diff == 3) {
                countTriples++;
            }
        }
        System.out.println("There were " + countSingles + " increments of 1");
        System.out.println("There were " + countTriples + " increments of 3");
        int product = countSingles * countTriples;
        System.out.println("Multiplied: " + product);
    }

    private static void countEm(List<Integer> chargers) {
        Long[] countList = new Long[chargers.size()];
        Arrays.fill(countList, 0L);
        countList[0] = 1L;
        for (int i = 0; i < chargers.size(); i++) {
            long adapter1 = chargers.get(i);
            for (int j = i - 1; j >= 0; j--) {
                long adapter2 = chargers.get(j);
                if (adapter1 - adapter2 < 4) {
                    countList[i] += countList[j];
                } else {
                    break;
                }
            }
        }
        System.out.println(countList[chargers.size() - 1]);
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day10/chargers.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Integer> chargers = new ArrayList<>();
        chargers.add(0);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            chargers.add(Integer.parseInt(line));
        }

        Collections.sort(chargers);
        chargers.add(chargers.get(chargers.size()-1)+3);

        //getIncrements(chargers);
        countEm(chargers);
    }
}


