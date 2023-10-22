package org.invest_view.user.repository.service.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.invest_view.user.model.IssuerData;

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

    public static List<IssuerData> getAllIssuersSecId(JsonNode jsonNode) {
        JsonNode innerNode = jsonNode.get("securities");

        List<IssuerData> allIssuersMetadata = new ArrayList<>();
        List<String> fields = new ArrayList<>();

        innerNode.get("columns")
                .iterator()
                .forEachRemaining(fieldName -> fields.add(fieldName.asText()));

        int secIdIndex = fields.indexOf("SECID");
        int nameIndex = fields.indexOf("SHORTNAME");
        int levelIndex = fields.indexOf("LISTLEVEL");

        innerNode.get("data")
                .iterator()
                .forEachRemaining(node -> {
                    if (node.get(levelIndex).asInt()!=1) return;
                    allIssuersMetadata.add(new IssuerData(
                            node.get(secIdIndex).asText(),
                            node.get(nameIndex).asText()));
                });

        return allIssuersMetadata;
    }
}
