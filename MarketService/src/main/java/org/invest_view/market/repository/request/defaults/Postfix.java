package org.invest_view.market.repository.request.defaults;

public enum Postfix {
    DEFAULT_GET_FOR_LAST_MONTH_POSTFIX (".json?iss.meta=off&.only=history"),

    DEFAULT_GET_DATES_POSTFIX ("/dates.json"),

    DEFAULT_GET_NOW_POSTFIX (".json?iss.meta=off"),

    DEFAULT_GET_HISTORY_POSTFIX (".json?iss.meta=off&start="),

    DEFAULT_GET_HISTORY_CURSOR_POSTFIX (".json?iss.meta=off&iss.only=history.cursor"),

    DEFAULT_JSON_EXTENSION (".json");

    //DEFAULT_GET_USER_ISSUERS_POSTFIX ("get/user/issuers?id=");

    private final String value;

    private Postfix(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
