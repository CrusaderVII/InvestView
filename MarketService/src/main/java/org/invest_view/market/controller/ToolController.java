package org.invest_view.market.controller;

import org.invest_view.market.model.market_tools.MarketTool;
import org.invest_view.market.repository.service.DataService;
import org.invest_view.market.repository.service.MarketToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/market-data/v1/tool")
public class ToolController {

    @Autowired
    MarketToolsService marketToolsService;
    @Autowired
    DataService dataService;

    @GetMapping("/last-month/{tool}")
    public MarketTool getToolValuesForLastMonth(@PathVariable String tool, @RequestParam String secId) {

        return null;
    }

    @GetMapping("/last-week/{tool}")
    public MarketTool getToolValuesForLastWeek(@PathVariable String tool, @RequestParam String secId) {

        return null;
    }
}
