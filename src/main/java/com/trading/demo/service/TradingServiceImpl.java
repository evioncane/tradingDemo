package com.trading.demo.service;

import au.com.bytecode.opencsv.CSVReader;
import com.trading.demo.pojo.BollingerBand;
import com.trading.demo.pojo.DailyRecord;
import com.trading.demo.pojo.MarketPosition;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradingServiceImpl implements TradingService {

    private final CSVReader reader;
    private final OrderHandlingService orderHandlingService;
    private final SignalLogicService signalLogicService;
    private final List<Double> closingPrices;

    public TradingServiceImpl(CSVReader reader, OrderHandlingService orderHandlingService, SignalLogicService signalLogicService) {
        this.reader = reader;
        this.orderHandlingService = orderHandlingService;
        this.signalLogicService = signalLogicService;
        this.closingPrices = new ArrayList<>();
    }

    @Override
    public void trade() throws IOException {

        reader.readNext();
        String[] line;
        while ((line = reader.readNext()) != null) {
            executeAlgorithmSequence(line);
        }
    }

    /**
     * Follows the trading algorithm: import next price from csv -> update Bollinger band -> signal logic -> order handling
     *
     * @param line one line from the csv file
     */
    private void executeAlgorithmSequence(String[] line) {
        DailyRecord dailyRecord = readDailyRecord(line);
        this.closingPrices.add(dailyRecord.getClose());
        Double[] priceArray = getLatestClosingPrices();
        BollingerBand bollingerBand = new BollingerBand(priceArray);
        MarketPosition marketPosition = this.signalLogicService.signalLogic(dailyRecord, bollingerBand);
        this.orderHandlingService.handleOrder(marketPosition, dailyRecord.getOpen());
    }

    private Double[] getLatestClosingPrices() {
        Double[] priceArray;
        if (closingPrices.size() >= 30) {
            priceArray = closingPrices.subList(closingPrices.size()-30, closingPrices.size()-1).toArray(new Double[0]);
        }
        else {
            priceArray = closingPrices.toArray(new Double[0]);
        }
        return priceArray;
    }

    /**
     * Gets one line from the csv file and converts it into a DailyRecord class
     * It automatically updates the BollingerBan
     *
     * @param line one line from the csv file
     */
    private DailyRecord readDailyRecord(String[] line) {
        return new DailyRecord(line[0], line[1], line[2], line[3], line[4]);
    }
}
