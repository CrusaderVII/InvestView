package org.invest_view.market.controller;

import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.market_tools.MarketTool;
import org.invest_view.market.model.market_tools.ToolType;
import org.invest_view.market.repository.service.DataService;
import org.invest_view.market.repository.service.MarketToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/market-data/v1/tool")
public class ToolController {

    @Autowired
    MarketToolsService marketToolsService;
    @Autowired
    DataService dataService;

    @GetMapping("/last-month/{toolName}")
    public MarketTool getToolValuesForLastMonth(@PathVariable String toolName, @RequestParam String secId) {
        List<Issuer> issuerData = dataService.getIssuerForLastMonth(secId);
        ToolType tool = ToolType.valueOf(toolName.toUpperCase());
        MarketTool marketTool = new MarketTool(issuerData, tool);

        marketToolsService.setMarketTool(marketTool);

        return marketToolsService.calculate();
    }

    @GetMapping("/last-week/{toolName}")
    public MarketTool getToolValuesForLastWeek(@PathVariable String toolName, @RequestParam String secId) {
        List<Issuer> issuerData = dataService.getIssuerForLastWeek(secId);
        ToolType tool = ToolType.valueOf(toolName.toUpperCase());
        MarketTool marketTool = new MarketTool(issuerData, tool);

        marketToolsService.setMarketTool(marketTool);

        return marketToolsService.calculate();
    }
}
