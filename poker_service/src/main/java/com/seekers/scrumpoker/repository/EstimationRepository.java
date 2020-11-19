package com.seekers.scrumpoker.repository;

import com.seekers.scrumpoker.model.AggregationResult;
import com.seekers.scrumpoker.model.Estimation;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EstimationRepository extends MongoRepository<Estimation, String> {
    @Aggregation(pipeline = {"{'$sort': {'$id': 1}}", "{'$group':{'_id': {'story':'$story', 'user':'$user'}, 'point': {'$last': '$point'}}}"})
    List<AggregationResult> groupByStoryAndUserEmailWithLastEstimation();
}
