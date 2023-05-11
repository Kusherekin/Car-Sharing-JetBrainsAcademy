package carsharing.utils;

import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);


    public static int getInt() {
        return SCANNER.nextInt();
    }

    public static String getString() {
        return SCANNER.nextLine();
    }
}