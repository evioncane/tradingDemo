package com.trading.demo.pojo;

public class BollingerBand {

    private double sma;

    private double low;

    private double high;

    public BollingerBand(Double[] prices) {
        calculateSma(prices);
        calculateHighAndLow(prices);
    }

    private void calculateSma(Double[] prices) {
        double sum = 0;
        for (Double price : prices) {
            sum += price;
        }
        sma = sum/prices.length;
    }

    private void calculateHighAndLow(Double[] prices) {
        double sum = 0;
        for (Double price : prices) {
            sum+=Math.pow((price-sma),2);
        }
        double mean = sum/prices.length;
        double deviation = Math.sqrt(mean);
        this.high = sma + 2*deviation;
        this.low = sma - 2*deviation;
    }

    public double getSma() {
        return sma;
    }

    public double getLow() {
        return low;
    }

    public double getHigh() {
        return high;
    }
}
