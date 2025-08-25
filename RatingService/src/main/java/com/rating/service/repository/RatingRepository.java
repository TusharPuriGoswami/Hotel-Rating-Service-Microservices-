package com.rating.service.repository;

import com.rating.service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingRepository extends MongoRepository<Rating , String> {
    /// Custom Finder Methods

    List<Rating> findByUserId(String userID);
    List<Rating> findByHotelId(String hotelId);
}
