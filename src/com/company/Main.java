package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("input.txt");
        File files = new File("output.txt");
        BestAskItem bestAskItem = new BestAskItem();
        BestBidItem bestBidItem = new BestBidItem();

        StandartFileReader fileRead = new StandartFileReader(file, bestAskItem, bestBidItem);
        fileRead.readLines();
    }

}

