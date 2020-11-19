package com.seekers.scrumpoker.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@RequiredArgsConstructor
public class Estimation {
    @Id
    String Id;

    @NonNull
    Story story;

    @NonNull
    User user;

    @NonNull
    String point;
}
