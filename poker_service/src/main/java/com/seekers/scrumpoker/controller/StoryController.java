package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoryController {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @GetMapping("/createStory")
    public String getSummary(@RequestParam(value = "title") String title, @RequestParam(value = "description") String description) {
        Story story = storyRepository.save(new Story(title, description));
        return "Story successfully created with title: " + story.getTitle();
    }
}
