package correcter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class File {
    public void readWrite() {
        Random rnd = new Random();
        try (FileInputStream infile = new FileInputStream("./send.txt");
            FileOutputStream outfile = new FileOutputStream("./received.txt")) {
            for (int data = infile.read(); data != -1; data = infile.read()) {
                data ^= 1 << rnd.nextInt(8);
                outfile.write(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
