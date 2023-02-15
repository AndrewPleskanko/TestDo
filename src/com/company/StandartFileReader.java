package com.company;

import java.io.*;
import java.util.HashMap;

public class StandartFileReader implements FileReader {
    private BestAskItem bestAskItem;
    private BestBidItem bestBidItem;
    private CheapestAsk cheapestAsk;

    private File file;
    private WriteLineToFile writeLinetoFile;


    public StandartFileReader(BestAskItem bestAskItem, BestBidItem bestBidItem,
                              CheapestAsk cheapestAsk, File file, WriteLineToFile writeLinetoFile) {
        this.bestAskItem = bestAskItem;
        this.bestBidItem = bestBidItem;
        this.cheapestAsk = cheapestAsk;
        this.file = file;
        this.writeLinetoFile = writeLinetoFile;
    }

    @Override
    public void readLines() throws IOException {
        BufferedReader br = new BufferedReader(new java.io.FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            switch (data[0]) {
                case "u":
                    updateLimitOrderBook(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]);
                    break;
                case "q":
                    switch (data[1]) {
                        case "best_bid" -> {
                            int bestBidSize = bestBidItem.getBestBidSize();
                            int bestBidPrice = bestBidItem.getBestBidPrice();
                            writeLinetoFile.writeLine(bestBidPrice, bestBidSize);
                        }
                        case "best_ask" -> {
                            int bestAskSize = bestAskItem.getBestAskSize();
                            int bestAskPrice = bestAskItem.getBestAskPrice();
                            writeLinetoFile.writeLine(bestAskPrice, bestAskSize);
                        }
                        case "size" -> {
                            int price = Integer.parseInt(data[2]);
                            int size = 0;
                            if (price == bestBidItem.getBestBidPrice()) {
                                size = bestBidItem.getBestBidSize();
                            } else if (price == bestAskItem.getBestAskPrice()) {
                                size = bestAskItem.getBestAskSize();
                            }
                            writeLinetoFile.writeLine(size, 0);
                        }
                    }
                    break;
                case "o":
                    switch (data[1]) {
                        case "buy" -> {
                            int size = Integer.parseInt(data[2]);
                            if (size > cheapestAsk.getCheapAskSize()) {
                                cheapestAsk.setCheapAskSize(0);
                            } else {
                                cheapestAsk.setCheapAskSize(cheapestAsk.getCheapAskSize() - size);
                            }
                        }
                        case "sell" -> {
                            int size = Integer.parseInt(data[2]);
                            if (size > bestBidItem.getBestBidSize()) {
                                bestBidItem.setBestBidSize(0);
                            } else {
                                bestBidItem.setBestBidSize(bestBidItem.getBestBidSize() - size);
                            }
                        }
                    }
                    break;
            }
        }
        br.close();
    }

    private void updateLimitOrderBook(int price, int size, String type) {
        switch (type) {
            case "bid":
                if (size == 0) {
                    if (price == bestBidItem.getBestBidPrice()) {
                        bestBidItem.setBestBidPrice(0);
                        bestBidItem.setBestBidSize(0);
                    }
                } else {
                    if (price >= bestBidItem.getBestBidPrice()) {
                        bestBidItem.setBestBidPrice(price);
                        bestBidItem.setBestBidSize(size);
                    }
                }
                break;
            case "ask":
                if (size == 0) {
                    if (price == bestAskItem.getBestAskPrice()) {
                        bestAskItem.setBestAskPrice(0);
                        bestAskItem.setBestAskSize(0);
                    }
                    if (price == cheapestAsk.getCheapAskPrice()) {
                        cheapestAsk.setCheapAskSize(0);
                        cheapestAsk.setCheapAskPrice(0);
                    }
                } else {
                    if (price >= bestAskItem.getBestAskPrice()) {
                        bestAskItem.setBestAskPrice(price);
                        bestAskItem.setBestAskSize(size);
                    }
                    if (price <= cheapestAsk.getCheapAskPrice()) {
                        cheapestAsk.setCheapAskPrice(price);
                        cheapestAsk.setCheapAskSize(size);
                    }
                }

                break;
        }
    }
}
