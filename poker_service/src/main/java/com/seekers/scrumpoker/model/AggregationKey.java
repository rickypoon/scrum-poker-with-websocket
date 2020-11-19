package com.seekers.scrumpoker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AggregationKey {
    Story story;
    User user;
}
