package com.trading.demo.config;

import au.com.bytecode.opencsv.CSVReader;
import ch.algotrader.simulation.Simulator;
import ch.algotrader.simulation.SimulatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Configuration
public class Config {

    public static final double INITIAL_CASH_BALANCE = 1000000.0;

    @Bean
    public Simulator simulator() {
        Simulator simulator = new SimulatorImpl();
        simulator.setCashBalance(INITIAL_CASH_BALANCE);
        return simulator;
    }

    @Bean
    public CSVReader csvReader() throws FileNotFoundException {
        return new CSVReader(new FileReader("EUR.USD.csv"));
    }
}
