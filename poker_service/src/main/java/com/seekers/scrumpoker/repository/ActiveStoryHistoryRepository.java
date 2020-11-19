package com.seekers.scrumpoker.repository;

import com.seekers.scrumpoker.model.ActiveStoryHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface ActiveStoryHistoryRepository extends MongoRepository<ActiveStoryHistory, String> {
    ActiveStoryHistory findFirstByOrderByCreatedDateAsc();

    ActiveStoryHistory findFirstByActionOrderByCreatedDateDesc(String action);

    ActiveStoryHistory findFirstByCreatedDateAfterOrderByCreatedDateAsc(Date date);
}
