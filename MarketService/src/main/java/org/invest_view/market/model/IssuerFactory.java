package org.invest_view.market.model;

public class IssuerFactory {

    public static Issuer create(String secId, String name, double openPrice, String date) {
        return new Issuer(secId, name, 0, 0, 0, openPrice, 0, date, 0);
    }

    public static Issuer create(String secId, String name, double lowPrice, double highPrice, double nowPrice) {
        return new Issuer(secId, name, lowPrice, highPrice, nowPrice, 0, 0, null, 0);
    }

    public static Issuer create(String secId, String name, String date, double openPrice, double nowPrice, double change) {
        return new Issuer(secId, name, 0, 0, nowPrice, openPrice, change/nowPrice*100, date, change);
    }

    public static Issuer create(String secId, String name, double lowPrice, double highPrice, String date) {
        return new Issuer(secId, name, lowPrice, highPrice, 0, 0, 0, date, 0);
    }
}
