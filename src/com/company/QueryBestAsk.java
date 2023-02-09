package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class QueryBestAsk implements MyFileWriter {

    @Override
    public void writeLine(int bestPrice, int bestSize) throws IOException {
        FileWriter fw = new FileWriter("output.txt", true);
        fw.write(bestPrice + "," + bestSize + "\n");
        fw.close();
    }
}
