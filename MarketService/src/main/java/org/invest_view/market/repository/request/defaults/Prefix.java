package org.invest_view.market.repository.request.defaults;

public enum Prefix {
    DEFAULT_GET_HISTORY_PREFIX ("https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/"),

    DEFAULT_GET_NOW_PREFIX ("https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities/"),

    DEFAULT_GET_ALL_ISSUERS_PREFIX ("https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities");

    //DEFAULT_GET_USER_ISSUERS_PREFIX("http://localhost:8090/users/");

    private final String value;

    Prefix(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
