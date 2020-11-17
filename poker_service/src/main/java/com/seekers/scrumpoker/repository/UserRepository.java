package com.seekers.scrumpoker.repository;

import com.seekers.scrumpoker.model.Story;
import com.seekers.scrumpoker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
