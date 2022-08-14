package com.example.java_final_1.repositories;
import com.example.java_final_1.models.Customer;
import com.example.java_final_1.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends MongoRepository<Customer, String> {


}
