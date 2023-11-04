package org.invest_view.router.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class IssuerResponse {
    private String secId;
    private String fullName;
    private double priceLow;
    private double priceHigh;
    private double priceNow;
    private double priceOpen;
    private double percent;
    private String date;
    private double change;
}
