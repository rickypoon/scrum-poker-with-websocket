package com.seekers.scrumpoker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class AggregationResult {
    @Id
    AggregationKey key;

    String point;
}
