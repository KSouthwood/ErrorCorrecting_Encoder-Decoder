package correcter;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        makeErrors(input);
    }

    /**
     * Introduce an error for every three char's in a String
     * @param input string to be changed
     */
    private static void makeErrors(String input) {
        Random rnd = new Random();
        int start = 0;
        int end = Math.min(3, input.length());
        String charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
        StringBuilder stream = new StringBuilder(input);

        do {
            stream.setCharAt(start + rnd.nextInt(end - start),
                    charset.charAt(rnd.nextInt(charset.length())));
            start = end;
            end = Math.min((end + 3), input.length());
        } while (start != end);

        System.out.println(stream.toString());
    }
}
