package com.trading.demo.service;

import ch.algotrader.entity.trade.MarketOrder;
import ch.algotrader.enumeration.Side;
import ch.algotrader.simulation.Simulator;
import com.trading.demo.pojo.MarketPosition;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class OrderHandlingServiceImpl implements OrderHandlingService{

    private final Simulator simulator;

    public OrderHandlingServiceImpl(Simulator simulator) {
        this.simulator = simulator;
    }

    @Override
    public void handleOrder(MarketPosition option, double currentPrice) {
        switch (option) {
            case ENTER_LONG:
            case CLOSE_SHORT:
                enter(currentPrice);
                break;
            case CLOSE_LONG:
            case ENTER_SHORT:
                close(currentPrice);
                break;
            case FLAT:
                break;
        }
    }

    private void close(double currentPrice) {
        this.simulator.setCurrentPrice(currentPrice);
        MarketOrder order = new MarketOrder(Side.SELL, 1);
        simulator.sendOrder(order);
        double balance = this.simulator.getCashBalance();
        System.out.println(format("Closed! Balance is: %f", balance));
    }

    private void enter(double currentPrice) {
        this.simulator.setCurrentPrice(currentPrice);
        MarketOrder order = new MarketOrder(Side.BUY, 1);
        simulator.sendOrder(order);
    }
}
