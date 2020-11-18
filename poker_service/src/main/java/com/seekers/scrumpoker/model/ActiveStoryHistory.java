package com.seekers.scrumpoker.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RequiredArgsConstructor
@Data
public class ActiveStoryHistory {
    @NonNull
    String storyId;

    @NonNull
    String userEmail;
}
