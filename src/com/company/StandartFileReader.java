package com.company;

import java.io.*;

public class StandartFileReader implements MyFileReader {
    private BestAskItem bestAskItem;
    private BestBidItem bestBidItem;
    private QueryBestBid queryBestBid;
    private QueryBestAsk queryBestAsk;
    private QuerySizeAtPrice querySizeAtPrice;
    private File file;

    public StandartFileReader(File file, BestAskItem bestAskItem, BestBidItem bestBidItem,
                              QueryBestBid queryBestBid, QueryBestAsk queryBestAsk, QuerySizeAtPrice querySizeAtPrice) {
        this.bestAskItem = bestAskItem;
        this.bestBidItem = bestBidItem;
        this.file = file;
        this.queryBestBid = queryBestBid;
        this.queryBestAsk = queryBestAsk;
        this.querySizeAtPrice = querySizeAtPrice;
    }

    @Override
    public void readLines() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            switch (data[0]) {
                case "u":
                    new UpdateLimitOrderBook(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]);
                    break;
                case "q":
                    switch (data[1]) {
                        case "best_bid" -> {
                            int bestBidSize = bestBidItem.getBestBidSize();
                            int bestBidPrice = bestBidItem.getBestBidPrice();
                            queryBestBid.writeLine(bestBidPrice, bestBidSize);
                        }
                        case "best_ask" -> {
                            int bestAskSize = bestAskItem.getBestAskSize();
                            int bestAskPrice = bestAskItem.getBestAskPrice();
                            queryBestAsk.writeLine(bestAskPrice, bestAskSize);
                        }
                        case "size" -> {
                            int price = Integer.parseInt(data[2]);
                            querySizeAtPrice.writeLine(price, 0);
                        }
                    }
                    break;
                case "o":
                    switch (data[1]) {
                        case "buy" -> {
                            int price = Integer.parseInt(data[2]);
                             new ExecuteBuyOrder(price);
                        }
                        case "sell" -> new ExecuteSellOrder(Integer.parseInt(data[2]));
                    }
                    break;
            }
        }
        br.close();
    }
}
