package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class WriteLineToFile implements FileWrite {


    @Override
    public void writeLine(int bestPrice, int bestSize) throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        if (bestSize == -1) {
            fw.write(bestPrice + "\n");
        } else {
            fw.write(bestPrice + "," + bestSize + "\n");
        }
        fw.close();
    }
}


