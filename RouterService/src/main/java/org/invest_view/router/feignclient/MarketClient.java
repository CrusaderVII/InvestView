package org.invest_view.router.feignclient;

import org.invest_view.router.model.IssuerMetadataResponse;
import org.invest_view.router.model.IssuerResponse;
import org.invest_view.router.model.PageMetadataResponse;
import org.invest_view.router.model.TimePeriodResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "MARKET-SERVICE", path = "api/market-data/v1/")
public interface MarketClient {

    //MAIN-controllers

    @GetMapping("main/mains-now")
    public List<IssuerResponse> getMainsNow();

    @GetMapping("main/stock")
    public List<IssuerResponse> getStockNow(@RequestParam int page);

    @GetMapping("main/stock/pages")
    public PageMetadataResponse getPages();

    @GetMapping("main/dates")
    public TimePeriodResponse getIssuerDates(@RequestParam String id);

    @GetMapping("main/history")
    public List<IssuerResponse> getIssuerHistory(@RequestParam String id);

    @GetMapping("main/issuers")
    public List<IssuerMetadataResponse> getAllIssuers();

    @GetMapping("main/issuers/level")
    public List<IssuerMetadataResponse> getAllIssuersOnCertainLevel(@RequestParam int level);

    //NOW-controllers

    @GetMapping("/issuer")
    public IssuerResponse getIssuerNow(@RequestParam String secId);

    @PostMapping("now/issuers/certain")
    public List<IssuerResponse> getCertainIssuersNow(@RequestBody List<IssuerMetadataResponse> list);

    @GetMapping("now/all")
    public List<IssuerResponse> getAllIssuersNow(@RequestParam int page);

    //HISTORY-controllers

    @GetMapping("/last/month")
    public List<IssuerResponse> getIssuerForLastMonth(@RequestParam String secId);

    @GetMapping("/last/week")
    public List<IssuerResponse> getIssuerForLastWeek(@RequestParam String secId);

}
