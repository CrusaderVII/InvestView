import org.invest_view.market.MarketServiceApplication;
import org.invest_view.market.controller.NowController;
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
public class NowControllerTests {

    @Autowired
    NowController controller;

    @Test
    @DisplayName("GET api/market-data/v1/now/issuer returns valid now-time data about random issuer")
    public void getIssuerNow_ReturnsValidData() {
        var listOfTickers = List.of("TCSG", "YNDX", "GAZP", "SBER");
        int randomIndex = (int) Math.random()*10%listOfTickers.size();

        Issuer issuer = controller.getIssuerNow(listOfTickers.get(randomIndex));

        assertThat(issuer!=null);
        assertThat(issuer.getPriceNow()!=0);
    }
}
