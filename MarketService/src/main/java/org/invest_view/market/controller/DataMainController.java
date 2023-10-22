package  org.invest_view.market.controller;

import org.invest_view.market.model.Issuer;
import org.invest_view.market.model.IssuerMetadata;
import org.invest_view.market.model.PageMetadata;
import org.invest_view.market.model.TimePeriod;
import org.invest_view.market.repository.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/market-data/v1/main")
public class DataMainController {

    @Autowired
    DataService service;

    @GetMapping("/mains-now")
    public  List<Issuer> getMainsNow() {
        List<Issuer> issuers = service.getMainsNow();

        return issuers;
    }

    @GetMapping("/stock")
    public List<Issuer> getStockNow(@RequestParam int page) {
        List<Issuer> issuers = service.getIssuersOnCertainLevelNow(1);

        int start = 10 * (page-1);
        int end = (start/10==issuers.size()/10)? start+issuers.size()%10 : start+10;

        return issuers.subList(start, end);
    }

    @GetMapping("/stock/pages")
    public PageMetadata getPages() {
        List<IssuerMetadata> issuers =  service.getIssuersMetadataOnCertainLevel(1);

        return new PageMetadata(issuers.size()/10+1);
    }

    @GetMapping("/last-month")
    public List<Issuer> getIssuer(@RequestParam String id) {
        return service.getIssuerForLastMonth(id);
    }

    @GetMapping("/dates")
    public TimePeriod getIssuerDates(@RequestParam String id) {
        return service.getIssuerDates(id);
    }

    @GetMapping("/history")
    public List<Issuer> getIssuerHistory(@RequestParam String id) {
        List<Issuer> list = service.getIssuerHistory(id);

        return list;
    }

    @GetMapping("/issuers")
    public List<IssuerMetadata> getAllIssuers() {
        List<IssuerMetadata> list = service.getAllIssuersMetadata();

        return list;
    }

    @GetMapping("/issuers/level")
    public List<IssuerMetadata> getAllIssuersOnCertainLevel(@RequestParam int level) {
        List<IssuerMetadata> issuers = service.getIssuersMetadataOnCertainLevel(level);

        return issuers;
    }

    @PostMapping("/save/all")
    public void saveAllIssuersToDatabase() {
        service.saveAllIssuersToDatabase();
    }
}
