package com.example.decipherjourney.Model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Interface UserRepository to connect the collection "user" to the MongoRepository.
 * 
 * @author Oskar Schiedewitz
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Function to get all users that are currently in the database.
     * 
     * @return A list of all users. 
     */
    public List<User> findAll();

    /**
     * Function to find a user by their username.
     * 
     * @param username The username of the user.
     * 
     * @return A list of all user with this username.
     */
    public List<User> findByUsername(String username);

}
