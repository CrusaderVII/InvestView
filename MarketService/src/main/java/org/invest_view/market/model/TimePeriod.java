package org.invest_view.market.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TimePeriod {
    private String beginning;
    private String ending;
}
