package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.ActiveStoryHistory;
import com.seekers.scrumpoker.repository.ActiveStoryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ActiveStoryController {
    private final ActiveStoryHistoryRepository activeStoryHistoryRepository;

    @Autowired
    public ActiveStoryController(ActiveStoryHistoryRepository activeStoryHistoryRepository) {
        this.activeStoryHistoryRepository = activeStoryHistoryRepository;
    }

    @PostMapping("/setActiveStory")
    public String createStory(@RequestBody Map<String, String> params) {
        ActiveStoryHistory activeStoryHistory = activeStoryHistoryRepository.save(new ActiveStoryHistory(params.get("storyId"), params.get("userEmail")));
        return "Attempt to set active (storyId): " + activeStoryHistory.getStoryId();
    }

    @GetMapping("/getActiveStory")
    public ActiveStoryHistory getActiveStory() {
        return activeStoryHistoryRepository.findFirstByOrderByStoryIdAsc();
    }
}
