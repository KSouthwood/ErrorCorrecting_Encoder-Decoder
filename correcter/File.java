package correcter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class File {
    private final int[] BIT_PAIRS = {0b11000000, 0b00110000, 0b00001100, 0b00000011};
    private final int[] BIT_PATTS = {0b10000000, 0b01000000, 0b00100000, 0b00010000,
                                     0b00001000, 0b00000100, 0b00000010, 0b00000001};

    public void sendFileWithErrors(String sendFile, String receiveFile) {
        Random rnd = new Random();
        try (FileInputStream infile = new FileInputStream(sendFile);
            FileOutputStream outfile = new FileOutputStream(receiveFile)) {
            for (int data = infile.read(); data != -1; data = infile.read()) {
                data ^= 1 << rnd.nextInt(8);
                outfile.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void encodeFile(String readFile, String writeFile) {
        byte data_out = 0;
        int parity = 0;
        int pairs = 0;
        boolean data_written = false;

        try (FileInputStream infile = new FileInputStream(readFile);
             FileOutputStream outfile = new FileOutputStream(writeFile)) {
            for (int data_in = infile.read(); data_in != -1; data_in = infile.read()) {
                // loop through the bits in the current data byte
                for (int bit = 0; bit < 8; bit++) {
                    data_written = false;   // if we're processing bits, we have more to write

                    // check if the current bit is a 1
                    if ((data_in & BIT_PATTS[bit]) != 0) {
                        data_out += BIT_PAIRS[pairs];   // add the correct bit pair
                        parity++;
                    }

                    // have we checked 3 bits?
                    if (++pairs == 3) {
                        // add the correct parity bit
                        if (parity % 2 == 1) {
                            data_out += BIT_PAIRS[pairs];
                        }

                        // write the current data out byte, and reset everything
                        outfile.write(data_out);
                        data_written = true;
                        pairs = 0;
                        parity = 0;
                        data_out = 0b00000000;
                    }
                }
            }

            // check if the last action of the last byte was to write out
            if (!data_written) {
                if (parity % 2 == 1) {
                    data_out += BIT_PAIRS[3];
                }
                outfile.write(data_out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decodeFile(String receiveFile, String decodeFile) {
        int[] pairs = {0, 0, 0, 0};
        int bit = 0;
        byte data_out = 0;
        int parity;

        try (FileInputStream infile = new FileInputStream(receiveFile);
             FileOutputStream outfile = new FileOutputStream(decodeFile)) {
            for (int data_in = infile.read(); data_in != -1; data_in = infile.read()) {
                parity = 0;
                for (int pair = 0; pair < 3; pair++) {
                    pairs[pair] = data_in & BIT_PAIRS[pair];
                    parity += pairs[pair] == BIT_PAIRS[pair] ? 1 : 0;   // add to parity if the bit pair is correct
                }

                pairs[3] = data_in & BIT_PAIRS[3];
                parity %= 2;

                if (pairs[3] == 0 || pairs[3] == BIT_PAIRS[3]) { // parity pair is correct, so a data pair is wrong
                    int data_parity = pairs[3] == 0 ? 0 : 1;
                    for (int pair = 0; pair < 3; pair++) {
                        if (pairs[pair] > 0 && pairs[pair] != BIT_PAIRS[pair]) {
                            // found the incorrect data pair, set to 0 if the calculated parity and data parity match
                            pairs[pair] = (parity ^ data_parity) == 0 ? 0 : BIT_PAIRS[pair];
                            break;
                        }
                    }
                }

                // all data pairs are correct at this point, build the decoded byte
                for (int pair = 0; pair < 3; pair++) {
                    data_out += pairs[pair] == 0 ? 0 : BIT_PATTS[bit];
                    if (++bit == 8) {
                        outfile.write(data_out);
                        bit = 0;
                        data_out = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
