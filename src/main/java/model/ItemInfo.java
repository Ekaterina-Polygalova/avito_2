package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemInfo {
    private String id;
    private Integer sellerId;
    private String name;
    private Integer price;
    private StatisticResponse statistics;
    private String createdAt;
}