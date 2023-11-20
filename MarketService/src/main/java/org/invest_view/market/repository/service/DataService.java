package org.invest_view.market.repository.service;

import org.invest_view.market.json_parser.JsonParser;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.IssuerMetadata;
import org.invest_view.market.model.time.TimeFrame;
import org.invest_view.market.model.time.TimePeriod;
import org.invest_view.market.repository.request.RequestConstructor;
import org.invest_view.market.repository.service.json_parser.HistoryJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Service
public class DataService{

    private HistoryJsonParser historyJsonParser;

    @Autowired
    public DataService(HistoryJsonParser historyJsonParser) {
        this.historyJsonParser = historyJsonParser;
    }

    public List<Issuer> getMainsNow() {
        String[] ids = {"TCSG", "SBER", "YNDX", "MTSS", "GAZP", "LKOH"};
        List<Issuer> issuers = new ArrayList<>();

        for (String id : ids) {
            issuers.add(getIssuerNow(id));
        }

        return issuers;
    }

    private List<Issuer> getIssuerMonthly(LinkedList<Issuer> issuerData) {
        List<Issuer> issuerDataFiltered = new LinkedList<>();
        Iterator iterator = issuerData.iterator();
        int currentMonth = Integer.parseInt(issuerData.getFirst()
                .getDate()
                .substring(5,7));
        int nextMonth = currentMonth==12? 1 : currentMonth+1;

        while (iterator.hasNext()) {
            Issuer issuer = (Issuer) iterator.next();
            currentMonth = Integer.parseInt(issuer.getDate()
                    .substring(5,7));

            if (currentMonth == nextMonth) {
                issuerDataFiltered.add(issuer);
                nextMonth = nextMonth==12? 1 : nextMonth+1;
            }
        }

        return issuerDataFiltered;
    }

    private List<Issuer> getIssuerDaily(LinkedList<Issuer> issuerData) {
        List<Issuer> issuerDataFiltered = new LinkedList<>();
        Collections.reverse(issuerData);
        Iterator iterator = issuerData.iterator();
        int iterationDay = 0;

        while (iterator.hasNext() && iterationDay<365) {
            Issuer issuer = (Issuer) iterator.next();
            issuerDataFiltered.add(issuer);
            iterationDay++;}

        return issuerDataFiltered;
    }

    private List<Issuer> getIssuerWeekly(LinkedList<Issuer> issuerData) {
        List<Issuer> issuerDataFiltered = new LinkedList<>();
        Collections.reverse(issuerData);
        Iterator iterator = issuerData.iterator();

        int currentYear = Integer.parseInt(issuerData.getFirst()
                .getDate()
                .substring(0,4));
        int limitYear = currentYear-2;
        int currentMonth = Integer.parseInt(issuerData.getFirst()
                .getDate()
                .substring(5,7));
        int previousMonth = currentMonth==1 ? 12 : currentMonth-1;

        while (iterator.hasNext() && currentYear>=limitYear) {
            Issuer issuer = (Issuer) iterator.next();
            currentMonth = Integer.parseInt(issuer.getDate()
                    .substring(5,7));
            currentYear = Integer.parseInt(issuer.getDate()
                    .substring(0,4));
        }

        return issuerDataFiltered;
    }

    public List<Issuer> getIssuerHistory(String secId, TimeFrame timeFrame) {
        List<Issuer> issuerHistory = new LinkedList<>();
        List<Issuer> issuerHistoryOnPages = new LinkedList<>();
        String historyCursorJson = RequestConstructor.getJson(RequestConstructor.getHistoryCursorRequest(secId));
        historyJsonParser.setJsonNode(historyCursorJson);

        int total = historyJsonParser.getPageNumber();
        int current = 0;



        switch (timeFrame){
            case MONTH -> {
                while (current <= total) {
                    String dataJson = RequestConstructor.getJson(RequestConstructor.getHistroyRequest(current, secId));
                    historyJsonParser.setJsonNode(dataJson);
                    issuerHistoryOnPages.addAll(historyJsonParser.getIssuerHistory(secId, current, total));
                    current += 100;
                }
                issuerHistory = getIssuerMonthly((LinkedList<Issuer>) issuerHistoryOnPages);
            }
            case WEEK -> {
                while (current <= total) {
                    issuerHistory.addAll(getIssuerHistoryOnPage(current, total, secId, TimeFrame.WEEK));
                    current += 100;
                }
            }

            case DAY -> {
                while (current<=total) {
                    String dataJson = RequestConstructor.getJson(RequestConstructor.getHistroyRequest(current, secId));
                    historyJsonParser.setJsonNode(dataJson);
                    issuerHistoryOnPages.addAll(historyJsonParser.getIssuerHistory(secId, current, total));
                    current+=100;
                }

                issuerHistory = getIssuerDaily((LinkedList<Issuer>) issuerHistoryOnPages);
            }
        }

        return issuerHistory;
    }

    @Deprecated
    public List<Issuer> getIssuerHistoryOnPage(int current, int total, String secId, TimeFrame timeFrame) {
        BufferedReader br = RequestConstructor.getPlainJson(RequestConstructor.getHistroyRequest(current, secId));

        List<Issuer> issuerData;

        try {
            issuerData = JsonParser
                    .getIssuerHistory(JsonParser.parse(readJson(br)), secId, current, total, timeFrame);
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
