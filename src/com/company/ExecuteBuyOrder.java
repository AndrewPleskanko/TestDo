package com.company;

public class ExecuteBuyOrder {
    BestAskItem bestAskItem = new BestAskItem();

    public ExecuteBuyOrder(int size) {
        if (size > bestAskItem.getBestAskSize()) {
            bestAskItem.setBestAskSize(0);
        } else {
            bestAskItem.setBestAskSize(bestAskItem.getBestAskSize() - size);
        }
    }
}
