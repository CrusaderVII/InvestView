package org.invest_view.router.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class TimePeriodResponse {
    private String beginning;
    private String ending;
}
