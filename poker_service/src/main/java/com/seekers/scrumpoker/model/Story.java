package com.seekers.scrumpoker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Story {
    @Id
    String storyId;

    String title;

    String description;
}
