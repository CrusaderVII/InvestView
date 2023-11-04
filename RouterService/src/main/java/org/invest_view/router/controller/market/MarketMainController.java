package org.invest_view.router.controller.market;

import org.invest_view.router.feignclient.MarketClient;
import org.invest_view.router.model.IssuerMetadataResponse;
import org.invest_view.router.model.IssuerResponse;
import org.invest_view.router.model.PageMetadataResponse;
import org.invest_view.router.model.TimePeriodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("invest-view/api/market/v1/main")
public class MarketMainController {

    @Autowired
    MarketClient marketClient;

    @GetMapping("/mains-now")
    public List<IssuerResponse> getMainsNow() {
        return marketClient.getMainsNow();
    }

    @GetMapping("/stock")
    public List<IssuerResponse> getStockNow(@RequestParam int page) {
        return marketClient.getStockNow(page);
    }

    @GetMapping("main/stock/pages")
    public PageMetadataResponse getPages() {
        return marketClient.getPages();
    }

    @GetMapping("/dates")
    public TimePeriodResponse getIssuerDates(@RequestParam String secId) {
        return marketClient.getIssuerDates(secId);
    }

    @GetMapping("/history")
    public List<IssuerResponse> getIssuerHistory(@RequestParam String secId) {
        return marketClient.getIssuerHistory(secId);
    }

    @GetMapping("/issuers")
    public List<IssuerMetadataResponse> getAllIssuers() {
        return marketClient.getAllIssuers();
    }

    @GetMapping("/issuers/level")
    public List<IssuerMetadataResponse> getAllIssuersOnCertainLevel(@RequestParam int level) {
        return marketClient.getAllIssuersOnCertainLevel(level);
    }
}
