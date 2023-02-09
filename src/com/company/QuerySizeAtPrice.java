package com.company;

import java.io.FileWriter;
import java.io.IOException;

public class QuerySizeAtPrice implements MyFileWriter {
    BestBidItem bestBidItem = new BestBidItem();
    BestAskItem bestAskItem = new BestAskItem();


    @Override
    public void writeLine(int bestPrice, int bestSize) throws IOException {
        int size = 0;
        if (bestPrice == bestBidItem.getBestBidPrice()) {
            size = bestBidItem.getBestBidSize();
        } else if (bestPrice == bestAskItem.getBestAskPrice()) {
            size = bestAskItem.getBestAskSize();
        }

        FileWriter fw = new FileWriter("output.txt", true);
        fw.write(size + "\n");
        fw.close();
    }
}
