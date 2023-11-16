package org.invest_view.market.model.market_tools;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.invest_view.market.model.Issuer;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MarketTool {

    private List<Issuer> issuerData;
    private ToolType tool;
    private List<Double> values;

    public MarketTool(List<Issuer> issuerData, ToolType tool) {
        this.issuerData = issuerData;
        this.tool = tool;
    }

}
