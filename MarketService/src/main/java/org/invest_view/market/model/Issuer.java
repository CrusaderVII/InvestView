package org.invest_view.market.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Issuer {

    private String secId;
    private String fullName;
    private double priceLow;
    private double priceHigh;
    private double priceNow;
    private double priceOpen;
    private double priceClose;
    private double percent;
    private String date;
    private double change;

}
