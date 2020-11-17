package com.seekers.scrumpoker.repository;

import com.seekers.scrumpoker.model.Story;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoryRepository extends MongoRepository<Story, String> {
}
