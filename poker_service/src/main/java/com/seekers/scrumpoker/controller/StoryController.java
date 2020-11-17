package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoryController {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @PostMapping("/createStory")
    public String createStory(@RequestParam(value = "storyId") String storyId,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "description") String description) {
        Story story = storyRepository.save(new Story(storyId, title, description));
        return "Story successfully created with title: " + story.getTitle();
    }

    @GetMapping("/getAllStory")
    public List<Story> getAllStory() {
        return storyRepository.findAll();
    }
}
