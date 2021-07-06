package com.trading.demo.service;

import com.trading.demo.pojo.BollingerBand;
import com.trading.demo.pojo.DailyRecord;
import com.trading.demo.pojo.MarketPosition;

public interface SignalLogicService {
    MarketPosition signalLogic(DailyRecord dailyRecord, BollingerBand bollingerBand);
}
