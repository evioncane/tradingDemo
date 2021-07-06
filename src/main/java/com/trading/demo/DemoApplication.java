package com.trading.demo;

import com.trading.demo.service.TradingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	private final TradingService tradingService;

	public DemoApplication(TradingService tradingService) {
		this.tradingService = tradingService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		System.out.println("<------------------------ TRADING START ------------------------>");
		this.tradingService.trade();
		System.out.println("<------------------------  TRADING END  ------------------------>");

	}

}
