package model.scheduler;

import model.exchangerates.ExchangeRatesParser;

public class ExchangeRatesTask extends ExecutableTask {

    public ExchangeRatesTask() {
        super(12);
    }

    @Override
    public void execute() {
        ExchangeRatesParser exchangeRatesParser = new ExchangeRatesParser();
        exchangeRatesParser.saveCbrRates();
    }
}
