package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        File files = new File("output.txt");
        BestAskItem bestAskItem = new BestAskItem();
        BestBidItem bestBidItem = new BestBidItem();
        CheapestAsk cheapestAsk = new CheapestAsk();
        WriteLineToFile writeLinetoFile = new WriteLineToFile();

        StandartFileReader fileRead = new StandartFileReader(bestAskItem, bestBidItem, cheapestAsk, file, writeLinetoFile);
        fileRead.readLines();
    }

}

