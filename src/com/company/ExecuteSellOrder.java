package com.company;

public class ExecuteSellOrder {
    BestBidItem bestBidSize = new BestBidItem();

    public ExecuteSellOrder(int size) {
        if (size > bestBidSize.getBestBidSize()) {
            bestBidSize.setBestBidSize(0);
        } else {
            bestBidSize.setBestBidSize(bestBidSize.getBestBidSize() - size);
        }
    }

}
