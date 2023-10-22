package org.invest_view.user.repository;

import org.invest_view.user.model.IssuerData;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RequestConstructor {

    public static String getAllIssuersOn1Level() {
        return "http://localhost:8081/api/market-data/v1/main/issuers/level";
    }

    public static List<IssuerData> getPlainJson() {
        String request = getAllIssuersOn1Level();
        RestTemplate template = new RestTemplate();
        List<IssuerData> issuers = new ArrayList<>();
        issuers = template.getForEntity(request, issuers.getClass()).getBody();

        return issuers;
    }
}
