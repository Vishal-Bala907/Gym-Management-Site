package com.gym.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gym.modals.GymUser;

@Repository
public interface GymUserRepository extends MongoRepository<GymUser, String> {
	
	GymUser findByUserName(String username);

}
