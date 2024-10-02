package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public final class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static String getString(String message, String regex) {
        String value = "";
        boolean valid = false;
        do {
            value = getString(message);
            if (value.matches(regex)) {
                valid = true;
            } else {
                System.out.println("Invalid value. Try again.");
            }
        } while (!valid);
        return value;
    }

    public static int getInt(String message) {
        int value = 0;
        boolean valid = false;
        do {
            try {
                value = Integer.parseInt(getString(message));
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Try again.");
            }
        } while (!valid);
        return value;
    }

    public static int getIntPositive(String message) {
        int value = 0;
        boolean valid = false;
        do {
            try {
                value = Integer.parseInt(getString(message));
                if (value >= 0) {
                    valid = true;
                } else {
                    System.out.println("Invalid number. Try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Try again.");
            }
        } while (!valid);
        return value;
    }

    public static Date getDate(String message) {
        Date date = null;
        boolean valid = false;
        do {
            try {
                date = new SimpleDateFormat("yyyy/MM").parse(getString(message));
                valid = true;
            } catch (ParseException ex) {
                System.out.println("Invalid date. Try again.");
            }
        } while (!valid);
        return date;
    }

    public static boolean getBoolean(String message) {
        boolean value = false;
        boolean valid = false;
        do {
            try {
                value = Boolean.parseBoolean(getString(message));
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid boolean. Try again.");
            }
        } while (!valid);
        return value;
    }
}
