package org.invest_view.user.repository.service.request;

import org.invest_view.user.model.IssuerData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class RequestConstructor {

    public static String getAllIssuersOn1Level() {
        return "https://iss.moex.com/iss/engines/stock/markets/shares/boards/TQBR/securities.json?sort_column=LISTLEVEL";
    }

    public static List<IssuerData> getAllIssuers() {
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> responseEntity = template.getForEntity(getAllIssuersOn1Level(), String.class);
        BufferedReader br = new BufferedReader(new StringReader(responseEntity.getBody()));

        List<IssuerData> issuers = null;
        try {
            issuers = JsonParser.getAllIssuersSecId(JsonParser.parse(readJson(br)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return issuers;
    }

    private static String readJson(BufferedReader br) {
        String output;
        StringBuilder builder = new StringBuilder();

        try {
            do {
                output = br.readLine();
                if(output!=null) builder.append(output+'\n');
            } while (output != null );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
