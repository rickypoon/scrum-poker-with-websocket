package com.seekers.scrumpoker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstimationController {
    @GetMapping("/summary")
    public String getSummary(@RequestParam(value = "storyId") int storyId) {
        return "A gave 3 points, B gave 3 points";
    }
}
