package org.invest_view.router.controller.market;

import org.invest_view.router.feignclient.MarketClient;
import org.invest_view.router.model.IssuerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("invest-view/api/market/v1/history")
public class MarketHistoryController {

    @Autowired
    MarketClient marketClient;

    @GetMapping("/last/month")
    public List<IssuerResponse> getIssuerForLastMonth(@RequestParam String secId) {
        return marketClient.getIssuerForLastMonth(secId);
    }

    @GetMapping("/last/week")
    public List<IssuerResponse> getIssuerForLastWeek(@RequestParam String secId) {
        return marketClient.getIssuerForLastWeek(secId);
    }
}