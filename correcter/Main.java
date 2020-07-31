package correcter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File();
        file.readWrite();
    }

    public static void message() {
        final Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Message msg = new Message(input);
        msg.print();
        msg.encodeMessage();
        msg.print();
        msg.makeErrors();
        msg.print();
        msg.decodeMessage();
        msg.print();
    }

}
