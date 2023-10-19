import org.invest_view.market.MarketServiceApplication;
import org.invest_view.market.controller.DataMainController;
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
public class DataMainControllerTests {
    @Autowired
    private DataMainController controller;

    @Test
    @DisplayName("GET api/market-data/v1/main/mains-now returns all main issuers with not-null now price")
     public void getMainsNow_ReturnsAllSixNotNullMainIssuers() {
        List<Issuer> issuers = controller.getMainsNow();

        assertThat(issuers.size()==6);
        issuers.stream().forEach(issuer -> {
            assertThat(issuer!=null);
            assertThat(issuer.getPriceNow()!=0);
        });
    }

    @Test
    @DisplayName("GET api/market-data/v1/main/mains-now returns all necessary issuers with not-null now price")
    public void getStockNow_ReturnsTenIssuersOnFirstPage() {
        List<Issuer> issuers = controller.getStockNow(1);

        assertThat(issuers.size()==10);
        issuers.stream().forEach(issuer -> {
            assertThat(issuer!=null);
            assertThat(issuer.getPriceNow()!=0);
        });
    }
}
