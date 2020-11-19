package com.seekers.scrumpoker.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class ActiveStoryHistory {
    @NonNull
    String storyId;

    @NonNull
    String userEmail;

    @NonNull
    String action;

    @NonNull
    Date createdDate;

    public ActiveStoryHistory(String storyId, String userEmail, String action) {
        this.storyId = storyId;
        this.userEmail = userEmail;
        this.action = action;
        this.createdDate = new Date();
    }
}
