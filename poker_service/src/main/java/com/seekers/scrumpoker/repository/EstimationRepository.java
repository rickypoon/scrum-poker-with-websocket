package com.seekers.scrumpoker.repository;

import com.seekers.scrumpoker.model.Estimation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstimationRepository extends MongoRepository<Estimation, String> {
}
