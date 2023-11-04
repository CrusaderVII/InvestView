package org.invest_view.router.controller.market;

import org.invest_view.router.feignclient.MarketClient;
import org.invest_view.router.model.IssuerMetadataResponse;
import org.invest_view.router.model.IssuerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invest-view/api/market/v1/now")
public class MarketNowController {

    @Autowired
    MarketClient marketClient;

    @GetMapping("/issuer")
    public IssuerResponse getIssuerNow(@RequestParam String secId) {
        return marketClient.getIssuerNow(secId);
    }

    @PostMapping("/issuers/certain")
    public List<IssuerResponse> getCertainIssuersNow(@RequestBody List<IssuerMetadataResponse> list) {
        return marketClient.getCertainIssuersNow(list);
    }

    @GetMapping("/all")
    public List<IssuerResponse> getAllIssuersNow(@RequestParam int page) {
        return marketClient.getAllIssuersNow(page);
    }
}
