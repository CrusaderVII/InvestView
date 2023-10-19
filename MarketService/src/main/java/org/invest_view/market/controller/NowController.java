package  org.invest_view.market.controller;

import org.apache.catalina.User;
import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.IssuerMetadata;
import org.invest_view.market.repository.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/market-data/v1/now")
public class NowController {

    @Autowired
    DataService service;

    @GetMapping("/issuer")
    public Issuer getIssuerNow(@RequestParam String secId) {

        return service.getIssuerNow(secId);
    }

    @PostMapping("issuers/certain")
    public List<Issuer> getCertainIssuersNow(@RequestBody List<IssuerMetadata> list) {
        List<Issuer> issuersNow = list.stream()
                .map(issuerMetadata -> service.getIssuerNow(issuerMetadata.getSecId()))
                .toList();

        return issuersNow;
    }

//    @GetMapping("/test")
//    public List<Issuer> getUserIssuers(@RequestParam Long id) {
//        List<Issuer> issuers = DataService.getCertainIssuersNow(id);
//
//        return issuers;
//    }

    @GetMapping("/all")
    public List<Issuer> getAllIssuersNow(@RequestParam int page) {
        List<IssuerMetadata> list = service.getAllIssuersMetadata();

        List<Issuer> listIssuers = list.stream()
                .map(issuer -> service.getIssuerNow(issuer.getSecId()))
                .toList();

        int begin = (page-1) * 10;
        int end = (page-1) * 10 + 10;

        return listIssuers.subList(begin >= listIssuers.size() ? listIssuers.size()-11 : begin,
                end >= listIssuers.size() ? listIssuers.size()-1 : end);
    }
}
