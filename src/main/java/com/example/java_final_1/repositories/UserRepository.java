package com.example.java_final_1.repositories;

import com.example.java_final_1.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);

}
