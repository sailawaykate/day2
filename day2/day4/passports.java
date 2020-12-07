package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class passports {

    public static ArrayList<String> fileReader() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader("day4/passports.txt"));
        ArrayList<String> passportsList = new ArrayList<>();
        String line = bufReader.readLine();
        StringBuilder currentLine = new StringBuilder();

        while (line != null) {
            if (!line.isBlank()) {
                currentLine.append(line).append(" ");
            } else {
                passportsList.add(String.valueOf(currentLine));
                currentLine.setLength(0);
            }
            line = bufReader.readLine();
        }
        passportsList.add(String.valueOf(currentLine));
        bufReader.close();
        return passportsList;
    }

    public static boolean matchesYear(final int min, final int max, final int inputyear) {
        boolean matches = false;
        if (inputyear >= min && inputyear <= max) {
            matches = true;
        }
        return matches;
    }

    public static boolean matchesHairColor(final String color) {
        boolean matches = false;
        //a # followed by exactly six characters 0-9 or a-f.
        if (String.valueOf(color.charAt(0)).equals("#") && color.length() == 7) {
            if (color.matches("^#?([a-f0-9]{6})")) {
                // contains only listed chars
                matches = true;
            }
        }
        return matches;
    }

    public static boolean matchesHeight(final String height) {
        //hgt (Height) - a number followed by either cm or in:
        //If cm, the number must be at least 150 and at most 193.
        //If in, the number must be at least 59 and at most 76.
        boolean matches = false;
        int num;
        if (height.endsWith("cm")) {
            try {
                num = Integer.parseInt(height.substring(0, height.length() - 2));
            } catch (NumberFormatException nfe) {
                num = 0;
            }
            if (num >= 150 && num <=193) {
                matches = true;
            }
        }

        if (height.endsWith("in")) {
            try {
                num = Integer.parseInt(height.substring(0, height.length() - 2));
            } catch (NumberFormatException nfe) {
                num = 0;
            }
            if (num >= 59 && num <=76) {
                matches = true;
            }
        }
        return matches;
    }

    public static boolean matchesEyeColor(final String color) {
        //ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        boolean matches = false;
        if (color.equals("amb") || color.equals("blu") || color.equals("brn")
        || color.equals("gry") || color.equals("grn") || color.equals("hzl") || color.equals("oth")) {
            matches = true;
        }
        return matches;
    }

    public static boolean matchesPassportId(final String pid) {
        boolean matches = false;
        if (pid.matches("^?([0-9]{9})")) {
            matches = true;
        }
        return matches;
    }

    public static void main(String[] args) throws IOException {
        int validCount = 0;
        final ArrayList<String> passports = fileReader();
        ArrayList<HashMap<String, String>> passportList = new ArrayList<>();
        for (String passport : passports) {
            HashMap<String, String> hashMap = new HashMap<>();
            final Object[] list = Arrays.stream(passport.split(" ")).toArray();
            for (Object item : list) {
                String[] pair = String.valueOf(item).split(":");
                hashMap.put(String.valueOf(pair[0]), String.valueOf(pair[1]));
            }
            passportList.add(hashMap);
        }

        for (HashMap<String, String> each : passportList) {
            if (each.containsKey("byr") &&
                    each.containsKey("iyr") &&
                    each.containsKey("eyr") &&
                    each.containsKey("hgt") &&
                    each.containsKey("hcl") &&
                    each.containsKey("ecl") &&
                    each.containsKey("pid")) {
                int byr;
                int iyr;
                int eyr;
                final String hgt = each.get("hgt");
                final String hcl = each.get("hcl");
                final String ecl = each.get("ecl");
                final String pid = each.get("pid");
                try {
                    byr = Integer.parseInt(each.get("byr"));
                } catch (NumberFormatException nfe) {
                    byr = 0;
                }
                try {
                    iyr = Integer.parseInt(each.get("iyr"));
                } catch (NumberFormatException nfe) {
                    iyr = 0;
                }
                try {
                    eyr = Integer.parseInt(each.get("eyr"));
                } catch (NumberFormatException nfe) {
                    eyr = 0;
                }
                //byr (Birth Year) - four digits; at least 1920 and at most 2002.
                if (matchesYear(1920, 2002, byr)) {
                    System.out.println("Contains valid birth year: " + byr);
                    //iyr (Issue Year) - four digits; at least 2010 and at most 2020.
                    if (matchesYear(2010, 2020, iyr)) {
                        //eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                        System.out.println("Contains valid issue year: " + iyr);
                        if (matchesYear(2020, 2030, eyr)) {
                            //eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                            System.out.println("Contains valid expiration year: " + eyr);
                            //hgt (Height) - a number followed by either cm or in:
                            if (matchesHeight(hgt)) {
                                System.out.println("Contains valid height: " + hgt);
                                //hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                                if (matchesHairColor(hcl)) {
                                    System.out.println("Contains valid hair color: " + hcl);
                                    //ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                                    if (matchesEyeColor(ecl)) {
                                        System.out.println("Contains valid eye color: " + ecl);
                                        // pid (Passport ID) - a nine-digit number, including leading zeroes.
                                        if (matchesPassportId(pid)) {
                                            System.out.println("Contains passport ID: " + pid);
                                            validCount += 1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(passportList.size());
        System.out.println(validCount);
    }
}
