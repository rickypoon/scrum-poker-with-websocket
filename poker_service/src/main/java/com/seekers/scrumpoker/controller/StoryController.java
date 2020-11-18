package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StoryController {
    private final StoryRepository storyRepository;

    @Autowired
    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @PostMapping("/createStory")
    public String createStory(@RequestBody Map<String, String> params) {
        Story story = storyRepository.save(new Story(params.get("storyId"), params.get("title"), params.get("description")));
        return "Story successfully created with title: " + story.getTitle();
    }

    @GetMapping("/getAllStory")
    public List<Story> getAllStory() {
        return storyRepository.findAll();
    }
}
