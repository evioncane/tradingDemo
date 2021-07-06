package com.trading.demo.service;

import com.trading.demo.pojo.MarketPosition;

public interface OrderHandlingService {
    void handleOrder(MarketPosition option, double currentPrice);
}
