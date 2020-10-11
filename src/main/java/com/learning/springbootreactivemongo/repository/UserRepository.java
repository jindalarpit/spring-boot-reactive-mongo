package com.learning.springbootreactivemongo.repository;

import com.learning.springbootreactivemongo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {

}
