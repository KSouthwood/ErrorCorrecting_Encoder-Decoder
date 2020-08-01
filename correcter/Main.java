package correcter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        file();
    }

    public static void file() {
        File file = new File();
        final Scanner scanner = new Scanner(System.in);

        System.out.println("Write a mode: ");
        switch (scanner.nextLine()) {
            case "encode":
                file.encodeFile("send.txt",
                        "encoded.txt");
                break;
            case "send":
                file.sendFileWithErrors("encoded.txt",
                        "received.txt");
                break;
            case "decode":
                file.decodeFile("received.txt",
                        "decoded.txt");
                break;
            default:
                break;
        }
    }
//    public static void message() {
//        final Scanner scanner = new Scanner(System.in);
//
//        String input = scanner.nextLine();
//        Message msg = new Message(input);
//        msg.print();
//        msg.encodeMessage();
//        msg.print();
//        msg.makeErrors();
//        msg.print();
//        msg.decodeMessage();
//        msg.print();
//    }

}
