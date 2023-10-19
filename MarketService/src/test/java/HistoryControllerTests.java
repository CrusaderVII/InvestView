import org.assertj.core.api.Assert;
import org.invest_view.market.MarketServiceApplication;
import org.invest_view.market.controller.HistoryController;
import org.invest_view.market.model.Issuer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = MarketServiceApplication.class)
public class HistoryControllerTests {

    @Autowired
    HistoryController controller;

    @Test
    @DisplayName("GET api/market-data/v1/history/last/month returns valid data about issuer for last month")
    public void getIssuerForLastMonth_ReturnsValidDataOfIssuerForLastMonth() {
        var listOfTickers = List.of("TCSG", "YNDX", "GAZP", "SBER");
        int randomIndex = (int) Math.random()*10%listOfTickers.size();

        List<Issuer> issuerForLastMonth = controller.getIssuerForLastMonth(listOfTickers.get(randomIndex));

        assertThat(issuerForLastMonth!=null);
        issuerForLastMonth.stream().forEach(issuer -> {
            assertThat(issuer!=null);
            assertThat(issuer.getPriceOpen()!=0);
        });
    }
}
