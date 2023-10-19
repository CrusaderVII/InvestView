package org.invest_view.market.repository;

import org.invest_view.market.json_parser.JsonParser;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.IssuerMetadata;
import org.invest_view.market.model.TimePeriod;
import org.invest_view.market.repository.request.RequestConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class DataService {

    public List<Issuer> getMainsNow() {
        String[] ids = {"TCSG", "SBER", "YNDX", "MTSS", "GAZP", "LKOH"};
        List<Issuer> issuers = new ArrayList<>();

        for (String id : ids) {
            issuers.add(getIssuerNow(id));
        }

        return issuers;
    }

    public List<Issuer> getIssuerForLastMonth(String secId) {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getRequest(secId));

        List<Issuer> issuerData;

        try {
            issuerData = JsonParser
                    .getIssuerForPeriod(JsonParser
                            .parse(readJson(br)), secId, 30);
        } catch (IOException e) {
            issuerData = null;

            e.printStackTrace();
        }

        return issuerData;
    }

    public List<Issuer> getIssuerForLastWeek(String secId) {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getRequest(secId));

        List<Issuer> issuerData;

        try {
            issuerData = JsonParser
                    .getIssuerForPeriod(JsonParser
                            .parse(readJson(br)), secId, 7);
        } catch (IOException e) {
            issuerData = null;

            e.printStackTrace();
        }

        return issuerData;
    }

    public TimePeriod getIssuerDates(String secId) {

        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getDatesRequest(secId));

        TimePeriod period;

        try {
            period = JsonParser.getTimePeriod(JsonParser.parse(readJson(br)));
        } catch (IOException e) {
            period = new TimePeriod("0", "0");

            e.printStackTrace();
        }

        return period;
    }

    public Issuer getIssuerNow(String secId) {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getNowRequest(secId));

        Issuer issuer;

        try {
            issuer = JsonParser
                    .getIssuerNow(JsonParser
                            .parse(readJson(br)), secId);
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
        return issuer;
    }

    public List<Issuer> getIssuerHistory(String secId) {
        List<Issuer> issuerHistory = new ArrayList<>();

        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getHistoryCursorRequest(secId));

        int total;
        int current = 0;

        try {
            total = JsonParser
                    .getPageNumber(JsonParser
                            .parse(readJson(br)));
        } catch (IOException e) {
            total = 0;

            e.printStackTrace();
        }

        while (current <= total) {
            issuerHistory.addAll(getIssuerHistoryOnPage(current, total, secId));

            current += 100;
        }

        return issuerHistory;
    }

    public List<Issuer> getIssuerHistoryOnPage(int current, int total, String secId) {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getHistroyRequest(current, secId));

        List<Issuer> issuerData;

        try {
            issuerData = JsonParser
                    .getIssuerHistory(JsonParser
                            .parse(readJson(br)), secId, current, total);
        } catch (IOException e) {
            issuerData = null;

            e.printStackTrace();
        }

        return issuerData;
    }

    public List<IssuerMetadata> getAllIssuersMetadata() {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getAllIssuersRequest());

        List<IssuerMetadata> issuersMetadata = new ArrayList<>();

        try {
            issuersMetadata = JsonParser
                    .getAllIssuersSecId(JsonParser
                            .parse(readJson(br)));
        } catch (IOException e) {
            issuersMetadata = null;

            e.printStackTrace();
        }

        return issuersMetadata;
    }

    public List<IssuerMetadata> getIssuersMetadataOnCertainLevel(int level) {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getAllIssuersRequest());

        List<IssuerMetadata> issuersMetadata = new ArrayList<>();

        try {
            issuersMetadata = JsonParser
                    .getIssuerMetadatasOnCertainLevel(JsonParser
                                    .parse(readJson(br)),
                            level);
        } catch (IOException e) {
            issuersMetadata = null;

            e.printStackTrace();
        }

        return issuersMetadata;
    }

//    public static List<Issuer> getCertainIssuersNow(Long id) {
//
//        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getUsersIssuerNow(id));
//
//        List<Issuer> issuers;
//        try {
//            issuers = JsonParser
//                    .getUserIssuersNow(JsonParser
//                            .parse(readJson(br)));
//        } catch (IOException e) {
//            issuers = null;
//
//            e.printStackTrace();
//        }
//
//        return issuers;
//    }

    public List<Issuer> getIssuersOnCertainLevelNow(int level) {
        List<IssuerMetadata> metadata = getIssuersMetadataOnCertainLevel(level);
        List<Issuer> issuersNow = metadata.stream()
                .map(issuerMetadata -> getIssuerNow(issuerMetadata.getSecId()))
                .toList();

        return issuersNow;
    }

    private String readJson(BufferedReader br) {
        String output;
        StringBuilder builder = new StringBuilder();
        try {
            do {
                output = br.readLine();

                if(output!=null) builder.append(output+'\n');

                ;
            } while (output != null );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
