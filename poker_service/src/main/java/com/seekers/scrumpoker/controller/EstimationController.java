package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.Estimation;
import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.model.User;
import com.seekers.scrumpoker.repository.EstimationRepository;
import com.seekers.scrumpoker.repository.StoryRepository;
import com.seekers.scrumpoker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class EstimationController {
    private final EstimationRepository estimationRepository;
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    @Autowired
    public EstimationController(EstimationRepository estimationRepository, StoryRepository storyRepository, UserRepository userRepository) {
        this.estimationRepository = estimationRepository;
        this.storyRepository = storyRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/estimate")
        public void estimate(@RequestBody Map<String, String> params) {
        Story story = storyRepository.findById(params.get("storyId")).orElse(null);
        User user = userRepository.findById(params.get("userEmail")).orElse(null);
        if (story == null || user == null) {
            return;
        }
        Estimation estimation = new Estimation(story, user, params.get("point"));
        estimationRepository.save(estimation);
    }
}
