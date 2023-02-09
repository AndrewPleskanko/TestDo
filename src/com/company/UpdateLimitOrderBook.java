package com.company;

public class UpdateLimitOrderBook {
    BestBidItem bestBidItem = new BestBidItem();
    BestAskItem bestAskItem = new BestAskItem();

    public UpdateLimitOrderBook(int price, int size, String type) {

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

