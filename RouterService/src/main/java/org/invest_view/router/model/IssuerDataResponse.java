package org.invest_view.router.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class IssuerDataResponse {
    private Long id;
    private String secId;
    private String fullName;
    private String imageId;
}
