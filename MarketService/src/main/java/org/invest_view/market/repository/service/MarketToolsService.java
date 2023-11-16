package org.invest_view.market.repository.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.market_tools.MarketTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
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
        }

        marketTool.setValues(result);
        return marketTool;
    }

    private List<Double> calculateRSI(List<Issuer> issuerData) {
        List<Double> values = new ArrayList<>();

        double positiveDays = 0;
        double negativeDays = 0;

        for (Issuer issuer : issuerData) {
            if(issuer.getChange()>=0) positiveDays+=issuer.getChange();
            else negativeDays-=issuer.getChange();
        }

        return values;
    }

    private List<Double> calculateEMA100(List<Issuer> issuerData) {
        List<Double> values = new ArrayList<>();
        values.add(issuerData.get(0).getPriceClose());

        for (int i = 1; i < issuerData.size(); i++) {
            double previousClosePricesSum = 0;
            for (int j = 0; j <= i; j++) {
                previousClosePricesSum+=issuerData.get(j).getPriceClose();
            }
            double emaValue = previousClosePricesSum/i+1;
            values.add(emaValue);
        }

        return values;
    }

//    private List<Double> calculateEMA200(List<Issuer> issuerData) {
//        List<Double> values = new ArrayList<>();
//
//        issuerData.forEach(issuer -> {
//
//        });
//
//        return values;
//    }
}
