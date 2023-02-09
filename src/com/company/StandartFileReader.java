package com.company;

import java.io.*;

public class StandartFileReader implements FileReader {
    private BestAskItem bestAskItem;
    private BestBidItem bestBidItem;

    private File file;
    private WriteLineToFile writeLinetoFile;

    public StandartFileReader(File file, BestAskItem bestAskItem, BestBidItem bestBidItem,) {
        this.bestAskItem = bestAskItem;
        this.bestBidItem = bestBidItem;
        this.file = file;
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
                            if (size > bestAskItem.getBestAskSize()) {
                                bestAskItem.setBestAskSize(0);
                            } else {
                                bestAskItem.setBestAskSize(bestAskItem.getBestAskSize() - size);
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

    private static void updateLimitOrderBook(int price, int size, String type) {
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
                } else {
                    if (price <= bestAskItem.getBestAskPrice()) {
                        bestAskItem.setBestAskPrice(price);
                        bestAskItem.setBestAskSize(size);
                    }
                }
                break;
        }
    }

}
