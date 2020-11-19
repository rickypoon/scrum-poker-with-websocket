package com.seekers.scrumpoker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seekers.scrumpoker.model.*;
import com.seekers.scrumpoker.repository.ActiveStoryHistoryRepository;
import com.seekers.scrumpoker.repository.EstimationRepository;
import com.seekers.scrumpoker.repository.StoryRepository;
import com.seekers.scrumpoker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EstimationController {
    private final EstimationRepository estimationRepository;
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;
    private final ActiveStoryHistoryRepository activeStoryHistoryRepository;

    @Autowired
    ActiveStoryController activeStoryController;

    @Autowired
    public EstimationController(EstimationRepository estimationRepository, StoryRepository storyRepository, UserRepository userRepository, ActiveStoryHistoryRepository activeStoryHistoryRepository) {
        this.estimationRepository = estimationRepository;
        this.storyRepository = storyRepository;
        this.userRepository = userRepository;
        this.activeStoryHistoryRepository = activeStoryHistoryRepository;
    }

    @MessageMapping("/estimate")
    @SendTo("/topic/estimation_result")
    public String estimate(@RequestBody String params) {
        Map<String, String> estimationMap = new HashMap<>();
        try {
            estimationMap.putAll(new ObjectMapper().readValue(params, Map.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String storyId = estimationMap.get("storyId");
        String userEmail = estimationMap.get("userEmail");
        String point = estimationMap.get("point");

        if (storyId == null || userEmail == null) {
            return "";
        }

        Story story = storyRepository.findById(estimationMap.get("storyId")).orElse(null);
        User user = userRepository.findById(estimationMap.get("userEmail")).orElse(null);
        if (story == null || user == null) {
            return "";
        }
        Estimation estimation = new Estimation(story, user, point);
        estimationRepository.save(estimation);

        List<AggregationResult> estimationList = estimationRepository.groupByStoryAndUserEmailWithLastEstimation().stream().filter(e -> e.getKey().getStory().getStoryId().equals(storyId)).collect(Collectors.toList());

        List<User> allUsers = userRepository.findAll();
        if (estimationList.stream().map(e -> e.getKey().getUser()).collect(Collectors.toList()).containsAll(allUsers)) {
            activeStoryHistoryRepository.save(new ActiveStoryHistory(storyId, "SYSTEM", "RESET"));
            return getSummary(storyId, estimationList);
        }

        return "";
    }

    private String getSummary(String storyId, List<AggregationResult> estimationList) {
        String summary = String.join(", ", estimationList.stream().map(estimation -> String.format("%s gave %s points", estimation.getKey().getUser().getEmail(), estimation.getPoint())).collect(Collectors.toList()));
        String summaryJson = String.format("{\"storyId\":\"%s\", \"summary\":\"%s\"}", storyId, summary);
        return summaryJson;
    }


}
