package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class mask {

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day14/mask.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> input = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            input.add(line);
        }

        String mask = "";
        long location;
        long number;
        StringBuilder binaryNum;
        HashMap<Long, Long> mem = new HashMap<>();

        for (String s : input) {
            // if it says "mask" then it's a new mask
            StringBuilder newBinaryNum = new StringBuilder();
            String[] values = s.split("=");
            if (values[0].trim().equals("mask")) {
                mask = s.split("=")[1].trim();
                System.out.println("Mask: " + mask);
            } else {
                // otherwise mem[number] = where you put the value
                location = Long.parseLong(values[0].replace("mem[", "")
                        .replace("]", "").trim());
                //location = Long.parseLong(String.valueOf(values[0].charAt(4)));
                // = number = the value
                number = Integer.parseInt(values[1].trim());
                binaryNum = new StringBuilder(Long.toBinaryString(number));
                // now work backwords from binary val on the mask
                for (int j = 1; j < mask.length() + 1; j++) {
                    // get last char at each
                    if (binaryNum.length() < mask.length()) {
                        // add on your leading zeros
                        for (int k = 0; k < mask.length() - binaryNum.length(); k++) {
                            binaryNum.insert(0, "0");
                        }
                    }
                    String myVal = String.valueOf(binaryNum.charAt(binaryNum.length() - j));
                    String maskVal = String.valueOf(mask.charAt(mask.length() - j));
                    if (!maskVal.equals("X") && !maskVal.equals(myVal)) {
                        // build your new binary value
                        newBinaryNum.insert(0, maskVal);
                    } else {
                        newBinaryNum.insert(0, myVal);
                    }
                }
                // plop your new value into the array at the location specified
                //System.out.println("Original Number: " + number);
                //System.out.println("Original Binary: " + binaryNum);
                //System.out.println("New Binary:      " + newBinaryNum);
                long converted = Long.parseLong(String.valueOf(newBinaryNum), 2);
                mem.put(location, converted);
                System.out.println("Put value " + converted + " at location " + location);

            }
        }
        System.out.println(mem);
        long sum = 0;
        for (long value : mem.values()) {
            sum += value;
        }
        System.out.println(sum);
    }
}


