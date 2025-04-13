package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticResponse {
    private Integer contacts;
    private Integer likes;
    private Integer viewCount;
}