package org.invest_view.market.repository.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.market_tools.MarketTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class MarketToolsService {

    MarketTool marketTool;

    public MarketTool calculate()  {
        List<Double> result = null;
        List<Issuer> issuerData = marketTool.getIssuerData();

        switch (marketTool.getTool()) {
            case RSI -> {
                result = calculateRSI(issuerData);
            }
            case EMA100 -> {
                result = calculateEMA100(issuerData);
            }
            case EMA200 -> {
                result = calculateEMA200(issuerData);
            }
        }

        marketTool.setValues(result);
        return marketTool;
    }

    private List<Double> calculateRSI(List<Issuer> issuerData) {
        List<Double> values = new ArrayList<>();

        issuerData.forEach(issuer -> {

        });

        return values;
    }

    private List<Double> calculateEMA100(List<Issuer> issuerData) {
        List<Double> values = new ArrayList<>();

        issuerData.forEach(issuer -> {

        });

        return values;
    }

    private List<Double> calculateEMA200(List<Issuer> issuerData) {
        List<Double> values = new ArrayList<>();

        issuerData.forEach(issuer -> {

        });

        return values;
    }
}
