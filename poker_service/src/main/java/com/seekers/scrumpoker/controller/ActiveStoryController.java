package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.ActiveStoryHistory;
import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.repository.ActiveStoryHistoryRepository;
import com.seekers.scrumpoker.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveStoryController {
    private final ActiveStoryHistoryRepository activeStoryHistoryRepository;
    private final StoryRepository storyRepository;

    @Autowired
    public ActiveStoryController(ActiveStoryHistoryRepository activeStoryHistoryRepository, StoryRepository storyRepository) {
        this.activeStoryHistoryRepository = activeStoryHistoryRepository;
        this.storyRepository = storyRepository;
    }

    public Story getActiveStory() {
        ActiveStoryHistory lastResetRecord = activeStoryHistoryRepository.findFirstByActionOrderByCreatedDateDesc("RESET");
        ActiveStoryHistory activeStory;
        if (lastResetRecord == null) {
            activeStory = activeStoryHistoryRepository.findFirstByOrderByCreatedDateAsc();
        } else {
            activeStory = activeStoryHistoryRepository.findFirstByCreatedDateAfterOrderByCreatedDateAsc(lastResetRecord.getCreatedDate());
        }
        return storyRepository.findById(activeStory.getStoryId()).orElse(null);
    }

    @MessageMapping("/user-all")
    @SendTo("/topic/user")
    public Story attemptToSetActiveStory(ActiveStoryHistory attempt) {
        ActiveStoryHistory activeStoryHistory = activeStoryHistoryRepository.save(new ActiveStoryHistory(attempt.getStoryId(), attempt.getUserEmail(), "PICK"));
        return getActiveStory();
    }
}
