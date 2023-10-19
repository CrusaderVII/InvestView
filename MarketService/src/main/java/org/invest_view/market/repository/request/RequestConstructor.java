package org.invest_view.market.repository.request;

import org.invest_view.market.repository.request.defaults.Postfix;
import org.invest_view.market.repository.request.defaults.Prefix;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.StringReader;

public class RequestConstructor {

    public static String getRequest(String secId) {
        return Prefix.DEFAULT_GET_HISTORY_PREFIX.value() + secId + Postfix.DEFAULT_GET_FOR_LAST_MONTH_POSTFIX.value();
    }

    public static String getDatesRequest(String secId) {
        return Prefix.DEFAULT_GET_HISTORY_PREFIX.value() + secId + Postfix.DEFAULT_GET_DATES_POSTFIX.value();
    }

    public static String getHistroyRequest(int page, String secId) {
        return Prefix.DEFAULT_GET_HISTORY_PREFIX.value() + secId + Postfix.DEFAULT_GET_HISTORY_POSTFIX.value() + page;
    }

    public static String getHistoryCursorRequest(String secId) {
        return Prefix.DEFAULT_GET_HISTORY_PREFIX.value() + secId + Postfix.DEFAULT_GET_HISTORY_CURSOR_POSTFIX.value();
    }

    public static String getAllIssuersRequest() {
        return Prefix.DEFAULT_GET_ALL_ISSUERS_PREFIX.value() + Postfix.DEFAULT_JSON_EXTENSION.value();
    }

    public static String getNowRequest(String secId) {
        return Prefix.DEFAULT_GET_NOW_PREFIX.value() + secId + Postfix.DEFAULT_GET_NOW_POSTFIX.value();
    }

    public static BufferedReader getPlainJson(String request) {
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> responseEntity = template.getForEntity(request, String.class);

        return new BufferedReader(new StringReader(responseEntity.getBody()));
    }

}
