package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class game {
    /*
    nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6

This is an infinite loop: with this sequence of jumps, the program will run forever. The moment the program tries to run any instruction a second time, you know it will never terminate.

Immediately before the program would run an instruction a second time, the value in the accumulator is 5.

Run your copy of the boot code. Immediately before any instruction is executed a second time, what value is in the accumulator?
     */
    public static ArrayList<ArrayList<Object>> map(ArrayList<String> list) {
        ArrayList<ArrayList<Object>> yaBoi = new ArrayList<>();

        for (String item : list) {
            ArrayList<Object> bois = new ArrayList<>();
            String[] bits = item.split(" ");
            bois.add(bits[0]);
            String upDown = bits[1].substring(0, 1);
            bois.add(upDown);
            int num = Integer.parseInt(bits[1].replace(upDown, ""));
            bois.add(num);
            yaBoi.add(bois);
        }
        return yaBoi;
    }

    public static List<Object> checkYourself(ArrayList<ArrayList<Object>> yaBois) {
        int acceleromater = 0;
        ArrayList<Integer> beenThereDoneThat = new ArrayList<>();
        int mLastValue = 0;
        boolean finished = true;

        for (int i = 0; i < yaBois.size();) {
            if (beenThereDoneThat.contains(i)) {
                //hold up
                finished = false;
                break;
            } else {
                beenThereDoneThat.add(i);
            }
            String doIt = (String) yaBois.get(i).get(0);
            String upOrDown = (String) yaBois.get(i).get(1);
            int numbah = (int) yaBois.get(i).get(2);
            switch (doIt) {
                case "nop":
                    // thank u, next
                    mLastValue = i;
                    i++;
                    break;
                case "acc":
                    //stomp that pedal
                    if (upOrDown.equals("+")) {
                        acceleromater += numbah;
                        mLastValue = i;
                        i++;
                    } else if (upOrDown.equals("-")) {
                        acceleromater = acceleromater - numbah;
                        mLastValue = i;
                        i++;
                    }
                    break;
                case "jmp":
                    //jump around
                    if (upOrDown.equals("+") && numbah != 0) {
                        mLastValue = i;
                        i += numbah;
                        break;
                    } else if (upOrDown.equals("-") && numbah != 0) {
                        mLastValue = i;
                        i = i - numbah;
                        break;
                    } else {
                        beenThereDoneThat.add(i);
                    }
                    break;
            }
        }
        return Arrays.asList(acceleromater, mLastValue, beenThereDoneThat.size(), finished);
    }

    public static void main(String... args) throws FileNotFoundException {
        File file = new File("day8/game.txt");
        Scanner sc = new Scanner(file);
        ArrayList<String> commands = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            commands.add(line);
        }

        ArrayList<ArrayList<Object>> yaBois = map(commands);
        List<Object> theDigits = checkYourself(yaBois);
        boolean didItFinish = (boolean) theDigits.get(3);
        List<Object> dang = new ArrayList<>();

        int counter = 0;
            while (!didItFinish) {
                yaBois = map(commands);
                String command = String.valueOf(yaBois.get(counter).get(0));

                switch (command) {
                    case "nop" -> yaBois.get(counter).set(0, "jmp");
                    case "jmp" -> yaBois.get(counter).set(0, "nop");
                    default -> throw new IllegalStateException("Unexpected value: " + command);
                }
                dang = checkYourself(yaBois);
                didItFinish = (boolean) dang.get(3);
                counter++;
            }
                System.out.println(counter - 1);
                System.out.println(dang);
            }
    }

