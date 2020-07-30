package correcter;

import java.util.Random;

public class Message {
    private StringBuilder message;

    Message(String message) {
        this.message = new StringBuilder(message);
    }

    /**
     * Encode the message by tripling each character in the string
     */
    public void encodeMessage() {
        for (int i = message.length() - 1; i >= 0; i--) {
            message.insert(i, String.valueOf(message.charAt(i)).repeat(2));
        }
    }

    /**
     * Introduce an error into the string, one changed character per three
     */
    public void makeErrors() {
        Random rnd = new Random();
        String charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < message.length(); i += 3) {
            message.setCharAt(i + rnd.nextInt(3),
                    charset.charAt(rnd.nextInt(charset.length())));
        }
    }

    /**
     * Decode the message by comparing chunks of three characters and seeing which character is repeated
     */
    public void decodeMessage() {
        StringBuilder decoded = new StringBuilder();

        for (int i = 0; i < message.length(); i += 3) {
            if (message.charAt(i) == message.charAt(i + 1)) {
                decoded.append(message.charAt(i));
            } else if (message.charAt(i) == message.charAt(i + 2)) {
                decoded.append(message.charAt(i));
            } else {
                decoded.append(message.charAt(i + 1));
            }
        }

        message = decoded;
    }

    public void print() {
        System.out.println(message.toString());
    }
}
