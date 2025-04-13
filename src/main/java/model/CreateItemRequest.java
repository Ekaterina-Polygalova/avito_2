package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateItemRequest {
    private Integer sellerID;
    private String name;
    private Integer price;
    private StatisticResponse statistics;
}
