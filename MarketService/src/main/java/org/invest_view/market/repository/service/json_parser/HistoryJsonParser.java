package org.invest_view.market.repository.service.json_parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.IssuerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class HistoryJsonParser extends JsonParser{

    public List<Issuer> getIssuerHistory(String secId, int current, int total) {
        JsonNode innerNode = getJsonNode().get("history");

        List<String> fields = new ArrayList<>();
        List<Issuer> issuers = new LinkedList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields
                        .add(fieldName.asText()));

        int boardIndex = fields.indexOf("BOARDID");
        int nameIndex = fields.indexOf("SHORTNAME");
        int openIndex  = fields.indexOf("OPEN");
        int closeIndex = fields.indexOf("CLOSE");
        int dateIndex = fields.indexOf("TRADEDATE");

        ArrayNode issuerData = (ArrayNode) innerNode.get("data");

        for (int i = 0; i < (current+100 > total ? total-current : 100); i+=1) {
            JsonNode issuerDate = issuerData.get(i);

            if (!issuerDate.get(boardIndex).asText().equals("TQBR")) continue;

            issuers.add(IssuerFactory.create(secId,
                    issuerDate.get(nameIndex).asText(),
                    issuerDate.get(openIndex).asDouble(),
                    issuerDate.get(dateIndex).asText(),
                    issuerDate.get(closeIndex).asDouble()));

        }

        return issuers;
    }

    public int getPageNumber() {
        JsonNode innerNode = getJsonNode().get("history.cursor");

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
}
