package com.example.java_final_1.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.java_final_1.models.MoviesAndTvs;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MoviesAndTvsRepository extends MongoRepository<MoviesAndTvs, String> {


}
