package com.ecommerce.ecomuser.repository;

import com.ecommerce.ecomuser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
