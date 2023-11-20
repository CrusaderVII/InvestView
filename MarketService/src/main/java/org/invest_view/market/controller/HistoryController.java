package  org.invest_view.market.controller;

import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.time.TimeFrame;
import org.invest_view.market.repository.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/market-data/v1/history")
public class HistoryController {

    @Autowired
    DataService service;

//    @GetMapping("/monthly")
//    public List<Issuer> getIssuerForLastMonth(@RequestParam String secId) {
//        List<Issuer> issuerForLastMonth = service.get;
//
//        return issuerForLastMonth;
//    }

//    @GetMapping("/weekly")
//    public List<Issuer> getIssuerForLastWeek(@RequestParam String secId) {
//        List<Issuer> issuerForLastWeekIssuers = service.getIssuerMonthly(secId);
//
//        return issuerForLastWeekIssuers;
//    }

    @GetMapping("/{timeFrame}")
    public List<Issuer> getIssuerHistory(@PathVariable String timeFrame, @RequestParam String secId) {
        List<Issuer> list = service.getIssuerHistory(secId, TimeFrame.valueOf(timeFrame.toUpperCase()));

        return list;
    }
}
