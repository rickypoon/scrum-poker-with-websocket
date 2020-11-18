package com.seekers.scrumpoker.repository;

import com.seekers.scrumpoker.model.ActiveStoryHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActiveStoryHistoryRepository extends MongoRepository<ActiveStoryHistory, String> {
    ActiveStoryHistory findFirstByOrderByStoryIdAsc();
}
