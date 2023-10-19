package org.invest_view.market.json_parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.IssuerFactory;
import org.invest_view.market.model.IssuerMetadata;
import org.invest_view.market.model.TimePeriod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static ObjectMapper mapper = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        return mapper;
    }

    public static JsonNode parse(String json) throws IOException {

        return mapper.readTree(json);
    }

    public static TimePeriod getTimePeriod(JsonNode jsonNode) {
        JsonNode innerNode = jsonNode.get("dates");

        JsonNode values = innerNode.get("data");

        return new TimePeriod(values.get(0).get(0).textValue(), values.get(0).get(1).textValue());
    }

    public static List<Issuer> getIssuerForPeriod (JsonNode jsonNode, String secId, int period) {
        JsonNode innerNode = jsonNode.get("history");

        List<String> fields = new ArrayList<>();
        List<Issuer> issuers = new ArrayList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields
                        .add(fieldName.asText()));

        int nameIndex = fields.indexOf("SHORTNAME");
        int openIndex  = fields.indexOf("OPEN");
        int dateIndex = fields.indexOf("TRADEDATE");

        ArrayNode issuerData = (ArrayNode) innerNode.get("data");

        for (int i = 0; i < period; i++) {
            JsonNode issuerDate = issuerData.get(i);

            issuers.add(IssuerFactory.create(secId,
                    issuerDate.get(nameIndex).asText(),
                    issuerDate.get(openIndex).asDouble(),
                    issuerDate.get(dateIndex).asText()));
        }

        return issuers;
    }

    public static Issuer getIssuerNow(JsonNode jsonNode, String secId) {
        JsonNode innerNodeMarketData = jsonNode.get("marketdata");
        JsonNode innerNodeIssuerData = jsonNode.get("securities");

        List<String> fieldsMarket = new ArrayList<>();
        List<String> fieldsIssuer = new ArrayList<>();


        innerNodeMarketData.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fieldsMarket
                        .add(fieldName.asText()));

        innerNodeIssuerData.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fieldsIssuer
                        .add(fieldName.asText()));

        int nameIndex = fieldsIssuer.indexOf("SHORTNAME");
        int dateIndex  = fieldsMarket.indexOf("SYSTIME");
        int nowIndex = fieldsMarket.indexOf("LAST");
        int openIndex = fieldsMarket.indexOf("OPEN");
        int changeIndex = fieldsMarket.indexOf("CHANGE");

        return IssuerFactory.create(secId,
                innerNodeIssuerData.get("data").get(0).get(nameIndex).asText(),
                innerNodeMarketData.get("data").get(0).get(dateIndex).asText(),
                innerNodeMarketData.get("data").get(0).get(openIndex).asDouble(),
                innerNodeMarketData.get("data").get(0).get(nowIndex).asDouble(),
                innerNodeMarketData.get("data").get(0).get(changeIndex).asDouble());
    }

    public static List<Issuer> getIssuerHistory(JsonNode jsonNode, String secId, int current, int total) {
        JsonNode innerNode = jsonNode.get("history");

        List<String> fields = new ArrayList<>();
        List<Issuer> issuers = new ArrayList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields
                        .add(fieldName.asText()));

        int nameIndex = fields.indexOf("SHORTNAME");
        int lowIndex  = fields.indexOf("LOW");
        int highIndex = fields.indexOf("HIGH");
        int dateIndex = fields.indexOf("TRADEDATE");

        String startingDateString = innerNode.get("data")
                .get(0)
                .get(dateIndex)
                .asText();

        for (int i = 0; i < (current+100 > total ? total-current : 100); i+=1) {
            JsonNode issuerDate = innerNode.get("data").get(i);

            String currentIteratorDate = issuerDate.get(dateIndex).asText();

            if(currentIteratorDate.substring(currentIteratorDate.length()-3)
                    .equals(startingDateString.substring(currentIteratorDate.length()-3))) {

                issuers.add(IssuerFactory.create(secId,
                        issuerDate.get(nameIndex).asText(),
                        issuerDate.get(lowIndex).asDouble(),
                        issuerDate.get(highIndex).asDouble(),
                        issuerDate.get(dateIndex).asText()));
            }
        }

        return issuers;
    }

    public static int getPageNumber(JsonNode jsonNode) {
        JsonNode innerNode = jsonNode.get("history.cursor");

        List<String> fields = new ArrayList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields
                        .add(fieldName.asText()));

        int index = fields.indexOf("TOTAL");

        return innerNode.get("data")
                .get(0)
                .get(index)
                .asInt();
    }

    public static List<IssuerMetadata> getAllIssuersSecId(JsonNode jsonNode) {
        JsonNode innerNode = jsonNode.get("securities");

        List<IssuerMetadata> allIssuersMetadata = new ArrayList<>();
        List<String> fields = new ArrayList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields.add(fieldName.asText()));

        int secIdIndex = fields.indexOf("SECID");
        int nameIndex = fields.indexOf("SHORTNAME");

        innerNode.get("data")
                .iterator()
                .forEachRemaining(node -> allIssuersMetadata.add(new IssuerMetadata(
                        node.get(secIdIndex).asText(),
                        node.get(nameIndex).asText())));

        return allIssuersMetadata;
    }

    public static List<IssuerMetadata> getIssuerMetadatasOnCertainLevel(JsonNode jsonNode, int level) {
        JsonNode innerNode = jsonNode.get("securities");

        List<IssuerMetadata> allIssuersMetadata = new ArrayList<>();
        List<String> fields = new ArrayList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields.add(fieldName.asText()));

        int secIdIndex = fields.indexOf("SECID");
        int nameIndex = fields.indexOf("SHORTNAME");
        int levelIndex = fields.indexOf("LISTLEVEL");
        int typeIndex = fields.indexOf("SECTYPE");

        innerNode.get("data")
                .iterator()
                .forEachRemaining(node -> {
                            if (node.get(levelIndex).asInt()==level && node.get(typeIndex).asText().equals("1")) {
                                allIssuersMetadata.add(new IssuerMetadata(
                                        node.get(secIdIndex).asText(),
                                        node.get(nameIndex).asText()));
                            }
                        }
                );

        return allIssuersMetadata;
    }
}
