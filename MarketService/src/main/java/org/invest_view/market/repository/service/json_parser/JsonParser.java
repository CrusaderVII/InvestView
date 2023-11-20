package org.invest_view.market.repository.service.json_parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class JsonParser {

    private ObjectMapper mapper;
    private JsonNode jsonNode;
    public JsonParser () {
        mapper = new ObjectMapper();
    }

    public void setJsonNode(String json) {
        try {
            this.jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public JsonNode getJsonNode() {
        return jsonNode;
    }
}
