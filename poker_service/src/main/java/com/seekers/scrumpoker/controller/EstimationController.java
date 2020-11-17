package com.seekers.scrumpoker.controller;

import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.model.User;
import com.seekers.scrumpoker.model.Estimation;
import com.seekers.scrumpoker.repository.EstimationRepository;
import com.seekers.scrumpoker.repository.StoryRepository;
import com.seekers.scrumpoker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

    @GetMapping("/estimate")
    public void getSummary(@RequestParam(value = "storyId") long storyId,
                             @RequestParam(value = "user") String userEmail,
                             @RequestParam(value = "point") String point) {
        Story story = storyRepository.findById(storyId).orElse(null);
        User user = userRepository.findById(userEmail).orElse(null);
        if (story == null || user == null) {
            return;
        }
        Estimation estimation = new Estimation(story, user, point);
        estimationRepository.save(estimation);
    }
}
