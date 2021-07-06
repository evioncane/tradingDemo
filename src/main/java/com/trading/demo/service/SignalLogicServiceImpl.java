package com.trading.demo.service;

import com.trading.demo.pojo.BollingerBand;
import com.trading.demo.pojo.DailyRecord;
import com.trading.demo.pojo.MarketPosition;
import org.springframework.stereotype.Service;

@Service
public class SignalLogicServiceImpl implements SignalLogicService {

    private MarketPosition currentMarketPosition;

    public SignalLogicServiceImpl() {
        this.currentMarketPosition = MarketPosition.FLAT;
    }

    @Override
    public MarketPosition signalLogic(DailyRecord dailyRecord, BollingerBand bollingerBand) {
        if (dailyRecord.getClose() < bollingerBand.getLow() && currentMarketPosition.equals(MarketPosition.FLAT)) {
            currentMarketPosition = MarketPosition.ENTER_LONG;
            return MarketPosition.ENTER_LONG;
        }
        else if (dailyRecord.getClose() > bollingerBand.getHigh()  && currentMarketPosition.equals(MarketPosition.FLAT)) {
            currentMarketPosition = MarketPosition.ENTER_SHORT;
            return MarketPosition.ENTER_SHORT;
        }
        else if (dailyRecord.getClose() > bollingerBand.getSma()  && currentMarketPosition.equals(MarketPosition.ENTER_LONG)) {
            currentMarketPosition = MarketPosition.CLOSE_LONG;
            return MarketPosition.CLOSE_LONG;
        }
        else if (dailyRecord.getClose() < bollingerBand.getSma()  && currentMarketPosition.equals(MarketPosition.ENTER_SHORT) ) {
            currentMarketPosition = MarketPosition.CLOSE_SHORT;
            return MarketPosition.CLOSE_SHORT;
        }
        return MarketPosition.FLAT;
    }
}
