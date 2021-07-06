package com.trading.demo.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyRecord {
    private final LocalDate localDate;
    private final Double open;
    private final Double low;
    private final Double high;
    private final Double close;

    public DailyRecord(String localDate, String open, String low, String high, String close) {
        this.localDate = LocalDate.parse(localDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        this.open = Double.parseDouble(open);
        this.low = Double.parseDouble(low);
        this.high = Double.parseDouble(high);
        this.close = Double.parseDouble(close);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public Double getLow() {
        return low;
    }

    public Double getHigh() {
        return high;
    }
}
