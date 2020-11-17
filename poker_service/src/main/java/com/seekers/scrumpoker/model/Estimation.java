package com.seekers.scrumpoker.model;

import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
public class Estimation {
    Story story;

    User user;

    String point;
}
