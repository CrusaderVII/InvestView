package  org.invest_view.market.controller;

import org.invest_view.market.model.Issuer;
import org.invest_view.market.repository.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/market-data/v1/history")
public class HistoryController {

    @Autowired
    DataService service;

    @GetMapping("/last/month")
    public List<Issuer> getIssuerForLastMonth(@RequestParam String secId) {
        List<Issuer> issuerForLastMonth = service.getIssuerForLastMonth(secId);

        return issuerForLastMonth;
    }

    @GetMapping("/last/week")
    public List<Issuer> getIssuerForLastWeek(@RequestParam String secId) {
        List<Issuer> issuerForLastWeekIssuers = service.getIssuerForLastWeek(secId);

        return issuerForLastWeekIssuers;
    }
}
